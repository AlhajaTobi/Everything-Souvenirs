package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.CreateQuoteRequest;
import com.skillnest.everythingsouvneirs.dtos.response.QuoteResponse;

import java.util.List;

public interface QuoteService {
    QuoteResponse createQuote(CreateQuoteRequest request);

    QuoteResponse getQuoteById(String id);

    List<QuoteResponse> getAllQuotes();

    QuoteResponse updateQuoteStatus(String id, String status);

    void deleteQuote(String id);
}
