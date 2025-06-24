package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateQuoteRequest {
    private String status;
    private List<CreateItemRequest> items;
}
