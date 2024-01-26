package com.res.order.controller;

import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.database.DBAccess;


@Controller
public class LoginController {
	
	@GetMapping("/adminLoginAction")
	public String adminLoginAction(@RequestParam("user_id") String userId, 
			@RequestParam("user_pwd") String userPwd,
			Model model) {
		try {
			ResultSet rs = DBAccess.checkAccByIdPwd(userId, userPwd);
			if (rs.next()) {
				System.out.println("HI Admin");
				return "redirect:adminHome";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		// Login
		return "redirect:/";
	}
	
	@GetMapping("/LoginAction")
	public String LoginAction(@RequestParam("user_id") String userId, 
			@RequestParam("user_pwd") String userPwd,
			Model model) {
		try {
			ResultSet rs = DBAccess.checkAccByIdPwd1(userId, userPwd);
			if (rs.next()) {
				System.out.println("HI customer");
				return "redirect:allMenu";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		// Login
		return "redirect:/";
	}
	
}