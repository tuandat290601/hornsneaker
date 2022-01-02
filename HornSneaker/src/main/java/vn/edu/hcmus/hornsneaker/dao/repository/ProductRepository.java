package vn.edu.hcmus.hornsneaker.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByType(String type);
	//@Query("SELECT * FROM product")
	//ArrayList<ProductEntity> findAll();
}
