package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.AddToCartRequest;
import com.skillnest.everythingsouvneirs.dtos.response.CartResponse;

public interface CartService {
    CartResponse addToCart(AddToCartRequest request);

    CartResponse getCartByCustomerId(String customerId);

    void clearCart(String customerId);
}
