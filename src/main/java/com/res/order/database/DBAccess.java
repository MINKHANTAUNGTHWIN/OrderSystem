package com.res.order.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBAccess {
	
	public static ResultSet checkAccByIdPwd(String userId, String userPwd) {
		try {
			Connection con = DBConnector.getConnection();
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM account WHERE user_id = ? AND user_pwd = ? AND user_role=0");
			pstm.setString(1, userId);
			pstm.setString(2, userPwd);

			ResultSet rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {	
			e.printStackTrace();
		}

		return null;
	}
	
	public static ResultSet checkAccByIdPwd1(String userId, String userPwd) {
		try {
			Connection con = DBConnector.getConnection();
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM account WHERE user_id = ? AND user_pwd = ?");
			pstm.setString(1, userId);
			pstm.setString(2, userPwd);

			ResultSet rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void changeStockStatus (int menuId,int status) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			String sql = "UPDATE all_menu SET status_of_stock=? WHERE menu_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1,status);
			pstm.setInt(2,menuId);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}