package vn.edu.hcmus.hornsneaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.NewsEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.NewsRepository;


@Service
public class NewsServices {
	@Autowired
	private NewsRepository newsRepo;

	public NewsEntity find(Long id) {
		return newsRepo.getById(id);		
	}

	public List<NewsEntity> findAll() {
		return newsRepo.findAll(Sort.by(Direction.DESC,"created"));
	}

	public List<NewsEntity> findAll(int i) {
		List<NewsEntity> list = newsRepo.findAll(Sort.by(Direction.DESC,"created"));
		return list.subList(0, i);
	}

	// @Transactional
    // public void increaseViews(NewsEntity product) {
	// 	product.increaseViews();
    // }
}