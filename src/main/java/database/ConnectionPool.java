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
 * 连接池的创建
 * 
 * 1.连接池的设计，包装一次连接，设置是否忙碌，应用时寻找
 * 2.需要背会的连接语句
	Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance()); // 实例化驱动类实例
	DriverManager.registerDriver(driver); // 注册JDBC驱动程序
	Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
 * 
 * @author DaiQing
 */
public class ConnectionPool {
	private String jdbcDriver = ""; // 1.数据库驱动
	private String dbUrl = ""; // 2.数据库名称
	private String dbUserName = ""; // 数据库用户名
	private String dbPassWord = ""; // 数据库用户密码
	private String testTable = ""; // 数据库表名

	private int initialConnections = 10; // 初始连接池大小
	private int incrementalConnections = 5; // 连接池单次扩容大小
	private int maxConnections = 50; // 连接池最大大小
	private Vector<PooledConnection> connections = null; // 存放连接池中数据库连接的向量，初始为null

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
				Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance()); // 实例化驱动类实例
				DriverManager.registerDriver(driver); // 注册JDBC驱动程序
				connections = new Vector<>();
				createConnections(this.initialConnections);
				System.out.println("连接池创建成功");
			}
		}
	}

	private void createConnections(int numConnections) throws SQLException {
		System.out.println("创建数据库连接");
		for (int i = 0; i < numConnections; ++i) {
			if (this.maxConnections > 0 && this.connections.size() >= this.maxConnections)
				break;
			connections.addElement(new PooledConnection(newConnection()));
			System.out.println("第" + (1 + i) + "个连接创建完成");
		}
	}

	private Connection newConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
		// 第一次连接，需要检查数据库支持的最大连接数
		if (connections.size() == 0) {
			DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
			int maxConn = metaData.getMaxConnections();
			// 更新最大连接数目
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
						// 如果此连接早已被中断，则创建一个新的连接
						try {
							conn = newConnection();
						} catch (SQLException e) {
							System.out.println("创建连接失败：" + e.getMessage());
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
	 * 应用归还连接
	 * @param conn
	 */
	public void returnConnection(Connection conn) {
		if (connections == null) {
			System.out.println("连接池不存在");
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
			System.out.println("连接池不存在，无法刷新");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> elements = connections.elements();
		while (elements.hasMoreElements()) {
			pConn = (ConnectionPool.PooledConnection) elements.nextElement();
			// 等待5秒钟
			if (pConn.isBusy()) {
				wait(5000);
			}
			//关闭此连接，用一个新的代替
			closeConnection(pConn.getConnection());
			pConn.setConnection(newConnection());
			pConn.setBusy(false);
		}
	}
	
	public synchronized void closeConnectionPool() throws InterruptedException {
		if (connections == null) {
			System.out.println("连接不存在，无法关闭");
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
				// 如果测试表为空，看了一尝试使用autoCommit来判断是否可用
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
			System.err.println("关闭数据库出错");
		}
	}

	/**
	 * 一个连接
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
