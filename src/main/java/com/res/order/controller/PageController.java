package com.res.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.res.order.dao.Menu;

@Controller
public class PageController {
	@GetMapping("/")
	public String login() {
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
}
