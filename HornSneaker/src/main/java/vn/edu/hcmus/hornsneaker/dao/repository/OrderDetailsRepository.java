package vn.edu.hcmus.hornsneaker.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.edu.hcmus.hornsneaker.dao.domain.OrderDetailsEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
	@Query("SELECT order FROM OrderDetailsEntity order WHERE order.orderId = ?1")
	List<OrderDetailsEntity> findByOrderId(Long id);
	
}
