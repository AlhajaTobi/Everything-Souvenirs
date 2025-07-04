package com.skillnest.everythingsouvneirs.service;


import com.skillnest.everythingsouvneirs.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllActiveProducts();
    Optional<Product> getProductById(String id);
    Optional<Product> getProductBySlug(String slug);
    List<Product> getProductsByCategory(String category);
    List<Product> getPopularProducts();
    List<Product> searchProducts(String searchTerm);
    List<Product> getProductsByPriceRange(Integer minPrice, Integer maxPrice);
    Page<Product> getProductsPaginated(Pageable pageable);
    Product updateProduct(String id, Product updatedProduct);
    boolean deleteProduct(String id);
    long getProductCountByCategory(String category);
}
