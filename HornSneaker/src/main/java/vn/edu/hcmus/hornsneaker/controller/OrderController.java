package vn.edu.hcmus.hornsneaker.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.dao.domain.NewsEntity;
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

	@RequestMapping("/news/{newsId}")
	public String viewDetail(@PathVariable("newsId") Long id, Model model) {
		model.addAttribute("content", "news_detail");
		model.addAttribute("news", newsServices.findById(id));
		return "page";
	}

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

	@RequestMapping("/admin/news/edit/{id}")
	public String viewEditNews(@PathVariable("id") Long id, Model model) {	
		model.addAttribute("content", "edit_news");
		model.addAttribute("news", newsServices.findById(id));
		return "page";
	}

	@PostMapping("/admin/news/edit/{id}")
	public String editNews(@PathVariable("id") Long id, @ModelAttribute NewsEntity news, Model model) {
		newsServices.edit(id, news);
		return "redirect:/admin/news/" + id;
	}

	@RequestMapping("/admin/news/delete/{id}")
	public String deleteNews(@PathVariable("id") Long id, Model model) {	
		newsServices.delete(id);
		return "redirect:/";
	}

}
