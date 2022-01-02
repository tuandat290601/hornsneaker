package vn.edu.hcmus.hornsneaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.hcmus.hornsneaker.dao.domain.NewsEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.NewsRepository;


@Service
public class NewsServices {
	@Autowired
	private NewsRepository newsRepo;

	public NewsEntity findById(Long id) {
		return newsRepo.getById(id);		
	}

	public List<NewsEntity> findAll() {
		return newsRepo.findAll(Sort.by(Direction.ASC,"created"));
	}

	public List<NewsEntity> findAll(int i) {
		List<NewsEntity> list = newsRepo.findAll(Sort.by(Direction.ASC,"created"));
		if (i >= list.size()) i = list.size();
		if (i < 0) return null;
		return list.subList(0, i);
	}

    public void add(NewsEntity news) {
		newsRepo.save(news);
    }

	public void delete(Long id) {
		newsRepo.deleteById(id);
	}

	@Transactional
    public void edit(Long id, NewsEntity news) {
		Optional<NewsEntity> target = newsRepo.findById(id);
		if (target.isPresent()) {
			NewsEntity entity = target.get();
			entity.setTitle(news.getTitle());
			entity.setContent(news.getContent());
			entity.setImage(news.getImage());
		}
    }
}