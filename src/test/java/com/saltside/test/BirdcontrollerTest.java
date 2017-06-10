package com.saltside.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.saltside.test.entity.*;
import com.saltside.test.repository.BirdRepository;
import com.saltside.test.service.BirdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;

/**
 * Test class for controller
 * Created by krsna on 04/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BirdcontrollerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @Autowired
    private BirdService birdService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    /**
     * test method for creating a bird
     */
    @Test
    public void createBirds() throws Exception {
        String birdJson = json(createBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isCreated());
    }
    /**
     * test method for creating a bad input bird
     */
    @Test
    public void createBadNameBirds() throws Exception {
        String birdJson = json(createBadNameBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isBadRequest());
    }
    /**
     * test method for creating a bad input bird
     */
    @Test
    public void createBadFamilyBirds() throws Exception {
        String birdJson = json(creatBadFamilyBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isBadRequest());
    }
    /**
     * test method for creating a bad input bird
     */
    @Test
    public void createBadContinentsBirds() throws Exception {
        String birdJson = json(createBadContinentBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isBadRequest());
    }
    /**
     * test method for getting birds
     */
    @Test
    public void getBirds() throws Exception {
        String birdJson = json(new Bird());
        this.mockMvc.perform(get("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isOk());
    }
    /**
     * test method for getting one bird  on input parameter
     */
    @Test
    public void getOneBird() throws Exception {
        String birdJson = json(createBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isCreated());
        Iterable<String> birdList = birdService.findAll();
        String visibleBird = birdList.iterator().next();
        this.mockMvc.perform(get("/" + "birds" + "/" + visibleBird)
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isOk());
    }
    /**
     * test method for deleting one bird  on input parameter
     */
    @Test
    public void deleteOneBird() throws Exception {
        String birdJson = json(createBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isCreated());
        Iterable<String> birdList = birdService.findAll();
        String visibleBird = birdList.iterator().next();
        this.mockMvc.perform(delete("/" + "birds" + "/" + visibleBird)
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    /**
     * create a new Bird obj
     */
    public Bird createBirdObj() {
        Bird bird = new Bird();
        bird.setName("Peacock");
        bird.setFamily("Bird");
        String[] continents = {"Europe", "Asia"};
        bird.setContinents(continents);
        bird.setAdded("10-10-2017");
        bird.setVisible(true);
        return bird;
    }
    /**
     * create a new Bird obj without name attribute
     */
    public Bird createBadNameBirdObj() {
        Bird bird = new Bird();
        bird.setFamily("Bird");
        String[] continents = {"Europe", "Asia"};
        bird.setContinents(continents);
        bird.setAdded("10-10-2017");
        bird.setVisible(true);
        return bird;
    }
    /**
     * create a new Bird obj without family attribute
     */
    public Bird creatBadFamilyBirdObj() {
        Bird bird = new Bird();
        bird.setName("Peacock");
        String[] continents = {"Europe", "Asia"};
        bird.setContinents(continents);
        bird.setAdded("10-10-2017");
        bird.setVisible(true);
        return bird;
    }
    /**
     * create a new Bird obj without continents attribute
     */
    public Bird createBadContinentBirdObj() {
        Bird bird = new Bird();
        bird.setName("Peacock");
        bird.setFamily("Bird");
        bird.setAdded("10-10-2017");
        bird.setVisible(true);
        return bird;
    }

}
