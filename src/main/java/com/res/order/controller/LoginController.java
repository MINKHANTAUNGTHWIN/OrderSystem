package com.res.order.controller;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.database.DBAccess;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
	
	@GetMapping("/adminLoginAction")
	public String adminLoginAction(@RequestParam("user_id") String userId, 
			@RequestParam("user_pwd") String userPwd,
			Model model) {
		try {
			ResultSet rs = DBAccess.checkAccByIdPwd(userId, userPwd);
			if (rs.next()) {
				
				return "redirect:adminHome";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		// Login
		return "redirect:/";
	}
	
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/customerLoginAction")
	public String LoginAction(@RequestParam("user_id") String userId, 
			@RequestParam("user_pwd") String userPwd,@RequestParam("tableId") int tId,
			Model model) {
		try {
			ResultSet rs = DBAccess.checkAccByIdPwd1(userId, userPwd);
			if (rs.next() && rs!=null) {
				httpSession.setAttribute("tableId",tId);
				httpSession.setAttribute("userId",userId);
				
				return "redirect:allMenu";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		// Login
		return "redirect:/";
	}
	
}