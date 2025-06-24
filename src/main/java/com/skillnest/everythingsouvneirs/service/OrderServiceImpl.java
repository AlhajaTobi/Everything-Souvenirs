package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.*;
import com.skillnest.everythingsouvneirs.data.repository.*;
import com.skillnest.everythingsouvneirs.dtos.request.CreateOrderRequest;
import com.skillnest.everythingsouvneirs.dtos.response.OrderResponse;
import com.skillnest.everythingsouvneirs.exception.CustomerNotFoundException;
import com.skillnest.everythingsouvneirs.exception.OrderNotFoundException;
import com.skillnest.everythingsouvneirs.exception.ProductNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<Item> items = request.getItems().stream().map(itemReq -> {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            return item;
        }).collect(Collectors.toList());

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        // Link items to order
        items.forEach(item -> item.setOrder(order));
        order.setItems(items);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.mapToOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return OrderMapper.mapToOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderResponse)
                .collect(Collectors.toList());
    }
}
