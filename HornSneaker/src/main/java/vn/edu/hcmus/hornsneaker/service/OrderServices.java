package vn.edu.hcmus.hornsneaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.hcmus.hornsneaker.dao.domain.OrderEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.OrderRepository;


@Service
public class OrderServices {
	@Autowired
	private OrderRepository newsRepo;

	public OrderEntity findById(Long id) {
		return newsRepo.getById(id);		
	}

	public List<OrderEntity> findAll() {
		return newsRepo.findAll();
	}

	public List<OrderEntity> findAll(int i) {
		List<OrderEntity> list = newsRepo.findAll(Sort.by(Direction.ASC,"created"));
		if (i >= list.size()) i = list.size();
		if (i < 0) return null;
		return list.subList(0, i);
	}

    public void add(OrderEntity news) {
		newsRepo.save(news);
    }

	public void delete(Long id) {
		newsRepo.deleteById(id);
	}
}