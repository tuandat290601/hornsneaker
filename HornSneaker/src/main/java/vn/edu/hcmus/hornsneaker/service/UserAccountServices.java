package vn.edu.hcmus.hornsneaker.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.UserAccountRepository;

@Service
public class UserAccountServices implements UserDetailsService {
	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserAccountEntity userAccount = getUserAccountByEmail(email);
		UserDetails user = User.withUsername(userAccount.getFirstname()).password(userAccount.getPassword())
				.authorities(userAccount.getRole()).build();
		return user;
	}

	public UserAccountEntity getUserAccountByEmail(String email) {
		Optional<UserAccountEntity> userAccount = userAccountRepository.findByEmail(email);
		if (!userAccount.isPresent()) {
			throw new UsernameNotFoundException("not found!");
		}
		return userAccount.get();
	}

	public UserAccountEntity getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			User user = (User) auth.getPrincipal();
			UserAccountEntity userAccount = getUserAccountByEmail(user.getUsername());
			return userAccount;
		}
		return null;
	}
}