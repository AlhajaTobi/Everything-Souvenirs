package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.ProductRequest;
import com.skillnest.everythingsouvneirs.dtos.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(String id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(String id, ProductRequest request);

    void deleteProduct(String id);
}
