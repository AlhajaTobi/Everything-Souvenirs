package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Product;
import com.skillnest.everythingsouvneirs.dtos.request.ProductRequest;
import com.skillnest.everythingsouvneirs.dtos.response.ProductResponse;

public class ProductMapper {

    public static Product mapToProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setMediaList(request.getMediaList());
        return product;
    }

    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .mediaList(product.getMediaList())
                .build();
    }
}
