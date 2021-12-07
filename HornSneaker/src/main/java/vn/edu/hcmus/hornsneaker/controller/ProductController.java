package vn.edu.hcmus.hornsneaker.controller;

import java.util.ArrayList;

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
	private ProductServices productServices;
	
	@RequestMapping("/category")
	public String viewProduct(Model model) {
		ArrayList <ProductEntity> list = productServices.listAllProducts();
		model.addAttribute("products", productServices.listAllProducts());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
		}
		return "category";
	}
}
