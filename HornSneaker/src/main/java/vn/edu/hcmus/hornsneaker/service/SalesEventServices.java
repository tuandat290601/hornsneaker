package vn.edu.hcmus.hornsneaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.SalesEventEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.SalesEventRepository;


@Service
public class SalesEventServices {
	@Autowired
	private SalesEventRepository salesEventRepo;

	public SalesEventEntity find(Long id) {
		return salesEventRepo.getById(id);		
	}

	public List<SalesEventEntity> findAll() {
		return salesEventRepo.findAll();
	}

	public List<SalesEventEntity> findAll(int i) {
		List<SalesEventEntity> list = salesEventRepo.findAll(Sort.by(Direction.ASC,"startDate"));
		if (i >= list.size()) i = list.size()-1;
		if (i < 0) return null;
		return list.subList(0, i);
	}

	// @Transactional
    // public void increaseViews(SalesEventEntity product) {
	// 	product.increaseViews();
    // }
}