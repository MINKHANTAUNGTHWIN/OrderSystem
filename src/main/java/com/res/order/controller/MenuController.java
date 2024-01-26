package com.res.order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.dao.Menu;
import com.res.order.database.DBAccess;

@Controller
public class MenuController {
	
	
//Old method
//	@PostMapping("/addMenuAction")
//	public String addMenuAction(
//			@RequestParam("image")MultipartFile menuPhoto,
//			@RequestParam("iName")String menuName,
//			@RequestParam("iPrice")String menuPrice,
//			@RequestParam("iCategory")int menuCategory,
//			@RequestParam("iIngre")String menuDetail) {
//		System.out.println("Add Menu Action");
//		System.out.println(menuPhoto);
//		System.out.println(menuName);
//		System.out.println(menuPrice);
//		System.out.println(menuCategory);
//		System.out.println(menuDetail);
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_app", "root", "root");
//			String sql = "INSERT INTO all_menu (menu_photo,menu_name,menu_price,menu_category,menu_detail) VALUES (?,?,?,?,?)";
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setBytes(1,menuPhoto.getBytes());
//			pstm.setString(2, menuName);
//			pstm.setString(3, menuPrice);
//			pstm.setInt(4, menuCategory);
//			pstm.setString(5, menuDetail);
//			
//			pstm.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return"redirect:adminHome";
//	}
	
	//addMenuAction Start
	@PostMapping("/addMenuAction")
	public String addMenuAction(@ModelAttribute Menu menu) {
		System.out.println("Add Menu Action");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			String sql = "INSERT INTO all_menu (menu_photo,menu_name,menu_price,menu_category,menu_details) VALUES (?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
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
		
		return"redirect:adminHome";
	}
	//addMenuAction End
	
	//editMenuAction Start
	
	@PostMapping("/editMenuAction")
	public String editMenuAction(@ModelAttribute Menu menu) {
		System.out.println(menu.getMenuId());
		System.out.println(menu.getMenuPrice());
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			String sql = "UPDATE all_menu SET menu_photo = ?,menu_name = ?, menu_price= ?, menu_category= ?, menu_details=? WHERE menu_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setBytes(1,menu.getMenuPhoto().getBytes());
			pstm.setString(2, menu.getMenuName());
			pstm.setString(3, menu.getMenuPrice());
			pstm.setInt(4, menu.getMenuCategory());
			pstm.setString(5, menu.getMenuDetail());
			pstm.setInt(6, menu.getMenuId());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:adminAllMenu";
	}
	
	//editMenuAction End
	
