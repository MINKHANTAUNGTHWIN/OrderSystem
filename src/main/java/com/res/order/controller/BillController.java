package com.res.order.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.res.order.dao.Order;
import com.res.order.database.DBAccess;

import jakarta.servlet.http.HttpSession;

@Controller
public class BillController {
	
	
	@Autowired
	HttpSession httpSession;
	@GetMapping("/okaikeiPo")
	public String okaikei(Model model) {
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
			
			
			ResultSet rs1 = DBAccess.totalPeople(uId);
			if(rs1.next()) {
				model.addAttribute("totalPeople",rs1.getInt("total_people"));
			}
			
			model.addAttribute("order",order);
			
			model.addAttribute("subTotal",subTotal);
			model.addAttribute("tax",subTotal * 0.1);
			model.addAttribute("total", subTotal + (subTotal * 0.1));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "okaikei";
	}
	
	@GetMapping("paymentSuccess")
	public String paymentSuccess() {
		try {
			int uId = (int) httpSession.getAttribute("customerId");
			DBAccess.paymentSuccess(uId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "payment_decision";
	}

}
