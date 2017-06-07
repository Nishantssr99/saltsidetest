package com.saltside.test.controller;

import com.saltside.test.entity.Added;
import com.saltside.test.entity.Bird;
import com.saltside.test.propertyvalidation.PropertiesValidator;
import com.saltside.test.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.saltside.test.propertyvalidation.PropertiesValidator.validate;

/**
 * Created by krsna on 03/06/2017.
 */
@RestController
public class BirdController {

    @Autowired
    private BirdService birdService;


    @RequestMapping(value = "/birds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Bird>> getBirds() {
        Collection<Bird> birdCollection = (Collection<Bird>) birdService.findAll();
        return new ResponseEntity<>(birdCollection, HttpStatus.OK);
    }

    @RequestMapping(value = "/birds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bird> createBird(@RequestBody @Validated Bird bird) {
        Added added = new Added();
        added.setType("string");
        added.setDescription("Date the bird was added to the registry." + Instant.now().toString());
        bird.getProperties().setAdded(added);
        validate(bird);
        Bird newBird = birdService.save(bird);
        return new ResponseEntity<>(newBird, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/birds/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bird> getBird(@PathVariable("id") int birdId) {
        Bird bird = birdService.findBybirdId(birdId);
        if (bird == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bird, HttpStatus.OK);
    }

    @RequestMapping(value = "/birds/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bird> deleteBird(@PathVariable("id") int birdId) {
        Bird bird = birdService.findBybirdId(birdId);
        if (bird == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        birdService.delete(birdId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test() {
        throw new ConstraintViolationException("error", Collections.emptySet());
    }

}
