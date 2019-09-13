package database;

import static org.junit.Assert.*;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ConnectionTest {

	@Test
	public void test() throws SQLException, InterruptedException {
		ConnectionPool connectionPool = ConnectionPoolUtils.getPoolInstance();
		String sql = "Select * from customer limit 10";
		for (int i = 0; i < 100; ++ i) {
			Connection connection = connectionPool.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			connectionPool.returnConnection(connection);
		}
		connectionPool.refreshConnections();
		connectionPool.closeConnectionPool();
	}

}
