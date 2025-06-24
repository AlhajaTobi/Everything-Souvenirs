package com.skillnest.everythingsouvneirs.data.repository;

import com.skillnest.everythingsouvneirs.data.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, String> {
}
