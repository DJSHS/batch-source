package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
		
		String url = "jdbc:postgresql://"+System.getenv("host")+":5432/postgres";
		String username = System.getenv("user");
		String password = System.getenv("pass");
	
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("connected to POSTgres ");
		}
		return connection;
	}
}
