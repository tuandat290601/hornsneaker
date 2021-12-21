package vn.edu.hcmus.hornsneaker.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmus.hornsneaker.dao.domain.CartEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.CartEntry;
import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.CartRepository;
import vn.edu.hcmus.hornsneaker.service.CartServices;
import vn.edu.hcmus.hornsneaker.service.UserAccountServices;

@Controller
@RequestMapping(path = "cart")
public class CartController {

	@Autowired
	private CartServices cartServices;

	@Autowired
	private UserAccountServices userAccountServices;

	Long getLoggedUserId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAccountEntity userAccount = userAccountServices.getUserAccountByEmail(user.getUsername());
		return userAccount.getId();
	}

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
		ArrayList<CartEntry> entries = cartServices.findAllOfUser(getLoggedUserId());
		int totalNumber = 0, totalCost = 0, shipCost = 10000;
		for (CartEntry cartEntry : entries) {
			totalNumber += cartEntry.getAmount();
			totalCost += cartEntry.getPrice();
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
	public String addToCart(@PathVariable("productId") Long id, Model model) {		
		cartServices.add(getLoggedUserId(), id);
		return "redirect:/product/" + id;
	}

	@RequestMapping("/remove/{cartEntryId}")
	public String removeFromCart(@PathVariable("cartEntryId") Long id, Model model) {		
		cartServices.remove(id);
		return "redirect:/cart";
	}
}
