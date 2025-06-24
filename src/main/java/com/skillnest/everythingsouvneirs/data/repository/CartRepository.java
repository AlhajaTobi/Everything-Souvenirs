package com.skillnest.everythingsouvneirs.data.repository;

import com.skillnest.everythingsouvneirs.data.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
}
