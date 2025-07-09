package com.skillnest.everythingsouvneirs.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients
                .create("mongodb+srv://oladimejivictor611:NYSh0JeKocC4Tmkl@everything-souvneirs.sijhpkl.mongodb.net/everything-souvenirs?retryWrites=true&w=majority");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "everything-souvenirs");
    }
}
