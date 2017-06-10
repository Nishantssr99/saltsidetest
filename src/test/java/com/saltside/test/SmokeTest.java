package com.saltside.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.saltside.test.controller.BirdController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * Created by krsna on 04/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {

    @Autowired
    private BirdController controller;
    /**
     * test whether bird controller is available or not
     * @throws Exception
     */
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}