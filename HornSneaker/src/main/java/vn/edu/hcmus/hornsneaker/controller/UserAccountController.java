package vn.edu.hcmus.hornsneaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.UserAccountRepository;

@Controller
public class UserAccountController {
	@Autowired
	private UserAccountRepository UserRepo;
	
	
	@RequestMapping("/login")
	public String viewLogin(Model model) {
		model.addAttribute("content", "loginform");
		return "page";
	}
	

	// @RequestMapping("/logout")
	// public String logout(Model model) {

	// 	model.addAttribute("content", "h");
	// 	return "page";
	// }
	
	@RequestMapping("/register")
	public String viewRegister(Model model) {
		model.addAttribute("content", "registerform");
		model.addAttribute("User", new UserAccountEntity());
		return "page";
	}
	
	@PostMapping("/register_success")
	public String viewRegisterSuccess(UserAccountEntity User, Model model) {
		if (UserRepo.findByEmail(User.getEmail()) == null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(User.getPassword());
			User.setPassword(encodedPassword);
			User.setRole("USER");
			UserRepo.save(User);
			return "register_success";
		}
		else {
			model.addAttribute("errorMessage", "Email existed!");
			return "redirect:/register";
		}
	}
}
