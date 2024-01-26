package com.res.order.database; 	

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBAccess {
	public static ResultSet loginByUserInfo(String userId,String userPwd,int userRole) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system","root","root");
			
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM account WHERE user_id = ? AND user_pwd = ? AND user_role = ?");
			pstm.setString(1, userId);
			pstm.setString(2, userPwd);
			pstm.setInt(3, userRole);
			
			return pstm.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception\
		e.printStackTrace();
		}
		return null;
	}
}
//	
//	public static ResultSet addMenu(MultipartFile iPhoto,String iName,String iPrice,int iCategory,String iIngre) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system","root","root");
//			
//			PreparedStatement pstm = conn.prepareStatement("INSERT INTO all_menu (menu_photo,menu_name,menu_price,menu_category,menu_details) VALUES (?,?,?,?,?)");
//			
//			pstm.setBytes(1, iPhoto.getBytes());
//			pstm.setString(2, iName);
//			pstm.setString(3, iPrice);
//			pstm.setInt(4, iCategory);
//			pstm.setString(5, iIngre);
//			
//			pstm.executeUpdate();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static ResultSet allMenu() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system","root","root");
//			
//			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM all_menu");
//			
//			return pstm.executeQuery();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static ResultSet removeMenu(int menuId) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system","root","root");
//			
//			PreparedStatement pstm = conn.prepareStatement("DELETE FROM all_menu WHERE menu_id = ?");
//			pstm.setInt(1, menuId);
//			
//			 pstm.executeUpdate();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static ResultSet editMenu(int menuId) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system","root","root");
//			
//			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM all_menu WHERE menu_id = ?");
//			pstm.setInt(1, menuId);
//			
//			return pstm.executeQuery();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public static void editMenuAction(Menu menu) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system","root","root");
//			
//			PreparedStatement pstm = conn.prepareStatement("UPDATE all_menu SET menu_name = ?, menu_price = ?, menu_category = ?, menu_details = ?  WHERE menu_id = ?");
//			
//			pstm.setString(1,menu.getMenuName());
//			pstm.setString(2, menu.getMenuPrice());
//			pstm.setInt(3,menu.getMenuCategory());
//			pstm.setString(4, menu.getMenuDetails());
//			
//			pstm.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception\
//			e.printStackTrace();
//		}
//	}
//}
//
//
