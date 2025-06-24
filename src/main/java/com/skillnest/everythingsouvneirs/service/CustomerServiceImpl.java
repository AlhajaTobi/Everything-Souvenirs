package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Cart;
import com.skillnest.everythingsouvneirs.data.model.Customer;
import com.skillnest.everythingsouvneirs.data.model.User;
import com.skillnest.everythingsouvneirs.data.repository.CustomerRepository;
import com.skillnest.everythingsouvneirs.data.repository.UserRepository;
import com.skillnest.everythingsouvneirs.dtos.request.CreateCustomerRequest;
import com.skillnest.everythingsouvneirs.dtos.response.CustomerResponse;
import com.skillnest.everythingsouvneirs.exception.CustomerNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new CustomerNotFoundException("User not found");
        }
        Customer customer = CustomerMapper.mapToCustomer(user.get(),request);
        customer.setRegistrationDate(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setActive(true);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return CustomerMapper.mapToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::mapToCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(String id, CreateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        customer.setUsername(request.getUsername());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());

        return CustomerMapper.mapToCustomerResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(String id) {
        if (!userRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
