package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Cart;
import com.skillnest.everythingsouvneirs.data.model.Customer;
import com.skillnest.everythingsouvneirs.data.model.Item;
import com.skillnest.everythingsouvneirs.data.model.Product;
import com.skillnest.everythingsouvneirs.data.repository.CartRepository;
import com.skillnest.everythingsouvneirs.data.repository.CustomerRepository;
import com.skillnest.everythingsouvneirs.data.repository.ProductRepository;
import com.skillnest.everythingsouvneirs.dtos.request.AddToCartRequest;
import com.skillnest.everythingsouvneirs.dtos.response.CartResponse;
import com.skillnest.everythingsouvneirs.exception.CartNotFoundException;
import com.skillnest.everythingsouvneirs.exception.CustomerNotFoundException;
import com.skillnest.everythingsouvneirs.exception.ProductNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse addToCart(AddToCartRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart.setItems(new ArrayList<>());
        }

        Item item = new Item();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        cart.getItems().add(item);

        cartRepository.save(cart);

        return CartMapper.mapToCartResponse(cart);
    }

    @Override
    public CartResponse getCartByCustomerId(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Cart cart = customer.getCart();
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }

        return CartMapper.mapToCartResponse(cart);
    }

    @Override
    public void clearCart(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Cart cart = customer.getCart();
        if (cart != null) {
            cart.getItems().clear();
            cartRepository.save(cart);
        }
    }
}
