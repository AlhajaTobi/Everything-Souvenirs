package com.skillnest.everythingsouvneirs.controller;

import com.skillnest.everythingsouvneirs.dtos.request.CreateQuoteRequest;
import com.skillnest.everythingsouvneirs.dtos.response.QuoteResponse;
import com.skillnest.everythingsouvneirs.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteResponse> create(@RequestBody CreateQuoteRequest request) {
        return ResponseEntity.ok(quoteService.createQuote(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAll() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
