package com.skillnest.everythingsouvneirs.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Quote {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createdAt;
    private String status;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<Item> items;
}
