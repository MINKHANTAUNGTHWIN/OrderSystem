package com.res.order.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.dao.Table;
import com.res.order.database.DBAccess;


@Controller
public class TableController {
	
	@GetMapping("showAllTable")
	public String init(Model model) {
		
		List<Table> tables = new ArrayList<>();
		try {
			ResultSet rs = DBAccess.showAllTable();
			
			while(rs.next()) {
				Table obj = new Table();
				obj.setTableId(rs.getInt("table_id"));
				obj.setTableStatus(rs.getInt("table_status"));
				obj.setTableCapacity(rs.getInt("table_capacity"));
				obj.setTotalPeople(rs.getInt("total_people"));
				obj.setCustomerName(rs.getString("customer_name"));
				obj.setCustomerId(rs.getInt("customer_id"));
			
				
				tables.add(obj);
			}
			
			model.addAttribute("menu",tables);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			e.printStackTrace();
		}
		
	return "table_list";
}
	
	@GetMapping("upDateAction")
	public String upDateAction(@RequestParam("customerName")String cName,@RequestParam("noOfPeople") int nPeople,@RequestParam("customerId") int cId) {
		try {
			DBAccess.updateAction(cName, nPeople, cId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:showAllTable";
	}
	
	@GetMapping("cancelAction")
	public String cancelAction(@RequestParam("customerId")int cId,@RequestParam("tableNo")int tId) {
		try {
			DBAccess.cancleAction(cId, tId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:showAllTable";
	}
}
