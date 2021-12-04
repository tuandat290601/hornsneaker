package vn.edu.hcmus.hornsneaker.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
