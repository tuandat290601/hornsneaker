package vn.edu.hcmus.hornsneaker.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		UserDetails user = User.withUsername(userAccount.getEmail()).password(userAccount.getPassword())
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

	// private Collection<? extends GrantedAuthority> getAuthorities(UserAccountEntity userAccount) {
	// 	List<GrantedAuthority> authorities = new ArrayList<>();
	// 	authorities.add(new SimpleGrantedAuthority(userAccount.getRole()));

	// 	return authorities;
	// }
}