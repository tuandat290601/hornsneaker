package vn.edu.hcmus.hornsneaker.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.ProductRepository;
import vn.edu.hcmus.hornsneaker.service.CartServices;
import vn.edu.hcmus.hornsneaker.service.ProductServices;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductServices productServices;

	@Autowired
	private CartServices cartServices;

	@RequestMapping("/category")
	public String viewProduct(Model model) {
		model.addAttribute("content", "category");
		model.addAttribute("products", productRepo.findAll());
		return "page";
	}

	@RequestMapping("/product/{productId}")
	public String viewDetail(@PathVariable("productId") Long id, Model model) {
		ProductEntity product = productServices.find(id);
		ArrayList<Integer> sizes = productServices.findAllSizeOf(id);
		Boolean isAdded = cartServices.findByProductId(id);

		productServices.increaseViews(product);
		model.addAttribute("content", "product_detail");
		model.addAttribute("name", product.getName());
		model.addAttribute("price", product.getPriceFormatted());
		model.addAttribute("description", product.getDescription());
		model.addAttribute("image", product.getImage());
		model.addAttribute("id", product.getId());
		model.addAttribute("sizes", sizes);
		model.addAttribute("isAdded", isAdded);
		return "page";
	}

	@RequestMapping("/admin/product")
	public String viewProductManagement(Model model) {
		model.addAttribute("content", "product_management");
		return "page";
	}
	
	@RequestMapping("/admin/product/add")
	public String viewAddProduct(Model model) {
		model.addAttribute("content", "add_product");
		return "page";
	}
	
	@RequestMapping("/admin/product/edit")
	public String viewEditProduct(Model model) {
		model.addAttribute("content", "edit_product");
		return "page";
	}

}
