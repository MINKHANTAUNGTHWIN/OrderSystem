package com.res.order.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static Connection connection;
	
	public static Connection getConnection() {
		return connection == null ? createConnection() : connection;
	}
	
	private static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			connection = conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	


}