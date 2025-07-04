package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {

    void sendQuoteRequestConfirmation(QuoteRequest quoteRequest);
    void sendQuoteRequestNotification(QuoteRequest quoteRequest);
    void sendQuoteStatusUpdate(QuoteRequest quoteRequest);
}
