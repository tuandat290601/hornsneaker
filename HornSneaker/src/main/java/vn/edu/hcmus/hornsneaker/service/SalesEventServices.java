package vn.edu.hcmus.hornsneaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.hcmus.hornsneaker.dao.domain.SalesEventEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.SalesEventRepository;


@Service
public class SalesEventServices {
	@Autowired
	private SalesEventRepository salesEventRepo;

	public SalesEventEntity findById(Long id) {
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

	public void add(SalesEventEntity salesEvent) {
		salesEventRepo.save(salesEvent);
    }

	public void delete(Long id) {
		salesEventRepo.deleteById(id);
	}

	@Transactional
    public void edit(Long id, SalesEventEntity salesEvent) {
		Optional<SalesEventEntity> target = salesEventRepo.findById(id);
		if (target.isPresent()) {
			SalesEventEntity entity = target.get();
			entity.setTitle(salesEvent.getTitle());
			entity.setStartDate(salesEvent.getStartDate());
			entity.setEndDate(salesEvent.getEndDate());
		}
    }
}