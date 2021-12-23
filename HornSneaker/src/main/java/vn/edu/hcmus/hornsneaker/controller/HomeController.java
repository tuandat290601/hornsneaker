package vn.edu.hcmus.hornsneaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.service.ProductServices;

@Controller
public class HomeController {

	@Autowired
	private ProductServices productServices;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<ProductEntity> products = productServices.getHighlightedProducts(3);

		model.addAttribute("content", "index");
		model.addAttribute("products", products);
		// model.addAttribute("content", "index");
		// model.addAttribute("content", "index");
		return "page";
	}
}
