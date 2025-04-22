package org.example.service;

import org.example.model.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    List<Product> findAll();
    Product update(Product product);
    void delete(Long id);
}
