package com.skillnest.everythingsouvneirs.service;


import com.skillnest.everythingsouvneirs.data.enums.QuoteStatus;
import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;
import com.skillnest.everythingsouvneirs.data.repository.QuoteRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteRequestService {

    @Autowired
    private QuoteRequestRepository quoteRequestRepository;

    @Autowired
    private EmailService emailService;

    // Create new quote request
    public QuoteRequest createQuoteRequest(QuoteRequest quoteRequest) {
        QuoteRequest savedRequest = quoteRequestRepository.save(quoteRequest);

        // Send confirmation email
        emailService.sendQuoteRequestConfirmation(savedRequest);

        // Send notification to admin
        emailService.sendQuoteRequestNotification(savedRequest);

        return savedRequest;
    }

    // Get all quote requests
    public List<QuoteRequest> getAllQuoteRequests() {
        return quoteRequestRepository.findAll();
    }

    // Get quote request by ID
    public Optional<QuoteRequest> getQuoteRequestById(String id) {
        return quoteRequestRepository.findById(id);
    }

    // Get quote requests by status
    public List<QuoteRequest> getQuoteRequestsByStatus(QuoteStatus status) {
        return quoteRequestRepository.findByStatus(status);
    }

    // Get quote requests by email
    public List<QuoteRequest> getQuoteRequestsByEmail(String email) {
        return quoteRequestRepository.findByEmailOrderByCreatedAtDesc(email);
    }

    // Get paginated quote requests by status
    public Page<QuoteRequest> getQuoteRequestsByStatusPaginated(QuoteStatus status, Pageable pageable) {
        return quoteRequestRepository.findByStatusOrderByCreatedAtDesc(status, pageable);
    }

    // Update quote request status
    public QuoteRequest updateQuoteRequestStatus(String id, QuoteStatus status) {
        Optional<QuoteRequest> quoteRequest = quoteRequestRepository.findById(id);
        if (quoteRequest.isPresent()) {
            QuoteRequest request = quoteRequest.get();
            request.setStatus(status);
            QuoteRequest updatedRequest = quoteRequestRepository.save(request);

            // Send status update email
            emailService.sendQuoteStatusUpdate(updatedRequest);

            return updatedRequest;
        }
        return null;
    }

    // Get recent quote requests
    public List<QuoteRequest> getRecentQuoteRequests() {
        return quoteRequestRepository.findTop10ByOrderByCreatedAtDesc();
    }

    // Get quote requests by date range
    public List<QuoteRequest> getQuoteRequestsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return quoteRequestRepository.findByCreatedAtBetween(startDate, endDate);
    }

    // Get count by status
    public long getCountByStatus(QuoteStatus status) {
        return quoteRequestRepository.countByStatus(status);
    }

    // Search quote requests by product
    public List<QuoteRequest> searchByProduct(String product) {
        return quoteRequestRepository.findByProductContainingIgnoreCase(product);
    }

    // Delete quote request
    public boolean deleteQuoteRequest(String id) {
        if (quoteRequestRepository.existsById(id)) {
            quoteRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
