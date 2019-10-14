package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.BackendChallengerApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@SpringBootTest(classes = { BackendChallengerApplication.class})
@ActiveProfiles("test")
@WebAppConfiguration
abstract class BaseControllerTest {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    String convertToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    <T> T convertToObject(String json, Class<T> clazz) throws JsonParseException, IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    <T> T getResponseObject(MvcResult mvcResult, Class<T> clazz) throws IOException {
        return convertToObject(mvcResult.getResponse().getContentAsString(), clazz);
    }

    int getStatus(MvcResult mvcResult) {
        return mvcResult.getResponse().getStatus();
    }

    String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }
}
