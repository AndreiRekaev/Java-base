package org.example.repository;

import org.example.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setName("Laptop");
        testProduct.setPrice(999.99);
        testProduct.setDesc("High-performance laptop");
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void save_ShouldPersistProduct() {
        Product savedProduct = productRepository.save(testProduct);

        assertNotNull(savedProduct.getId());
        assertEquals("Laptop", savedProduct.getName());
    }

    @Test
    void findById_ShouldReturnProduct() {
        Product savedProduct = productRepository.save(testProduct);
        Product foundProduct = productRepository.findById(savedProduct.getId());

        assertNotNull(foundProduct);
        assertEquals(savedProduct.getId(), foundProduct.getId());
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        productRepository.save(testProduct);
        List<Product> products = productRepository.findAll();

        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    @Test
    void update_ShouldModifyProduct() {
        Product savedProduct = productRepository.save(testProduct);
        savedProduct.setPrice(899.99);
        Product updatedProduct = productRepository.update(savedProduct);

        assertEquals(899.99, updatedProduct.getPrice());
    }

    @Test
    void delete_ShouldRemoveProduct() {
        Product savedProduct = productRepository.save(testProduct);
        productRepository.delete(savedProduct.getId());

        assertNull(productRepository.findById(savedProduct.getId()));
    }
}
