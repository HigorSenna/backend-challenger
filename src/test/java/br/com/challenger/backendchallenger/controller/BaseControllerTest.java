package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.BackendChallengerApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    <T> T sendGet(String url, Class<T> clazz) throws Exception {
        MvcResult mvcResult = this.sendGet(url);
        return getResponseObject(mvcResult, clazz);
    }

    MvcResult sendGet(String url) throws Exception {
        return this.mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON_VALUE))
                .andReturn();
    }

    <T> T sendPost(String requestBody, String url, Class<T> clazz) throws Exception {
        MvcResult mvcResult = this.sendPost(requestBody, url);
        return getResponseObject(mvcResult, clazz);
    }

    MvcResult sendPost(String requestBody, String url) throws Exception {
        return this.mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andReturn();
    }

    <T> T sendPut(String requestBody, String url, Class<T> clazz) throws Exception {
        MvcResult mvcResult = this.sendPut(requestBody, url);
        return getResponseObject(mvcResult, clazz);
    }

    MvcResult sendPut(String requestBody, String url) throws Exception {
        return this.mockMvc.perform(put(url)
                .contentType(APPLICATION_JSON_VALUE)
                .content(requestBody))
                .andReturn();
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

    @SuppressWarnings("unchecked")
     Page getPage(MvcResult mvcResult) throws IOException {
        LinkedHashMap page = getResponseObject(mvcResult, LinkedHashMap.class);
        Integer pageNumber = (Integer) page.get("number");
        Integer pageSize = (Integer) page.get("size");
        List values = (List) page.get("content");
        return new PageImpl(values, PageRequest.of(pageNumber,pageSize), values.size());
    }
}
