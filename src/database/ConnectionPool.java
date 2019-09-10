package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.Enumeration;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * ���ӳصĴ���
 * 
 * 1.���ӳص���ƣ���װһ�����ӣ������Ƿ�æµ��Ӧ��ʱѰ��
 * 2.��Ҫ������������
	Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance()); // ʵ����������ʵ��
	DriverManager.registerDriver(driver); // ע��JDBC��������
	Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
 * 
 * @author DaiQing
 */
public class ConnectionPool {
	private String jdbcDriver = ""; // 1.���ݿ�����
	private String dbUrl = ""; // 2.���ݿ�����
	private String dbUserName = ""; // ���ݿ��û���
	private String dbPassWord = ""; // ���ݿ��û�����
	private String testTable = ""; // ���ݿ����

	private int initialConnections = 10; // ��ʼ���ӳش�С
	private int incrementalConnections = 5; // ���ӳص������ݴ�С
	private int maxConnections = 50; // ���ӳ�����С
	private Vector<PooledConnection> connections = null; // ������ӳ������ݿ����ӵ���������ʼΪnull

	public ConnectionPool(String jdbcDriver, String dbUrl, String dbUserName, String dbPassword) {
		this.jdbcDriver = jdbcDriver;
		this.dbUrl = dbUrl;
		this.dbUserName = dbUserName;
		this.dbPassWord = dbPassword;
	}

	public int getInitialConnections() {
		return this.initialConnections;
	}

	public void setInitialConnections(int initialConnections) {
		this.initialConnections = initialConnections;
	}

	public int getIncrementalConnections() {
		return this.incrementalConnections;
	}

	public String getTestTable() {
		return testTable;
	}

	public void setTestTable(String testTable) {
		this.testTable = testTable;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public void setIncrementalConnections(int incrementalConnections) {
		this.incrementalConnections = incrementalConnections;
	}

	public void createPool()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connections != null) {
			return;
		} else {
			synchronized (ConnectionPool.class) {
				if (connections != null) {
					return;
				}
				Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance()); // ʵ����������ʵ��
				DriverManager.registerDriver(driver); // ע��JDBC��������
				connections = new Vector<>();
				createConnections(this.initialConnections);
				System.out.println("���ӳش����ɹ�");
			}
		}
	}

	private void createConnections(int numConnections) throws SQLException {
		System.out.println("�������ݿ�����");
		for (int i = 0; i < numConnections; ++i) {
			if (this.maxConnections > 0 && this.connections.size() >= this.maxConnections)
				break;
			connections.addElement(new PooledConnection(newConnection()));
			System.out.println("��" + (1 + i) + "�����Ӵ������");
		}
	}

	private Connection newConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
		// ��һ�����ӣ���Ҫ������ݿ�֧�ֵ����������
		if (connections.size() == 0) {
			DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
			int maxConn = metaData.getMaxConnections();
			// �������������Ŀ
			if (maxConn > 0 && this.maxConnections > maxConn) {
				this.maxConnections = maxConn;
			}
		}
		return connection;
	}

	public synchronized Connection getConnection() throws InterruptedException, SQLException {
		if (connections == null)
			return null;
		Connection conn = getFreeConnection();
		while (conn == null) {
			wait(250);
			conn = getFreeConnection();
		}
		return conn;
	}

	private Connection getFreeConnection() throws SQLException {
		Connection connection = findFreeConnection();
		if (connection == null) {
			createConnections(incrementalConnections);
			connection = findFreeConnection();
			if (connection == null)
				return null;
		}
		return connection;
	}

	private Connection findFreeConnection() {
		Connection conn = null;
		PooledConnection pCon = null;
		Enumeration<PooledConnection> enumeration = connections.elements();
		while (enumeration.hasMoreElements()) {
			pCon = enumeration.nextElement();
			synchronized (pCon) {
				if (!pCon.isBusy()) {
					conn = pCon.getConnection();
					pCon.setBusy(true);
					if (!testConnection(conn)) {
						// ������������ѱ��жϣ��򴴽�һ���µ�����
						try {
							conn = newConnection();
						} catch (SQLException e) {
							System.out.println("��������ʧ�ܣ�" + e.getMessage());
							return null;
						}
						pCon.setConnection(conn);
					}
					break;
				}
			}
		}
		return conn;
	}

	/**
	 * Ӧ�ù黹����
	 * @param conn
	 */
	public void returnConnection(Connection conn) {
		if (connections == null) {
			System.out.println("���ӳز�����");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> elements = connections.elements();
		while (elements.hasMoreElements()) {
			pConn = (ConnectionPool.PooledConnection) elements.nextElement();
			if (pConn.getConnection() == conn) {
				pConn.setBusy(false);
				break;
			}
		}
	}

	public synchronized void refreshConnections() throws InterruptedException, SQLException {
		if (connections == null) {
			System.out.println("���ӳز����ڣ��޷�ˢ��");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> elements = connections.elements();
		while (elements.hasMoreElements()) {
			pConn = (ConnectionPool.PooledConnection) elements.nextElement();
			// �ȴ�5����
			if (pConn.isBusy()) {
				wait(5000);
			}
			//�رմ����ӣ���һ���µĴ���
			closeConnection(pConn.getConnection());
			pConn.setConnection(newConnection());
			pConn.setBusy(false);
		}
	}
	
	public synchronized void closeConnectionPool() throws InterruptedException {
		if (connections == null) {
			System.out.println("���Ӳ����ڣ��޷��ر�");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> elements = connections.elements();
		while (elements.hasMoreElements()) {
			pConn = (ConnectionPool.PooledConnection) elements.nextElement();
			if (pConn.isBusy())
				wait(5000);
			closeConnection(pConn.getConnection());
			connections.remove(pConn);
		}
	}
	
	private boolean testConnection(Connection conn) {
		try {
			if (testTable.equals("")) {
				// ������Ա�Ϊ�գ�����һ����ʹ��autoCommit���ж��Ƿ����
				conn.setAutoCommit(true);
			} else {
				Statement statement = conn.createStatement();
				statement.execute("select * from " + testTable + " limit 1");
			}
		} catch (SQLException e) {
			closeConnection(conn);
			return false;
		}
		return true;
	}

	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("�ر����ݿ����");
		}
	}

	/**
	 * һ������
	 * 
	 * @author DaiQing
	 */
	private static class PooledConnection {
		Connection connection = null;
		boolean busy = false;

		public PooledConnection(Connection connection) {
			this.connection = connection;
		}

		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		public boolean isBusy() {
			return busy;
		}

		public void setBusy(boolean busy) {
			this.busy = busy;
		}

	}

}
