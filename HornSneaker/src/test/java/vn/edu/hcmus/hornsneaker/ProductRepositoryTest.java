package vn.edu.hcmus.hornsneaker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import vn.edu.hcmus.hornsneaker.dao.domain.ProductEntity;
import vn.edu.hcmus.hornsneaker.dao.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository repository;

    @Test
    public void testCreateProduct() {
        ProductEntity product = new ProductEntity();
        product.setName("name");
        product.setDescription("description");
        product.setImage("image");
        product.setPrice(123);

        ProductEntity saved = repository.save(product);

        ProductEntity exist = entityManager.find(ProductEntity.class, saved.getId());

        assertThat(product.getId()).isEqualTo(exist.getId());
    }
}