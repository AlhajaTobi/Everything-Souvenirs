package com.skillnest.everythingsouvneirs.data.repository;

import com.skillnest.everythingsouvneirs.data.enums.QuoteStatus;
import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuoteRequestRepository extends MongoRepository<QuoteRequest, String> {

    // Find by status
    List<QuoteRequest> findByStatus(QuoteStatus status);

    // Find by email
    List<QuoteRequest> findByEmailOrderByCreatedAtDesc(String email);

    // Find by date range
    List<QuoteRequest> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find by product
    List<QuoteRequest> findByProductContainingIgnoreCase(String product);

    // Paginated results
    Page<QuoteRequest> findByStatusOrderByCreatedAtDesc(QuoteStatus status, Pageable pageable);

    // Count by status
    long countByStatus(QuoteStatus status);

    // Find recent requests
    List<QuoteRequest> findTop10ByOrderByCreatedAtDesc();
}
