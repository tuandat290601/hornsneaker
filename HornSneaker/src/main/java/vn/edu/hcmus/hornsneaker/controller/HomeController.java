package vn.edu.hcmus.hornsneaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.dao.domain.NewsEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.SalesEventEntity;
import vn.edu.hcmus.hornsneaker.service.NewsServices;
import vn.edu.hcmus.hornsneaker.service.ProductServices;
import vn.edu.hcmus.hornsneaker.service.SalesEventServices;

@Controller
public class HomeController {

	@Autowired
	private ProductServices productServices;

	@Autowired
	private NewsServices newsServices;

	@Autowired
	private SalesEventServices saleEventServices;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<ProductEntity> products = productServices.getHighlightedProducts(3);
		List<NewsEntity> news = newsServices.findAll(5);
		List<SalesEventEntity> events = saleEventServices.findAll(4);

		model.addAttribute("content", "index");
		model.addAttribute("products", products);
		model.addAttribute("news", news);
		model.addAttribute("events", events);
		return "page";
	}
}
