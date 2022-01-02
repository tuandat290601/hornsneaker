package vn.edu.hcmus.hornsneaker.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.dao.domain.SalesEventEntity;
import vn.edu.hcmus.hornsneaker.service.SalesEventServices;

@Controller
public class SalesEventController {

	@Autowired
	private SalesEventServices salesEventServices;

	@RequestMapping("/sales")
	public String viewSalesEvent(Model model) {
		model.addAttribute("content", "salesEvent");
		model.addAttribute("salesEvent", salesEventServices.findAll());
		return "page";
	}

	@RequestMapping("/sales/{id}")
	public String viewDetail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("content", "salesEvent_detail");
		model.addAttribute("sales", salesEventServices.findById(id));
		return "page";
	}

	@RequestMapping("/admin/sales")
	public String viewSalesEventManagement(Model model) {
		model.addAttribute("content", "salesEvent_management");
		return "page";
	}

	@RequestMapping("/admin/sales/add")
	public String viewAddSalesEvent(Model model) {	
		model.addAttribute("content", "add_sales");
		model.addAttribute("sales", new SalesEventEntity());
		return "page";
	}

	@PostMapping("/admin/sales/add")
	public String addSalesEvent(SalesEventEntity sales, Model model) {
		salesEventServices.add(sales);
		return "redirect:/admin/sales/add";
	}

	@RequestMapping("/admin/sales/edit/{id}")
	public String viewEditSalesEvent(@PathVariable("id") Long id, Model model) {	
		model.addAttribute("content", "edit_sales");
		model.addAttribute("sales", salesEventServices.findById(id));
		return "page";
	}

	@PostMapping("/admin/sales/edit/{id}")
	public String editSalesEvent(@PathVariable("id") Long id, @ModelAttribute SalesEventEntity sales, Model model) {
		salesEventServices.edit(id, sales);
		return "redirect:/admin/sales/" + id;
	}

	@RequestMapping("/admin/sales/delete/{id}")
	public String deleteSalesEvent(@PathVariable("id") Long id, Model model) {	
		salesEventServices.delete(id);
		return "redirect:/";
	}

}
