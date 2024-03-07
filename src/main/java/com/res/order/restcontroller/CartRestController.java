package com.res.order.restcontroller;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.res.order.database.DBAccess;

import jakarta.servlet.http.HttpSession;



@RestController
public class CartRestController {
	

	@Autowired
	HttpSession httpSession;
	@GetMapping("/addCart")
	public void addCart(@RequestParam ("count") int count,
			@RequestParam ("itemName") String itemName,
			@RequestParam ("itemPrice") int itemPrice,Model model) {
		
		System.out.println("Calling Me!" + count +" "+ itemName +" "+ itemPrice);
		int tabId = (int) httpSession.getAttribute("tableId");
		int cusId = (int) httpSession.getAttribute("customerId");
		System.out.println(tabId);
		System.out.println(cusId);
		
		try {
			DBAccess.insertOrderCart(count,itemName,tabId,cusId,itemPrice);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
