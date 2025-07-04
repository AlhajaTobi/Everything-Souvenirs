package com.skillnest.everythingsouvneirs.controller;

import com.skillnest.everythingsouvneirs.data.enums.QuoteStatus;
import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;
import com.skillnest.everythingsouvneirs.service.QuoteRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotes")
@CrossOrigin(origins = "*")
public class QuoteRequestController {

    @Autowired
    private QuoteRequestService quoteRequestService;

    // Create new quote request
    @PostMapping
    public ResponseEntity<Map<String, Object>> createQuoteRequest(@Valid @RequestBody QuoteRequest quoteRequest) {
        try {
            QuoteRequest createdRequest = quoteRequestService.createQuoteRequest(quoteRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Quote request submitted successfully");
            response.put("data", createdRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to submit quote request");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Get all quote requests (admin only)
    @GetMapping
    public ResponseEntity<List<QuoteRequest>> getAllQuoteRequests(
            @RequestParam(required = false) QuoteStatus status,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String product) {

        try {
            List<QuoteRequest> requests;

            if (status != null) {
                requests = quoteRequestService.getQuoteRequestsByStatus(status);
            } else if (email != null && !email.isEmpty()) {
                requests = quoteRequestService.getQuoteRequestsByEmail(email);
            } else if (product != null && !product.isEmpty()) {
                requests = quoteRequestService.searchByProduct(product);
            } else {
                requests = quoteRequestService.getAllQuoteRequests();
            }

            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get paginated quote requests
    @GetMapping("/paginated")
    public ResponseEntity<Page<QuoteRequest>> getQuoteRequestsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) QuoteStatus status) {

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<QuoteRequest> requests;

            if (status != null) {
                requests = quoteRequestService.getQuoteRequestsByStatusPaginated(status, pageable);
            } else {
                // If no status specified, we need to create a custom paginated method
                requests = Page.empty(pageable);
            }

            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get quote request by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuoteRequest> getQuoteRequestById(@PathVariable String id) {
        Optional<QuoteRequest> request = quoteRequestService.getQuoteRequestById(id);
        return request.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update quote request status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateQuoteRequestStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> statusUpdate) {

        try {
            String statusStr = statusUpdate.get("status");
            QuoteStatus status = QuoteStatus.valueOf(statusStr.toUpperCase());

            QuoteRequest updatedRequest = quoteRequestService.updateQuoteRequestStatus(id, status);

            if (updatedRequest != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Status updated successfully");
                response.put("data", updatedRequest);

                return ResponseEntity.ok(response);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Quote request not found");

            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Invalid status value");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to update status");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get recent quote requests
    @GetMapping("/recent")
    public ResponseEntity<List<QuoteRequest>> getRecentQuoteRequests() {
        try {
            List<QuoteRequest> requests = quoteRequestService.getRecentQuoteRequests();
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get quote requests by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<QuoteRequest>> getQuoteRequestsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00", formatter);
            LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59", formatter);

            List<QuoteRequest> requests = quoteRequestService.getQuoteRequestsByDateRange(start, end);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get statistics
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getQuoteRequestStatistics() {
        try {
            Map<String, Object> stats = new HashMap<>();

            stats.put("total", quoteRequestService.getAllQuoteRequests().size());
            stats.put("pending", quoteRequestService.getCountByStatus(QuoteStatus.PENDING));
            stats.put("reviewed", quoteRequestService.getCountByStatus(QuoteStatus.REVIEWED));
            stats.put("quoted", quoteRequestService.getCountByStatus(QuoteStatus.QUOTED));
            stats.put("accepted", quoteRequestService.getCountByStatus(QuoteStatus.ACCEPTED));
            stats.put("completed", quoteRequestService.getCountByStatus(QuoteStatus.COMPLETED));
            stats.put("rejected", quoteRequestService.getCountByStatus(QuoteStatus.REJECTED));

            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete quote request
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteQuoteRequest(@PathVariable String id) {
        try {
            boolean deleted = quoteRequestService.deleteQuoteRequest(id);

            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("success", true);
                response.put("message", "Quote request deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Quote request not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Failed to delete quote request");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
