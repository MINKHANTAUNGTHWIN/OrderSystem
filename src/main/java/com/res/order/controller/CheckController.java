package com.res.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.database.DBAccess;

@Controller
public class CheckController {
	
	@GetMapping("/registerCheckIn")
	public String registerCheckIn(
			@RequestParam("customerName")String customerName,
			@RequestParam("noOfPeople")int peopleCount,
			@RequestParam("tableNo")int tableId) {
		
		DBAccess.registerCheckIn(customerName, peopleCount, tableId);
		
		return"redirect:showAllTable";
	}

}
