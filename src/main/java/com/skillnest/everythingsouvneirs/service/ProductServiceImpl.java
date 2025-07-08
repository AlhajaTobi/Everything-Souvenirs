package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Product;
import com.skillnest.everythingsouvneirs.data.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        generateSlug(product);
        return productRepository.save(product);
    }

    // Get all active products
    public List<Product> getAllActiveProducts() {
        return productRepository.findByIsActiveTrue();
    }

    // Get product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Get product by slug
    public Optional<Product> getProductBySlug(String slug) {
        return productRepository.findBySeoSlugAndIsActiveTrue(slug);
    }

    // Get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryAndIsActiveTrue(category);
    }

    // Get popular products
    public List<Product> getPopularProducts() {
        return productRepository.findByIsPopularTrueAndIsActiveTrue();
    }

    // Search products
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.findByTitleOrDescriptionContainingIgnoreCase(searchTerm);
    }

    // Get products by price range
    public List<Product> getProductsByPriceRange(Integer minPrice, Integer maxPrice) {
        return productRepository.findByPriceRangeBetween(minPrice, maxPrice);
    }

    // Get paginated products
    public Page<Product> getProductsPaginated(Pageable pageable) {
        return productRepository.findByIsActiveTrue(pageable);
    }

    // Update product
    public Product updateProduct(String id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setTitle(updatedProduct.getTitle());
            product.setCategory(updatedProduct.getCategory());
            product.setDescription(updatedProduct.getDescription());
            product.setPriceRange(updatedProduct.getPriceRange());
            product.setImages(updatedProduct.getImages());
            product.setPopular(updatedProduct.isPopular());
            product.setActive(updatedProduct.isActive());
            product.setSpecifications(updatedProduct.getSpecifications());
            product.setInventory(updatedProduct.getInventory());

            generateSlug(product);
            return productRepository.save(product);
        }
        return null;
    }

    // Delete product (soft delete)
    public boolean deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setActive(false);
            productRepository.save(p);
            return true;
        }
        return false;
    }

    // Generate slug from title
    private void generateSlug(Product product) {
        if (product.getSeo() == null) {
            product.setSeo(new com.skillnest.everythingsouvneirs.data.model.SEO());
        }

        String slug = product.getTitle()
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");

        product.getSeo().setSlug(slug);

        if (product.getSeo().getMetaTitle() == null || product.getSeo().getMetaTitle().isEmpty()) {
            product.getSeo().setMetaTitle(product.getTitle());
        }

        if (product.getSeo().getMetaDescription() == null || product.getSeo().getMetaDescription().isEmpty()) {
            String description = product.getDescription();
            if (description.length() > 160) {
                description = description.substring(0, 157) + "...";
            }
            product.getSeo().setMetaDescription(description);
        }
    }

    // Get product count by category
    public long getProductCountByCategory(String category) {
        return productRepository.countByCategoryAndIsActiveTrue(category);
    }
}
