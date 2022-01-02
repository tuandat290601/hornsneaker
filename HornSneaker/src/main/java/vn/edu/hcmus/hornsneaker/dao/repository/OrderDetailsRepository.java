package vn.edu.hcmus.hornsneaker.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmus.hornsneaker.dao.domain.OrderDetailsEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
	
}
