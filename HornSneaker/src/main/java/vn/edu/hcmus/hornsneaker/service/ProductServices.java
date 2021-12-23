package vn.edu.hcmus.hornsneaker.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

//import java.math.BigInteger;
//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductSizesEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.ProductRepository;
import vn.edu.hcmus.hornsneaker.dao.repository.ProductSizesRepository;


@Service
public class ProductServices {
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductSizesRepository sizesRepository;
	
	/*
	public ArrayList<ProductEntity> listAllProducts() {
		return productRepo.findAll();
	}
	*/

	public ProductEntity find(Long id) {
		return productRepo.getById(id);		
	}

	public ArrayList<Integer> findAllSizeOf(Long productId) {
		ArrayList<Integer> list = new ArrayList<>();

		List<ProductSizesEntity> all = sizesRepository.findAll();
		for (ProductSizesEntity productSizesEntity : all) {
			if (productSizesEntity.getProductId().equals(productId)) {
				list.add(productSizesEntity.getSize());
			}
		}

		return list;
	}

	@Transactional
    public void increaseViews(ProductEntity product) {
		product.increaseViews();
    }

	public List<ProductEntity> getHighlightedProducts(int i) {
		List<ProductEntity> list = productRepo.findAll(Sort.by(Direction.DESC,"views"));
		return list.subList(0, i);
	}
}