	//deleteMenuAction
	@GetMapping("deleteMenuAction")
	public String deleteMenuAction(@ModelAttribute Menu menu) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			String sql = "DELETE FROM all_menu WHERE menu_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1,menu.getMenuId());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:adminAllMenu";
	}
	
	@GetMapping("/editMenu")
	public String toEditMenuPage(Model model,@RequestParam("menuId") int menuId) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			String sql = "SELECT * FROM all_menu WHERE menu_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, menuId);
			
			ResultSet rs = pstm.executeQuery();
			Menu menuObj = new Menu();
			if(rs.next()) {
				menuObj.setMenuId(rs.getInt("menu_id"));
				menuObj.setMenuName(rs.getString("menu_name"));
				menuObj.setMenuPrice(rs.getString("menu_price"));
				menuObj.setMenuCategory(rs.getInt("menu_category"));
				menuObj.setMenuDetail(rs.getString("menu_details"));
				menuObj.setPhotoBase64String(Base64.encodeBase64String(rs.getBytes("menu_photo")));
			}
			model.addAttribute("menuObj",menuObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "edit_menu_item";
	}
	
	
	//AllMenu Page for Customer Start
	@GetMapping("/allMenu")
	public String toAllMenuPage(Model model) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
			String sql = "SELECT * FROM all_menu";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			ResultSet rs = pstm.executeQuery(); //multiple record
			List <Menu> setMenu = new ArrayList<>();
			List <Menu> tanpinMenu = new ArrayList<>();
			List <Menu> dessertMenu = new ArrayList<>();
			List <Menu> softDrinkMenu = new ArrayList<>();
			List <Menu> whiskeyMenu = new ArrayList<>();
			List <Menu> beerMenu = new ArrayList<>();
			List <Menu> nonAlcholMenu = new ArrayList<>();
			while(rs.next()) {
				Menu menuObj = new Menu();
				menuObj.setMenuId(rs.getInt("menu_id"));
				menuObj.setMenuName(rs.getString("menu_name"));
				menuObj.setMenuPrice(rs.getString("menu_price"));
				menuObj.setMenuCategory(rs.getInt("menu_category"));
				menuObj.setMenuDetail(rs.getString("menu_details"));
				menuObj.setStatusOfStock(rs.getInt("status_of_stock"));
				
				menuObj.setPhotoBase64String(Base64.encodeBase64String(rs.getBytes("menu_photo")));
				
				switch (menuObj.getMenuCategory()) {
				case 0:
					setMenu.add(menuObj);
					break;
				case 1:
					tanpinMenu.add(menuObj);
					break;
				case 2:
					dessertMenu.add(menuObj);
					break;
				case 3:
					softDrinkMenu.add(menuObj);
					break;
				case 4:
					whiskeyMenu.add(menuObj);
					break;
				case 5:
					beerMenu.add(menuObj);
					break;
				case 6:
					nonAlcholMenu.add(menuObj);
					break;
				
				}
			}
			
			model.addAttribute("setMenus",setMenu);
			model.addAttribute("tanpinMenus",tanpinMenu);
			model.addAttribute("dessertMenus",dessertMenu);
			model.addAttribute("softDrinkMenus",softDrinkMenu);
			model.addAttribute("whiskeyMenus",whiskeyMenu);
			model.addAttribute("beerMenus",beerMenu);
			model.addAttribute("nonAlcholMenus",nonAlcholMenu);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "all_menu";
	}
	//AllMenu Page for Customer End
	
	//adminAllMenu Page Start
		@GetMapping("/adminAllMenu")
		public String toAdminAllMenuPage(Model model) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-30bbb2cd-first-project-2024.a.aivencloud.com:15724/res_order_app?sslmode=require", "avnadmin", "AVNS_PWS5KJ-LqBEeeLMayFX");
				String sql = "SELECT * FROM all_menu";
				PreparedStatement pstm = conn.prepareStatement(sql);
				
				ResultSet rs = pstm.executeQuery(); //multiple record
				List <Menu> setMenu = new ArrayList<>();
				List <Menu> tanpinMenu = new ArrayList<>();
				List <Menu> dessertMenu = new ArrayList<>();
				List <Menu> softDrinkMenu = new ArrayList<>();
				List <Menu> whiskeyMenu = new ArrayList<>();
				List <Menu> beerMenu = new ArrayList<>();
				List <Menu> nonAlcholMenu = new ArrayList<>();
				while(rs.next()) {
					Menu menuObj = new Menu();
					menuObj.setMenuId(rs.getInt("menu_id"));
					menuObj.setMenuName(rs.getString("menu_name"));
					menuObj.setMenuPrice(rs.getString("menu_price"));
					menuObj.setMenuCategory(rs.getInt("menu_category"));
					menuObj.setMenuDetail(rs.getString("menu_details"));
					menuObj.setStatusOfStock(rs.getInt("status_of_stock"));
					
					menuObj.setPhotoBase64String(Base64.encodeBase64String(rs.getBytes("menu_photo")));
					
					switch (menuObj.getMenuCategory()) {
					case 0:
						setMenu.add(menuObj);
						break;
					case 1:
						tanpinMenu.add(menuObj);
						break;
					case 2:
						dessertMenu.add(menuObj);
						break;
					case 3:
						softDrinkMenu.add(menuObj);
						break;
					case 4:
						whiskeyMenu.add(menuObj);
						break;
					case 5:
						beerMenu.add(menuObj);
						break;
					case 6:
						nonAlcholMenu.add(menuObj);
						break;
					
					}
				}
				
				model.addAttribute("setMenus",setMenu);
				model.addAttribute("tanpinMenus",tanpinMenu);
				model.addAttribute("dessertMenus",dessertMenu);
				model.addAttribute("softDrinkMenus",softDrinkMenu);
				model.addAttribute("whiskeyMenus",whiskeyMenu);
				model.addAttribute("beerMenus",beerMenu);
				model.addAttribute("nonAlcholMenus",nonAlcholMenu);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "admin_all_menu";
		}
		//adminAllMenu Page End
	
	
	@GetMapping("updateStockStatusAction")
	public String changeStockStatus(Model model,@RequestParam("menuId") int menuId,
			@RequestParam("status") int status) {
		try {
			
			DBAccess.changeStockStatus(menuId,status);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:adminAllMenu";
	}
	
//	@GetMapping("updateStockStatusAction1")
//	public String changeStockStatus1(Model model,@RequestParam("menuId") int menuId,
//			@RequestParam("status") int status) {
//		try {
//			DBAccess.changeStockStatus(menuId,1);
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return "redirect:adminAllMenu";
//	}
	
}