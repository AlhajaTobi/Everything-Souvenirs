package com.skillnest.everythingsouvneirs.data.repository;

import com.skillnest.everythingsouvneirs.data.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {
}
