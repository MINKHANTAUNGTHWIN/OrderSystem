package com.res.order.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.dao.Menu;
import com.res.order.dao.Order;
import com.res.order.dao.Table;
import com.res.order.database.DBAccess;

import jakarta.servlet.http.HttpSession;

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
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res_order_system", "root", "root");
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
		
		DBAccess.addMenuAction(menu);
		
		return"redirect:adminHome";
	}
	//addMenuAction End
	
	//editMenuAction Start
	
	@PostMapping("/editMenuAction")
	public String editMenuAction(@ModelAttribute Menu menu) {
		
		
		try {
			 DBAccess.editMenuAction(menu);
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
		
		DBAccess.deleteMenuAction(menu);
		
		return "redirect:adminAllMenu";
	}
	
	@GetMapping("/editMenu")
	public String toEditMenuPage(Model model,@RequestParam("menuId") int menuId) {
		
		try {
			
			
			ResultSet rs = DBAccess.editMenu(menuId);
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
	
	
	@Autowired
	HttpSession httpSession;
	//AllMenu Page for Customer Start
	@GetMapping("/allMenu")
	public String toAllMenuPage(Model model) {
		try {
			int tableId =(int)httpSession.getAttribute("tableId");
			ResultSet rs = DBAccess.toAllMenu(tableId);
			
			if(rs.next()) {
				httpSession.setAttribute("customerId", rs.getInt("customer_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			
			ResultSet rs = DBAccess.adminAllMenu(); //multiple record
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
		
		return "Customer_all_menu";
	}
	//AllMenu Page for Customer End
	
	//adminAllMenu Page Start
		@GetMapping("/adminAllMenu")
		public String toAdminAllMenuPage(Model model) {
			
			try {
				ResultSet rs = DBAccess.adminAllMenu();//multiple record
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
	
	
//	@GetMapping("updateStockStatusAction")
//	public String changeStockStatus(Model model,@RequestParam("menuId") int menuId,
//			@RequestParam("status") int status) {
//		try {
//			
//			DBAccess.changeStockStatus(menuId,status);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return "redirect:adminAllMenu";
//	}
	
	
//	@GetMapping("fillStock")
//	public String fillStock() {
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return "redirect:adminAllMenu";
//		
//	}
//	
//	@GetMapping("emptyStock")
//	public String emptyStock() {
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return "redirect:adminAllMenu";
//	}
	
	
	
	
	  @GetMapping("emptyStock") 
	  public String emptyStock(Model model,@RequestParam("menuId") int menuId) { 
		  try {
			  DBAccess.emptyStock(menuId);
		  }
		  catch (Exception e) {
			  // TODO: handle exception e.printStackTrace(); }
			  e.printStackTrace();
		  }
	  
	  return "redirect:adminAllMenu"; }
	  
	  @GetMapping("fillStock") 
	  public String fillStock(Model model,@RequestParam("menuId") int menuId) { 
		  try {
			  DBAccess.fillStock(menuId);
		  }
		  catch (Exception e) {
			  // TODO: handle exception e.printStackTrace(); }
			  e.printStackTrace();
		  }
	  
	  return "redirect:adminAllMenu"; }
	  
	  @GetMapping("okaikeiMachi")
	  public String okaikeiMachi(Model model) {
		  
		  
		  try {
			  ResultSet rs = DBAccess.okaikeiMachi();
			  
			  List<Table> customTableList = new ArrayList<>();
			  while(rs.next()) {
				  Table customTable = new Table();
				  customTable.setTableId(rs.getInt("table_id"));
				  customTable.setCustomerId(rs.getInt("customer_id"));
				  
				  
				  
				  customTableList.add(customTable);
				  
			  }
			  model.addAttribute("customerList",customTableList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  return "okaikeimachi";
	  }
	  
	  @GetMapping("shiharaiMeisaiAction")
	  public String shiharaiMeisai(@RequestParam("customid")int customerid,@RequestParam("tableid")int tableid,
			  @RequestParam("pageid")int pageId,Model model) {
		  
		  try {
			  ResultSet rs = DBAccess.shiharaiMeisai(customerid);
			  List <Order> order = new ArrayList<>();
				int subTotal = 0;
				while(rs.next()) {
					Order menuPo = new Order();
					menuPo.setCustomerId(rs.getInt("customer_id"));
					menuPo.setOrderItemName(rs.getString("order_item_name"));
					menuPo.setOrderPrice(rs.getInt("order_price"));
					menuPo.setOrderquantity(rs.getInt("order_quantity"));
					menuPo.setOrderId(rs.getInt("order_id"));
					menuPo.setPageId(rs.getInt(pageId));
					
					
					subTotal += menuPo.getOrderPrice() * menuPo.getOrderquantity();
					
					
					
					order.add(menuPo);
					
				}
				model.addAttribute("order",order);
				
				model.addAttribute("subTotal",subTotal);
				model.addAttribute("tax",subTotal * 0.1);
				model.addAttribute("total", subTotal + (subTotal * 0.1));
				
				model.addAttribute("cId", customerid);
				model.addAttribute("tId", tableid);
				model.addAttribute("pageId",pageId);
				// pageid
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  return "shiharaiMeisai";
	  }
	  
	  @GetMapping("shiharaiKanryouAction")
	  public String shiharaiKanryouAction(@RequestParam("customerId")int customerid,@RequestParam("tableid")int tableid) {
		  try {
			  DBAccess.shiharaiKanryouActionChangeCustomerCheckStatus(customerid);
			  DBAccess.shiharaiKanryouActionChangeTableStatus(tableid);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  return"redirect:okaikeiMachi";
	  }
	  
	  @GetMapping("journelAction")
	  public String journelAction(Model model) {
		  try {
			  ResultSet rs = DBAccess.journelAction();
			  List<Table> cTable = new ArrayList<>();
			  while(rs.next()) {
				  Table tableList = new Table();
				  tableList.setTableStatus(rs.getInt("check_status"));
				  tableList.setCustomerId(rs.getInt("customer_id"));
				  tableList.setTableId(rs.getInt("table_id"));
				  tableList.setCheckInTime(rs.getString("checkin_time"));
				  tableList.setCheckOutTime(rs.getString("checkout_time"));
				  cTable.add(tableList);
			  }
			model.addAttribute("cTable",cTable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  return "journel";
	  }
	  
	  
	
}