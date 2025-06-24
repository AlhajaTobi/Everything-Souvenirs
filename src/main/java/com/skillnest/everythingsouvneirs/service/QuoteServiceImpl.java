package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Quote;
import com.skillnest.everythingsouvneirs.data.repository.QuoteRepository;
import com.skillnest.everythingsouvneirs.dtos.request.CreateQuoteRequest;
import com.skillnest.everythingsouvneirs.dtos.response.QuoteResponse;
import com.skillnest.everythingsouvneirs.exception.QuoteNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.QuoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;

    @Override
    public QuoteResponse createQuote(CreateQuoteRequest request) {
        Quote quote = QuoteMapper.mapToQuote(request);
        quote.setCreatedAt(LocalDateTime.now());
        quote.getItems().forEach(item -> item.setQuote(quote));
        quoteRepository.save(quote);
        return QuoteMapper.mapToQuoteResponse(quote);
    }

    @Override
    public QuoteResponse getQuoteById(String id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("Quote not found"));
        return QuoteMapper.mapToQuoteResponse(quote);
    }

    @Override
    public List<QuoteResponse> getAllQuotes() {
        return quoteRepository.findAll().stream()
                .map(QuoteMapper::mapToQuoteResponse)
                .collect(Collectors.toList());
    }

    @Override
    public QuoteResponse updateQuoteStatus(String id, String status) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("Quote not found"));
        quote.setStatus(status);
        quoteRepository.save(quote);
        return QuoteMapper.mapToQuoteResponse(quote);
    }

    @Override
    public void deleteQuote(String id) {
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException("Quote not found");
        }
        quoteRepository.deleteById(id);
    }
}
