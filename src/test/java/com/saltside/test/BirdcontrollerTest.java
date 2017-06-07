package com.saltside.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.saltside.test.entity.*;
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

    @Test
    public void createBirds() throws Exception {
        String birdJson = json(createBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void getBirds() throws Exception {
        String birdJson = json(new Bird());
        this.mockMvc.perform(get("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isOk());
    }

    @Test
    public void getOneBird() throws Exception {
        String birdJson = json(new Bird());
        this.mockMvc.perform(get("/" + "birds" + "/" + 1)
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteOneBird() throws Exception {
        String birdJson = json(createBirdObj());
        this.mockMvc.perform(post("/" + "birds")
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isCreated());
        this.mockMvc.perform(delete("/" + "birds" + "/" + 1)
                .contentType(contentType)
                .content(birdJson))
                .andExpect(status().isNoContent());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    public Bird createBirdObj() {
        Bird bird = new Bird();
        bird.setTitle("POST /birds [request]");
        bird.setDescription("Add a new bird to the library");
        bird.setDescription("object");
        bird.setBirdId(1);
        Properties properties = new Properties();
        Name name = new Name();
        name.setType("string");
        name.setDescription("English bird name");
        Added added = new Added();
        added.setType("string");
        added.setDescription("Date the bird was added to the registry. Format YYYY-MM-DD");
        Visible visible = new Visible();
        visible.setType("boolean");
        visible.setDescription("Determines if the bird should be visible in lists");
        Family family = new Family();
        family.setDescription("Latin bird family name");
        Continents continents = new Continents();
        continents.setDescription("Continents the bird exist on");
        continents.setMinItems(1);
        Items items = new Items();
        items.setType("string");
        continents.setItems(items);
        family.setType("string");

        properties.setName(name);
        properties.setFamily(family);
        properties.setContinents(continents);
        properties.setAdded(added);
        properties.setVisible(visible);
        bird.setProperties(properties);
        return bird;
    }

}
