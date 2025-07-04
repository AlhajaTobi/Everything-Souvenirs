package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.QuoteRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from}")
    private String fromEmail;

    @Value("${app.email.admin}")
    private String adminEmail;

    public void sendQuoteRequestConfirmation(QuoteRequest quoteRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(quoteRequest.getEmail());
        message.setSubject("Quote Request Received - Everything Souvenirs & Gifts");

        String text = String.format(
                "Dear %s,\n\n" +
                        "Thank you for your quote request. We have received your inquiry for %s.\n\n" +
                        "Request Details:\n" +
                        "Product: %s\n" +
                        "Quantity: %s\n" +
                        "Company: %s\n\n" +
                        "We will review your request and get back to you within 24 hours with a detailed quote.\n\n" +
                        "Best regards,\n" +
                        "Everything Souvenirs & Gifts Team\n" +
                        "Phone: +234 705 353 1269\n" +
                        "Email: everythingsouvenirsandgifts@gmail.com",
                quoteRequest.getName(),
                quoteRequest.getProduct(),
                quoteRequest.getProduct(),
                quoteRequest.getQuantity() != null ? quoteRequest.getQuantity() : "Not specified",
                quoteRequest.getCompany() != null ? quoteRequest.getCompany() : "Not specified"
        );

        message.setText(text);
        mailSender.send(message);
    }

    public void sendQuoteRequestNotification(QuoteRequest quoteRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(adminEmail);
        message.setSubject("New Quote Request Received");

        String text = String.format(
                "A new quote request has been received:\n\n" +
                        "Customer: %s\n" +
                        "Email: %s\n" +
                        "Phone: %s\n" +
                        "Company: %s\n" +
                        "Product: %s\n" +
                        "Quantity: %s\n" +
                        "Message: %s\n\n" +
                        "Please review and respond within 24 hours.",
                quoteRequest.getName(),
                quoteRequest.getEmail(),
                quoteRequest.getPhone() != null ? quoteRequest.getPhone() : "Not provided",
                quoteRequest.getCompany() != null ? quoteRequest.getCompany() : "Not provided",
                quoteRequest.getProduct(),
                quoteRequest.getQuantity() != null ? quoteRequest.getQuantity() : "Not specified",
                quoteRequest.getMessage() != null ? quoteRequest.getMessage() : "No additional message"
        );

        message.setText(text);
        mailSender.send(message);
    }

    public void sendQuoteStatusUpdate(QuoteRequest quoteRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(quoteRequest.getEmail());
        message.setSubject("Quote Request Status Update - Everything Souvenirs & Gifts");

        String text = String.format(
                "Dear %s,\n\n" +
                        "Your quote request status has been updated to: %s\n\n" +
                        "Product: %s\n" +
                        "Request ID: %s\n\n" +
                        "If you have any questions, please don't hesitate to contact us.\n\n" +
                        "Best regards,\n" +
                        "Everything Souvenirs & Gifts Team\n" +
                        "Phone: +234 705 353 1269\n" +
                        "Email: everythingsouvenirsandgifts@gmail.com",
                quoteRequest.getName(),
                quoteRequest.getStatus().toString(),
                quoteRequest.getProduct(),
                quoteRequest.getId()
        );

        message.setText(text);
        mailSender.send(message);
    }
}