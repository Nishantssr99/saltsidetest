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
@SpringBootTest
public class BirdRepositoryTest {

    @Autowired
    private BirdRepository birdRepository;

    @Test
    public void contexLoads() throws Exception {
        assertThat(birdRepository).isNotNull();
    }
}
