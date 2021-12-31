package vn.edu.hcmus.hornsneaker.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.transform.ResultTransformer;

//import java.math.BigInteger;
//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
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
		if (i >= list.size()) i = list.size()-1;
		if (i < 0) return null;
		return list.subList(0, i);
	}
	
	public void addProduct(ProductEntity product, ArrayList<ProductSizesEntity> sizeList) {
		productRepo.save(product);
		List<ProductEntity> productList = productRepo.findAll();
		for (ProductEntity productEntity : productList) {
			if (productEntity.getName().equals(product.getName())) {
				for (ProductSizesEntity size : sizeList) {
					size.setProductId(productEntity.getId());
					sizesRepository.save(size);
				}
			}
		}
	}

	public void editProduct(Long id, ProductEntity obj) {
		ProductEntity product = productRepo.getById(id);
		product.setName(obj.getName());
		product.setDescription(obj.getDescription());
		product.setImage(obj.getImage());
		product.setPrice(obj.getPrice());
		productRepo.save(product);
	}

    public List<ProductEntity> search(String query) {
        List<ProductEntity> productList = productRepo.findAll(Sort.by(Direction.DESC,"views"));
		ArrayList<ProductEntity> result = new ArrayList<>();
		for (ProductEntity productEntity : productList) {
			if (productEntity.getName().toLowerCase().contains(query.toLowerCase())) {
				result.add(productEntity);
			}
		}
		return result;
    }

}