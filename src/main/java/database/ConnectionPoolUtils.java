package database;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolUtils {
	private ConnectionPoolUtils() {
	}

	private volatile static ConnectionPool pool = null;

	public static ConnectionPool getPoolInstance() {
		if (pool == null) {
			synchronized (ConnectionPoolUtils.class) {
				if (pool == null) {
					pool = new ConnectionPool("com.mysql.jdbc.Driver",
							"jdbc:mysql://localhost:3306/cumt?useUnicode=true&characterEncoding=utf-8", "root",
							"daiqing123");
					try {
						pool.createPool();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return pool;
	}
}
