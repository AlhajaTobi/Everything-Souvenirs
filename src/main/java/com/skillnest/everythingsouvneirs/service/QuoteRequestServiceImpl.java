package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.enums.QuoteStatus;
import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;
import com.skillnest.everythingsouvneirs.data.repository.QuoteRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteRequestServiceImpl implements QuoteRequestService {

    private final QuoteRequestRepository quoteRequestRepository;
    private final EmailService emailService;

    @Override
    public QuoteRequest createQuoteRequest(QuoteRequest quoteRequest) {
        QuoteRequest savedRequest = quoteRequestRepository.save(quoteRequest);

        // Send confirmation and admin notification
        emailService.sendQuoteRequestConfirmation(savedRequest);
        emailService.sendQuoteRequestNotification(savedRequest);

        return savedRequest;
    }

    @Override
    public List<QuoteRequest> getAllQuoteRequests() {
        return quoteRequestRepository.findAll();
    }

    @Override
    public Optional<QuoteRequest> getQuoteRequestById(String id) {
        return quoteRequestRepository.findById(id);
    }

    @Override
    public List<QuoteRequest> getQuoteRequestsByStatus(QuoteStatus status) {
        return quoteRequestRepository.findByStatus(status);
    }

    @Override
    public List<QuoteRequest> getQuoteRequestsByEmail(String email) {
        return quoteRequestRepository.findByEmailOrderByCreatedAtDesc(email);
    }

    @Override
    public Page<QuoteRequest> getQuoteRequestsByStatusPaginated(QuoteStatus status, Pageable pageable) {
        return quoteRequestRepository.findByStatusOrderByCreatedAtDesc(status, pageable);
    }

    @Override
    public QuoteRequest updateQuoteRequestStatus(String id, QuoteStatus status) {
        Optional<QuoteRequest> quoteRequest = quoteRequestRepository.findById(id);
        if (quoteRequest.isPresent()) {
            QuoteRequest request = quoteRequest.get();
            request.setStatus(status);
            QuoteRequest updatedRequest = quoteRequestRepository.save(request);

            emailService.sendQuoteStatusUpdate(updatedRequest);
            return updatedRequest;
        }
        return null;
    }

    @Override
    public List<QuoteRequest> getRecentQuoteRequests() {
        return quoteRequestRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public List<QuoteRequest> getQuoteRequestsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return quoteRequestRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public long getCountByStatus(QuoteStatus status) {
        return quoteRequestRepository.countByStatus(status);
    }

    @Override
    public List<QuoteRequest> searchByProduct(String product) {
        return quoteRequestRepository.findByProductContainingIgnoreCase(product);
    }

    @Override
    public boolean deleteQuoteRequest(String id) {
        if (quoteRequestRepository.existsById(id)) {
            quoteRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
