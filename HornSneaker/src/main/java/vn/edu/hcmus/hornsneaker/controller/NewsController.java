package vn.edu.hcmus.hornsneaker.controller;

import java.util.Date;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.dao.domain.NewsEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.service.NewsServices;

@Controller
public class NewsController {

	@Autowired
	private NewsServices newsServices;

	@RequestMapping("/news")
	public String viewNews(Model model) {
		model.addAttribute("content", "news");
		model.addAttribute("newsList", newsServices.findAll());
		return "page";
	}

	// @RequestMapping("/news/{newsId}")
	// public String viewDetail(@PathVariable("newsId") Long id, Model model) {
	// 	NewsEntity news = newsServices.find(id);
	// 	ArrayList<Integer> sizes = newsServices.findAllSizeOf(id);
	// 	Boolean isAdded = cartServices.findByNewsId(id);

	// 	newsServices.increaseViews(news);
	// 	model.addAttribute("content", "news_detail");
	// 	model.addAttribute("name", news.getName());
	// 	model.addAttribute("price", news.getPriceFormatted());
	// 	model.addAttribute("description", news.getDescription());
	// 	model.addAttribute("image", news.getImage());
	// 	model.addAttribute("id", news.getId());
	// 	model.addAttribute("sizes", sizes);
	// 	model.addAttribute("isAdded", isAdded);
	// 	return "page";
	// }

	@RequestMapping("/admin/news")
	public String viewNewsManagement(Model model) {
		model.addAttribute("content", "news_management");
		model.addAttribute("newsList", newsServices.findAll());
		return "page";
	}

	@RequestMapping("/admin/news/add")
	public String viewAddNews(Model model) {	
		model.addAttribute("content", "add_news");
		model.addAttribute("news", new NewsEntity());
		return "page";
	}

	@PostMapping("/admin/news/add")
	public String addNews(NewsEntity news, Model model) {
		news.setCreated(new Date());
		newsServices.add(news);
		return "redirect:/admin/news/add";
	}

	@RequestMapping("/admin/news/delete/{id}")
	public String deleteNews(@PathVariable("id") Long id, Model model) {	
		newsServices.delete(id);
		return "redirect:/";
	}

}
