package com.saltside.test.service;

import com.saltside.test.entity.Bird;
import com.saltside.test.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service for save, find, findall and delete.
 * Created by krsna on 03/06/2017.
 */
@Service
public class BirdService {


    @Autowired
    private BirdRepository birdServiceRepository;

    @Transactional
    public Bird save(Bird bird) {
        if (bird.isVisible() == null) {
            bird.setVisible(false);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        bird.setAdded(simpleDateFormat.format(date));
        return birdServiceRepository.save(bird);
    }

    public Iterable<String> findAll() {
        List<String> bordList = new ArrayList<String>();
        Collection<Bird> birdCollection = (Collection<Bird>) birdServiceRepository.findAll();
        for (Bird bird : birdCollection) {
            if (bird.isVisible()) {
                bordList.add(bird.id);
            }
        }
        return bordList;
    }

    public Bird findById(String id) {
        return birdServiceRepository.findById(id);
    }

    public void delete(String id) {
        birdServiceRepository.delete(birdServiceRepository.findById(id));
    }

}
