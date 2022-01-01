package vn.edu.hcmus.hornsneaker.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmus.hornsneaker.dao.domain.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
