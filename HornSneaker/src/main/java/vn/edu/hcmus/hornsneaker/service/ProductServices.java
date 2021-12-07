package vn.edu.hcmus.hornsneaker.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.ProductRepository;


@Service
public class ProductServices {
	@Autowired
	private ProductRepository productRepo;
	
	public ArrayList<ProductEntity> listAllProducts() {
		return productRepo.findAll();
	}
}