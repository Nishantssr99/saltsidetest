package com.saltside.test;

import com.saltside.test.controller.BirdController;
import com.saltside.test.repository.BirdRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by krsna on 07/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BirdRepositoryTest {

    @Autowired
    private BirdRepository birdRepository;

    /**
     * test whether bird repository is available or not
     * @throws Exception
     */
    @Test
    public void contexLoads() throws Exception {
        assertThat(birdRepository).isNotNull();
    }
}
