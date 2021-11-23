package vn.edu.hcmus.hornsneaker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import vn.edu.hcmus.hornsneaker.dao.domain.UserAccountEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.UserAccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserAccountRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserAccountRepository repository;

    @Test
    public void testCreateUser() {
        UserAccountEntity user = new UserAccountEntity();
        user.setEmail("mail@mail.com");
        user.setPassword("password");
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.setAddress("address");
        user.setPhone("phone");

        UserAccountEntity saved = repository.save(user);

        UserAccountEntity exist = entityManager.find(UserAccountEntity.class, saved.getId());

        assertThat(user.getEmail()).isEqualTo(exist.getEmail());
    }
}