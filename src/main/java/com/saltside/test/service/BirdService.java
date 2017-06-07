package com.saltside.test.service;

import com.saltside.test.entity.Bird;
import com.saltside.test.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by krsna on 03/06/2017.
 */
@Service
public class BirdService {

    @Autowired
    private BirdRepository birdServiceRepository;

    @Transactional
    public Bird save(Bird bird)
    {
        return birdServiceRepository.save(bird);
    }

    public Iterable<Bird> findAll()
    {
        return birdServiceRepository.findAll();
    }

    public Bird findBybirdId(int birdId){
        return birdServiceRepository.findBybirdId(birdId);
    }

    public void delete(int birdId) {
         birdServiceRepository.delete(birdServiceRepository.findBybirdId(birdId));
    }

}
