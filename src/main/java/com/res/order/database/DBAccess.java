package com.res.order.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.res.order.dao.Menu;

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
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE all_menu SET status_of_stock=? WHERE menu_id=?");
			pstm.setInt(1,status);
			pstm.setInt(2,menuId);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fillStock(int menuId) {
		System.out.println("HI > " + menuId);
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE all_menu SET status_of_stock = 1 WHERE menu_id=?");
	
			pstm.setInt(1,menuId);
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void emptyStock(int menuId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE all_menu SET status_of_stock = 0 WHERE menu_id=?");
	
			pstm.setInt(1,menuId);
			pstm.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	public static void registerCheckIn(String customerName,int peopleCount,int tableId) {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE all_tables SET table_status = 1 WHERE table_id = ?;");
			pstm.setInt(1, tableId);
			
			pstm.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO customer_info (customer_name,total_people,checkin_time,table_id)VALUES(?,?,sysdate(),?)");
			pstm.setString(1, customerName);
			pstm.setInt(2, peopleCount);
			pstm.setInt(3, tableId);
			
			 pstm.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet adminAllMenu() {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM all_menu");
			
			ResultSet rs =pstm.executeQuery();
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet editMenu(int menuId) {
		try {
			Connection conn =DBConnector.getConnection();
			
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM all_menu WHERE menu_id=?");
			pstm.setInt(1, menuId);
			
		    ResultSet rs =pstm.executeQuery();
		    return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void cancleAction(int cId,int tId) {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("DELETE FROM customer_info WHERE customer_id = ?");
			pstm.setInt(1, cId);
			pstm.executeUpdate();
			
			PreparedStatement pstm1 = conn.prepareStatement("UPDATE all_tables SET table_status = 0 WHERE table_id = ? ");
			pstm1.setInt(1,tId);
			pstm1.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void updateAction(String cName,int nPeople,int cId) {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE customer_info SET customer_name = ?,total_people = ? WHERE customer_id = ?");
			pstm.setString(1,cName);
			pstm.setInt(2,nPeople);
			pstm.setInt(3, cId);
			
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static ResultSet showAllTable() {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM all_tables LEFT JOIN(SELECT * FROM customer_info WHERE check_status = 0)"
					+ "AS checkInCustomer ON all_tables.table_id = checkInCustomer.table_id");
			ResultSet rs = pstm.executeQuery();
			
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet login() {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM all_tables");
			ResultSet rs = pstm.executeQuery();
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void editMenuAction(Menu menu) {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO all_menu (menu_photo,menu_name,menu_price,menu_category,menu_details) VALUES (?,?,?,?,?)");
			pstm.setBytes(1,menu.getMenuPhoto().getBytes());
			pstm.setString(2, menu.getMenuName());
			pstm.setString(3, menu.getMenuPrice());
			pstm.setInt(4, menu.getMenuCategory());
			pstm.setString(5, menu.getMenuDetail());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void addMenuAction(Menu menu) {
		try {
			Connection conn =DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO all_menu (menu_photo,menu_name,menu_price,menu_category,menu_details) VALUES (?,?,?,?,?)");
			pstm.setBytes(1,menu.getMenuPhoto().getBytes());
			pstm.setString(2, menu.getMenuName());
			pstm.setString(3, menu.getMenuPrice());
			pstm.setInt(4, menu.getMenuCategory());
			pstm.setString(5, menu.getMenuDetail());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void deleteMenuAction(Menu menu) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("DELETE FROM all_menu WHERE menu_id=?");
			pstm.setInt(1,menu.getMenuId());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static ResultSet toAllMenu(int cusId) {
		try {
			
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT customer_id from customer_info WHERE check_status = 0 AND table_id = ?");
			pstm.setInt(1,cusId);
			System.out.println(pstm.toString());
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void insertOrderCart(int orderQun ,String orderItemName,int tableId,int customerId,int itemPrice) {
		try {
			Connection con = DBConnector.getConnection();
			String sql = "INSERT INTO orders (order_quantity,order_item_name,table_id,customer_id,order_price) VALUES(?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1,orderQun);
			pstm.setString(2,orderItemName);
			pstm.setInt(3,tableId);
			pstm.setInt(4,customerId);
			pstm.setInt(5,itemPrice);
			
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static ResultSet viewCart(int cusId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM orders WHERE customer_id = ? AND order_status = 0");
			pstm.setInt(1, cusId);
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void cancleorder(int orderId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("DELETE FROM orders WHERE order_id = ?");
			pstm.setInt(1,orderId);
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void orderNow(int orderId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE orders SET order_status = 1 WHERE order_id = ?");
			pstm.setInt(1, orderId);
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static ResultSet orderHis(int cusId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM orders WHERE customer_id = ? AND order_status = 1");
			pstm.setInt(1, cusId);
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static ResultSet totalPeople(int cusId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT total_people FROM customer_info WHERE customer_id = ? ");
			pstm.setInt(1, cusId);
			ResultSet rs = pstm.executeQuery();
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void paymentSuccess(int cusId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE customer_info SET check_status = 1 WHERE customer_id = ?");
			pstm.setInt(1, cusId);
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
 	
	public static ResultSet okaikeiMachi() {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT table_id,customer_id FROM customer_info WHERE check_status = 1;");
			
			ResultSet rs = pstm.executeQuery();
			
			return rs;
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet shiharaiMeisai(int cusId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM orders WHERE customer_id = ? AND order_status = 1");
			pstm.setInt(1, cusId);
			ResultSet rs = pstm.executeQuery();
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	public static void shiharaiKanryouActionChangeCustomerCheckStatus(int cusId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE customer_info SET check_status = 2, checkout_time = sysdate()WHERE customer_id = ?");
			pstm.setInt(1, cusId);
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	public static void shiharaiKanryouActionChangeTableStatus(int tableId) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE all_tables SET table_status = 0 WHERE table_id = ?");
			pstm.setInt(1, tableId);
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	public static ResultSet journelAction() {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM customer_info WHERE check_status = 2;");
			ResultSet rs = pstm.executeQuery();
			
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
//	public static void empty() {
//		try {
//			Connection conn = DBConnector.getConnection();
//			PreparedStatement pstm = conn.prepareStatement("SELECT status_of_stock = ? FROM all_menu");
//			pstm.set
//		} catch (Exception e) {
//			e.printStackTrace();// TODO: handle exception
//		}
//	}
	


	
	

			
	
}