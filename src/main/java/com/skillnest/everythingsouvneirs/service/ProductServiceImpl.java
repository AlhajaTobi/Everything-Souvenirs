package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Product;
import com.skillnest.everythingsouvneirs.data.repository.ProductRepository;
import com.skillnest.everythingsouvneirs.dtos.request.ProductRequest;
import com.skillnest.everythingsouvneirs.dtos.response.ProductResponse;
import com.skillnest.everythingsouvneirs.exception.ProductNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = ProductMapper.mapToProduct(request);
        Product saved = productRepository.save(product);
        return ProductMapper.mapToProductResponse(saved);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return ProductMapper.mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setMediaList(request.getMediaList()); // Replace existing media

        return ProductMapper.mapToProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
