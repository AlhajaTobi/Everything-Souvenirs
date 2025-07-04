package com.skillnest.everythingsouvneirs.data.repository;

import com.skillnest.everythingsouvneirs.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByIsActiveTrue();

    // Find products by category
    List<Product> findByCategoryAndIsActiveTrue(String category);

    // Find popular products
    List<Product> findByIsPopularTrueAndIsActiveTrue();

    // Find products by price range
    @Query("{'priceRange.min': {$gte: ?0}, 'priceRange.max': {$lte: ?1}, 'isActive': true}")
    List<Product> findByPriceRangeBetween(Integer minPrice, Integer maxPrice);

    // Search products by title or description
    @Query("{'$or': [{'title': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'}}], 'isActive': true}")
    List<Product> findByTitleOrDescriptionContainingIgnoreCase(String searchTerm);

    // Find by slug
    Optional<Product> findBySeoSlugAndIsActiveTrue(String slug);

    // Paginated search
    Page<Product> findByIsActiveTrue(Pageable pageable);

    // Count active products by category
    long countByCategoryAndIsActiveTrue(String category);
}
