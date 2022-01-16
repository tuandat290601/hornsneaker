package vn.edu.hcmus.hornsneaker.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.hcmus.hornsneaker.dao.domain.OrderDetailsEntity;
import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
	@Query("SELECT order FROM OrderDetailsEntity order WHERE order.orderId = :id")
	List<OrderDetailsEntity> findByOrderId(@Param("id") Long id);
	
	@Query("SELECT product FROM ProductEntity product, OrderDetailsEntity order WHERE order.productId = product.id AND order.orderId = :id" )
	List<ProductEntity> findProductInCart(@Param("id") Long id);
}
