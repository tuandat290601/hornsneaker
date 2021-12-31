package vn.edu.hcmus.hornsneaker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductSizesEntity;
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
	
	ArrayList<ProductSizesEntity> sizeList = new ArrayList<>();
	ProductEntity local = new ProductEntity();

	@RequestMapping("/category")
	public String viewProduct(@RequestParam(value = "search", defaultValue = "") String query, Model model) {
		List<ProductEntity> products;
		if (query.equals("")) products = productRepo.findAll();
		else {
			products = productServices.search(query);
			if (products.size() == 0) model.addAttribute("info", "Can't find the item you searched for");
		}
		model.addAttribute("content", "category");
		model.addAttribute("products", products);
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
		List<ProductEntity> products = productRepo.findAll();
		model.addAttribute("content", "product_management");
		model.addAttribute("products", products);
		return "page";
	}
	
	@RequestMapping("/admin/product/add")
	public String viewAddProduct(Model model) {	
		model.addAttribute("content", "add_product");
		model.addAttribute("product", local);
		model.addAttribute("productSize", new ProductSizesEntity());
		model.addAttribute("sizeList", sizeList);
		return "page";
	}
	
	@PostMapping("/admin/product/addSize")
	public String addSize(@ModelAttribute ProductEntity product, @ModelAttribute ProductSizesEntity productSize, Model model) {
		System.out.println("Add size");
		System.out.println(product.getName());
		System.out.println(local.getName());
		model.addAttribute("content", "add_product");
		sizeList.add(productSize);
		//productServices.addProduct(product, productSizes);
		return "redirect:/admin/product/add";
	}
	

	@PostMapping("/admin/product/add")
	public String addProduct(@ModelAttribute ProductEntity product, Model model) {
		model.addAttribute("content", "add_product");
		productServices.addProduct(product, sizeList);
		return "redirect:/admin/product";
	}
	
	@RequestMapping("/admin/product/edit/{productId}")
	public String viewEditProduct(@PathVariable("productId") Long productId, Model model) {
		ProductEntity product = productRepo.getById(productId);
		model.addAttribute("content", "edit_product");
		model.addAttribute("product", product);
		return "page";
	}

	@PostMapping("/admin/product/edit/save")
	public String editProduct(ProductEntity product, Model model) {
		productServices.editProduct(product.getId(), product);
		model.addAttribute("content", "edit_product");
		model.addAttribute("product", product);
		return "page";
	}
	
	@RequestMapping("/admin/product/delete/{productId}")
	public String deleteProduct(@PathVariable("productId") Long id, Model model) {
		productRepo.delete(productRepo.getById(id));
		return "redirect:/admin/product";
	}
}
