package vn.edu.hcmus.hornsneaker.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmus.hornsneaker.dao.domain.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
