package com.saltside.test.repository;

import com.saltside.test.entity.Bird;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * repository class for Bird service
 * Created by krsna on 03/06/2017.
 */
public interface BirdRepository extends MongoRepository<Bird, String> {
    public Bird findById(String id);
}
