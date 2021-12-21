package vn.edu.hcmus.hornsneaker.controller;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.ProductRepository;
import vn.edu.hcmus.hornsneaker.service.ProductServices;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepo;
	private ProductServices productServices;

	
	@RequestMapping("/category")
	public String viewProduct(Model model) {
		model.addAttribute("content", "category");
		model.addAttribute("products", productRepo.findAll());
		return "page";
	}

	@RequestMapping("/product")
	public String viewDetail(Model model) {
		ProductEntity product = productServices.find(1);

		model.addAttribute("content", "product_detail");
		model.addAttribute("name", product.getName());
		model.addAttribute("price", product.getPrice());
		model.addAttribute("description", product.getDescription());
		return "page";
	}
	
	@RequestMapping("/admin/product")
	public String viewProductManagement(Model model) {
		model.addAttribute("content", "product_management");
		return "page";
	}


	@RequestMapping("/cart")
	public String viewCart(Model model) {
		model.addAttribute("content", "cart");
		return "page";
	}
}
