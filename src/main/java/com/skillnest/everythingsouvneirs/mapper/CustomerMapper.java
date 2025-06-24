package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Customer;
import com.skillnest.everythingsouvneirs.data.model.User;
import com.skillnest.everythingsouvneirs.dtos.request.CreateCustomerRequest;
import com.skillnest.everythingsouvneirs.dtos.response.CustomerResponse;

public class CustomerMapper {

    public static Customer mapToCustomer(User user, CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setId(user.getId());
        customer.setEmail(user.getEmail());
        customer.setPassword(user.getPassword());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setUsername(request.getUsername());
        return customer;
    }

    public static CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .user(customer)
                .fullName(customer.getUsername())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
