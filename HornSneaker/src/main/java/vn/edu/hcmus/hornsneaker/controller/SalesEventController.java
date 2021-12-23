package vn.edu.hcmus.hornsneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.service.SalesEventServices;

@Controller
@RequestMapping("/event")
public class SalesEventController {

	@Autowired
	private SalesEventServices salesEventServices;

	@RequestMapping("")
	public String viewSalesEvent(Model model) {
		model.addAttribute("content", "salesEvent");
		model.addAttribute("salesEvent", salesEventServices.findAll());
		return "page";
	}

	// @RequestMapping("/salesEvent/{salesEventId}")
	// public String viewDetail(@PathVariable("salesEventId") Long id, Model model) {
	// 	SalesEventEntity salesEvent = salesEventServices.find(id);
	// 	ArrayList<Integer> sizes = salesEventServices.findAllSizeOf(id);
	// 	Boolean isAdded = cartServices.findBySalesEventId(id);

	// 	salesEventServices.increaseViews(salesEvent);
	// 	model.addAttribute("content", "salesEvent_detail");
	// 	model.addAttribute("name", salesEvent.getName());
	// 	model.addAttribute("price", salesEvent.getPriceFormatted());
	// 	model.addAttribute("description", salesEvent.getDescription());
	// 	model.addAttribute("image", salesEvent.getImage());
	// 	model.addAttribute("id", salesEvent.getId());
	// 	model.addAttribute("sizes", sizes);
	// 	model.addAttribute("isAdded", isAdded);
	// 	return "page";
	// }

	// @RequestMapping("/admin/salesEvent")
	// public String viewSalesEventManagement(Model model) {
	// 	model.addAttribute("content", "salesEvent_management");
	// 	return "page";
	// }

}
