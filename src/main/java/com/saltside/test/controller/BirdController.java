package com.saltside.test.controller;

import com.saltside.test.entity.Bird;
import com.saltside.test.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.Collections;

import static com.saltside.test.propertyvalidation.PropertiesValidator.validate;

/**
 * Rest controller class for mapping all the rest endpoints.
 * Created by krsna on 03/06/2017.
 */
@RestController
public class BirdController {

    @Autowired
    private BirdService birdService;

    /**
     * List all the bird with attribute "visible" set to true.
     * Request mapped to get
     * @return
     */
    @RequestMapping(value = "/birds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<String>> getBirds() {
        return new ResponseEntity<>((Collection<String>) birdService.findAll(), HttpStatus.OK);
    }

    /**
     * Create and save bird, if the bird attribute "visible" is not set, set it too default
     * add today's date in UTC timezone
     * @param bird
     * @return
     */

    @RequestMapping(value = "/birds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bird> createBird(@RequestBody @Validated Bird bird) {
        validate(bird);
        Bird newBird = birdService.save(bird);
        return new ResponseEntity<>(newBird, HttpStatus.CREATED);
    }
    /**
     * get bird by id, returns 200 OK if the bird exists else return 404 Not found.
     * @return
     */
    @RequestMapping(value = "/birds/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bird> getBird(@PathVariable("id") String id) {
        Bird bird = birdService.findById(id);
        if (bird == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!bird.isVisible()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(bird, HttpStatus.OK);
    }

    /**
     * delete birds by id, return 200 OK, if the has been removed and 404 Not found if the bird didn't exist
     * @param birdId
     * @return
     */
    @RequestMapping(value = "/birds/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bird> deleteBird(@PathVariable("id") String birdId) {
        Bird bird = birdService.findById(birdId);
        if (bird == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        birdService.delete(birdId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test() {
        throw new ConstraintViolationException("error", Collections.emptySet());
    }

}
