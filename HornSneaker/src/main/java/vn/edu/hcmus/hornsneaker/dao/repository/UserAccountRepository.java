package vn.edu.hcmus.hornsneaker.dao.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;


public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
	Optional<UserAccountEntity> findByEmail(String Email);
}
