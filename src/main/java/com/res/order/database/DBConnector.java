package com.res.order.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DBConnector {
	private static Connection connection;
	
	public static Connection getConnection() {
		return connection == null ? createConnection() : connection;
	}
	
	private static Connection createConnection() {
		try {
			ExecutorService threadpool = Executors.newCachedThreadPool();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Future<Connection> futureTask = threadpool.submit(() -> DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX"));
			while (!futureTask.isDone()) {
			    System.out.println("FutureTask is not finished yet..."); 
			} 
			
			Connection conn = futureTask.get();

//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system", "root", "root");
			connection = conn;
			
			threadpool.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	


}