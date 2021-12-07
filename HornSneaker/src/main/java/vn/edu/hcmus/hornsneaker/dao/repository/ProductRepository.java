package vn.edu.hcmus.hornsneaker.dao.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	//@Query("SELECT * FROM product")
	ArrayList<ProductEntity> findAll();
}
