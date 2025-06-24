package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.CreateCustomerRequest;
import com.skillnest.everythingsouvneirs.dtos.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);

    CustomerResponse getCustomerById(String id);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(String id, CreateCustomerRequest request);

    void deleteCustomer(String id);
}
