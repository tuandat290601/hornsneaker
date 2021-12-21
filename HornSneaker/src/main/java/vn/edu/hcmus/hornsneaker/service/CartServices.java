package vn.edu.hcmus.hornsneaker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.CartEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.CartEntry;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.CartRepository;

@Service
public class CartServices {
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductServices productServices;

	public void add(Long userId, Long productId) {
		CartEntity entry = new CartEntity(userId, productId, 1);
		cartRepo.save(entry);
	}

	public ArrayList<CartEntry> findAllOfUser(Long userId) {
		List<CartEntity> all = cartRepo.findAll(Sort.by("id"));;		
		ArrayList<CartEntry> list = new ArrayList<>();
		for (CartEntity cartEntity : all) {
			if (cartEntity.getUserId().equals(userId)) {
				ProductEntity product = productServices.find(cartEntity.getProductId());
				list.add(new CartEntry(cartEntity.getId(), product, cartEntity.getAmount()));
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
}