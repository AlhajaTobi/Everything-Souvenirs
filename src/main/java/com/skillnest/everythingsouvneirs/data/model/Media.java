package com.skillnest.everythingsouvneirs.data.model;

import com.skillnest.everythingsouvneirs.data.enums.MediaType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Media {
    @Id
    private String id = UUID.randomUUID().toString();

    private String url;
    private MediaType type;

}