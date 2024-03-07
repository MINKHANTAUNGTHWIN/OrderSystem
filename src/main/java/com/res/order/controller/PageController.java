package com.res.order.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.dao.Menu;
import com.res.order.dao.Order;
import com.res.order.dao.Table;
import com.res.order.database.DBAccess;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/")
	public String login(Model model) {
		
		List<Table> tablesObj = new ArrayList<>();
		try {
			
			ResultSet rs =DBAccess.login();
			while(rs.next()){
				Table tableIdObj = new Table();
				tableIdObj.setTableId(rs.getInt("table_id"));
				tableIdObj.setTableStatus(rs.getInt("table_status"));
				
				tablesObj.add(tableIdObj);
				
			}
			model.addAttribute("tableList",tablesObj);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		return "index";
	}

	@GetMapping("/adminHome")
	public String adminHome() {
		return "admin_home";
	}

	@GetMapping("/addMenu")
	public String toAddMenuPage(Model model) {
		Menu menu = new Menu();
		menu.setMenuName("Good");
		
		model.addAttribute("menuObj", menu);
		return "add_menu_item";
	}
	
	@GetMapping("viewCart")
	public String viewCart(Model model) {
		try {
			int uId = (int) httpSession.getAttribute("customerId");
			ResultSet rs = DBAccess.viewCart(uId);
			List <Order> order = new ArrayList<>();
			while(rs.next()) {
				Order menuPo = new Order();
				
				menuPo.setOrderItemName(rs.getString("order_item_name"));
				menuPo.setOrderPrice(rs.getInt("order_price"));
				menuPo.setOrderquantity(rs.getInt("order_quantity"));
				menuPo.setOrderId(rs.getInt("order_id"));
				
				
				order.add(menuPo);
				
			}
			model.addAttribute("order",order);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "cart";	
	}
	
	@GetMapping("cancleOrder")
	public String cancleOrder(@RequestParam("orderId")int orderId) {
		try {
			DBAccess.cancleorder(orderId);
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		return "redirect:viewCart";
	}
	
	@GetMapping("orderNow")
	public String orderNow(@RequestParam("orderId")int orderId) {
		try {
			DBAccess.orderNow(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:viewCart";
	}
	
	@GetMapping("orderHis")
	public String orderHis(Model model) {
		try {
			int uId = (int) httpSession.getAttribute("customerId");
			ResultSet rs = DBAccess.orderHis(uId);
			List <Order> order = new ArrayList<>();
			int subTotal = 0;
			while(rs.next()) {
				Order menuPo = new Order();
				
				menuPo.setOrderItemName(rs.getString("order_item_name"));
				menuPo.setOrderPrice(rs.getInt("order_price"));
				menuPo.setOrderquantity(rs.getInt("order_quantity"));
				menuPo.setOrderId(rs.getInt("order_id"));
				
				subTotal += menuPo.getOrderPrice() * menuPo.getOrderquantity();
				
				
				
				order.add(menuPo);
				
			}
			model.addAttribute("order",order);
			
			model.addAttribute("subTotal",subTotal);
			model.addAttribute("tax",subTotal * 0.1);
			model.addAttribute("total", subTotal + (subTotal * 0.1));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "orderHistory";

}
}
