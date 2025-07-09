package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.enums.QuoteStatus;
import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuoteRequestService {
    // Create new quote request
    QuoteRequest createQuoteRequest(QuoteRequest quoteRequest);

    List<QuoteRequest> getAllQuoteRequests();

    Optional<QuoteRequest> getQuoteRequestById(String id);

    List<QuoteRequest> getQuoteRequestsByStatus(QuoteStatus status);

    List<QuoteRequest> getQuoteRequestsByEmail(String email);

    Page<QuoteRequest> getQuoteRequestsByStatusPaginated(QuoteStatus status, Pageable pageable);

    QuoteRequest updateQuoteRequestStatus(String id, QuoteStatus status);

    List<QuoteRequest> getRecentQuoteRequests();

    List<QuoteRequest> getQuoteRequestsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    long getCountByStatus(QuoteStatus status);

    List<QuoteRequest> searchByProduct(String product);

    boolean deleteQuoteRequest(String id);
}
