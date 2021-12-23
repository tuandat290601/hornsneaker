package vn.edu.hcmus.hornsneaker.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmus.hornsneaker.dao.domain.SalesEventEntity;

public interface SalesEventRepository extends JpaRepository<SalesEventEntity, Long> {
}
