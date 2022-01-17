package vn.edu.hcmus.hornsneaker.controller;

import java.util.ArrayList;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmus.hornsneaker.dao.domain.CartEntry;
import vn.edu.hcmus.hornsneaker.service.CartServices;

@Controller
@RequestMapping(path = "cart")
public class CartController {

	@Autowired
	private CartServices cartServices;

	public String toPriceFormatted(int price) {
		StringBuffer formatted = new StringBuffer(String.valueOf(price));
		for (int i = formatted.length() - 3; i > 0; i -= 3) {
			formatted.insert(i, ",");
		}
		formatted.append(" VND");
		return formatted.toString();
	}

	@RequestMapping("")
	public String viewCart(Model model) {
		ArrayList<CartEntry> entries = cartServices.findAllOfUser();
		int totalNumber = 0, totalCost = 0, shipCost = 10000;
		for (CartEntry cartEntry : entries) {
			int n = cartEntry.getAmount();
			totalNumber += n;
			totalCost += cartEntry.getPrice() * n;
		}

		int totalSum = totalCost + shipCost;
		model.addAttribute("content", "cart");
		model.addAttribute("entries", entries);
		model.addAttribute("totalNumber", totalNumber);
		model.addAttribute("totalCost", toPriceFormatted(totalCost));
		model.addAttribute("shipCost", toPriceFormatted(shipCost));
		model.addAttribute("totalSum", toPriceFormatted(totalSum));
		return "page";
	}

	@RequestMapping("/add/{productId}")
	public String addToCart(@PathVariable("productId") Long id, @RequestParam("size") int size, Model model) {
		cartServices.add(id, size);
		return "redirect:/cart";///" + id;
	}

	@RequestMapping("/remove/{cartEntryId}")
	public String removeFromCart(@PathVariable("cartEntryId") Long id, Model model) {
		cartServices.remove(id);
		return "redirect:/cart";
	}

	@RequestMapping("/{cartEntryId}")
	public String adjustAmount(@PathVariable("cartEntryId") Long id, @RequestParam("adjustment") int adj, Model model) {
		cartServices.adjustAmount(id, adj);
		return "redirect:/cart";
	}
}
