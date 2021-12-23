package vn.edu.hcmus.hornsneaker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.CartEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.CartEntry;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.CartRepository;

@Service
public class CartServices {
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductServices productServices;

	@Autowired
	private UserAccountServices userAccountServices;

	Long getLoggedUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			User user = (User) auth.getPrincipal();
			UserAccountEntity userAccount = userAccountServices.getUserAccountByEmail(user.getUsername());
			return userAccount.getId();
		}
		return -1L;
	}

	public void add(Long productId, int size) {
		CartEntity entry = new CartEntity(getLoggedUserId(), productId, size, 1);
		cartRepo.save(entry);
	}

	public ArrayList<CartEntry> findAllOfUser() {
		Long userId = getLoggedUserId();
		List<CartEntity> all = cartRepo.findAll(Sort.by("id"));;		
		ArrayList<CartEntry> list = new ArrayList<>();
		for (CartEntity cartEntity : all) {
			if (cartEntity.getUserId().equals(userId)) {
				ProductEntity product = productServices.find(cartEntity.getProductId());
				list.add(new CartEntry(cartEntity.getId(), product, cartEntity.getSize(), cartEntity.getAmount()));
			}
		}
		return list;
	}

	public void remove(Long cartEntryId) {
		cartRepo.deleteById(cartEntryId);
	}

	@Transactional
    public void adjustAmount(Long id, int adj) {
		Optional<CartEntity> cartEntity = cartRepo.findById(id);
		if (cartEntity.isPresent()) {
			CartEntity entity = cartEntity.get();
			int amount = entity.getAmount() + adj;
			entity.setAmount(amount);
		}
    }

    public Boolean findByProductId(Long id) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setProductId(id);
		cartEntity.setUserId(getLoggedUserId());
		Long n = cartRepo.count(Example.of(cartEntity));
		if (n == 0) return false;
        return true;
    }
}