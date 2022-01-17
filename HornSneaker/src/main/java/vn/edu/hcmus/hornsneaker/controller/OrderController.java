package vn.edu.hcmus.hornsneaker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.hcmus.hornsneaker.dao.domain.CartEntry;
import vn.edu.hcmus.hornsneaker.dao.domain.OrderDetailsEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.OrderEntity;
import vn.edu.hcmus.hornsneaker.service.CartServices;
import vn.edu.hcmus.hornsneaker.service.OrderServices;

@Controller
public class OrderController {

	@Autowired
	private OrderServices orderServices;
	
	@Autowired
	private CartServices cartServices;

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
		model.addAttribute("customer", orderServices.getUserInfo(orderServices.findById(id).getCustomer()));
		model.addAttribute("order_detail", orderServices.getOrderDetail(id));
		return "page";
	}
	
	@RequestMapping("/admin/order/{id}/accept")
	public String acceptOrder(@PathVariable("id") Long id, Model model) {
		orderServices.acceptOrder(id);
		model.addAttribute("content", "order_management");
		model.addAttribute("orderList", orderServices.findAll());
		return "page";
	}
	
	@RequestMapping("/admin/order/{id}/cancel")
	public String cancelOrder(@PathVariable("id") Long id, Model model) {
		orderServices.cancelOrder(id);
		model.addAttribute("content", "order_management");
		model.addAttribute("orderList", orderServices.findAll());
		return "page";
	}

	@RequestMapping("/order/confirm")
	public String viewConfirmOrder(Model model) {	
		model.addAttribute("content", "confirm");
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
	
	public String toPriceFormatted(int price) {
		StringBuffer formatted = new StringBuffer(String.valueOf(price));
		for (int i = formatted.length() - 3; i > 0; i -= 3) {
			formatted.insert(i, ",");
		}
		formatted.append(" VND");
		return formatted.toString();
	}
	
	@RequestMapping("/payment")
	public String viewCustomerOrder(Model model) {
		ArrayList<CartEntry> productList = cartServices.findAllOfUser();
		int totalCost = 0, shipCost = 10000;
		for (CartEntry cartEntry : productList) {
			int n = cartEntry.getAmount();
			totalCost += cartEntry.getPrice() * n;
		}
		int totalSum = totalCost + shipCost;
		model.addAttribute("content", "payment");
		model.addAttribute("cart", productList);
		model.addAttribute("order", new OrderEntity());
		model.addAttribute("totalCost", toPriceFormatted(totalCost));
		model.addAttribute("shipCost", toPriceFormatted(shipCost));
		model.addAttribute("totalSum", toPriceFormatted(totalSum));
		return "page";			
	}
	
	@PostMapping("/confirm" )
	public String viewOrderSuccess(OrderEntity orderEntity, Model model) {	
		orderServices.createOrder(orderEntity.getPhone(), orderEntity.getAddress());
		model.addAttribute("content", "thankyou");
		return "page";			
	}
}
