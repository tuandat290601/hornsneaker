package vn.edu.hcmus.hornsneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmus.hornsneaker.service.OrderServices;

@Controller
public class OrderController {

	@Autowired
	private OrderServices orderServices;

	// @RequestMapping("/order")
	// public String viewOrder(Model model) {
	// 	model.addAttribute("content", "order");
	// 	model.addAttribute("orderList", orderServices.findAll());
	// 	return "page";
	// }

	// @RequestMapping("/order/{orderId}")
	// public String viewDetail(@PathVariable("orderId") Long id, Model model) {
	// 	model.addAttribute("content", "order_detail");
	// 	model.addAttribute("order", orderServices.findById(id));
	// 	return "page";
	// }

	@RequestMapping("/admin/order")
	public String viewOrderManagement(Model model) {
		model.addAttribute("content", "order_management");
		model.addAttribute("orderList", orderServices.findAll());
		return "page";
	}

	// @RequestMapping("/admin/order/add")
	// public String viewAddOrder(Model model) {	
	// 	model.addAttribute("content", "add_order");
	// 	model.addAttribute("order", new OrderEntity());
	// 	return "page";
	// }

	// @PostMapping("/admin/order/add")
	// public String addOrder(OrderEntity order, Model model) {
	// 	order.setCreated(new Date());
	// 	orderServices.add(order);
	// 	return "redirect:/admin/order/add";
	// }

	@RequestMapping("/admin/order/{id}")
	public String viewOrderDetails(@PathVariable("id") Long id, Model model) {	
		model.addAttribute("content", "order_detail");
		model.addAttribute("order", orderServices.findById(id));
		return "page";
	}

	// @PostMapping("/admin/order/edit/{id}")
	// public String editOrder(@PathVariable("id") Long id, @ModelAttribute OrderEntity order, Model model) {
	// 	orderServices.edit(id, order);
	// 	return "redirect:/admin/order/" + id;
	// }

	// @RequestMapping("/admin/order/delete/{id}")
	// public String deleteOrder(@PathVariable("id") Long id, Model model) {	
	// 	orderServices.delete(id);
	// 	return "redirect:/";
	// }

}
