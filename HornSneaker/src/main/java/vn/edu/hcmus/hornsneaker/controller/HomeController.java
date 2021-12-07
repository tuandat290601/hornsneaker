package vn.edu.hcmus.hornsneaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("content", "indexform");
		return "page";
	}

	// @RequestMapping("/category")
	// public String viewCategory(Model model) {
	// 	model.addAttribute("content", "category");
	// 	return "page";
	// }
}
