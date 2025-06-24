package com.skillnest.everythingsouvneirs.controller;

import com.skillnest.everythingsouvneirs.dtos.request.AddToCartRequest;
import com.skillnest.everythingsouvneirs.dtos.response.CartResponse;
import com.skillnest.everythingsouvneirs.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<CartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        return ResponseEntity.ok(cartService.addToCart(addToCartRequest));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse> getCustomerCart(@PathVariable String customerId) {
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @DeleteMapping("/{customerId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String customerId) {
        cartService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }
}
