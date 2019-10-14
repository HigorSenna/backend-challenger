package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(JUnitPlatform.class)
class StoreControllerTest extends BaseControllerTest {

    private static final String BASE_PATH = "/stores";

    @Test
    public void shouldCreateStore() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));

        MvcResult mvcResult = super.mockMvc.perform(post(BASE_PATH)
                .contentType(APPLICATION_JSON_VALUE)
                .content(super.convertToJson(requestBody)))
                .andReturn();

        StoreDTO response = super.getResponseObject(mvcResult, StoreDTO.class);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId()),
                () -> assertNotNull(response.getName()),
                () -> assertEquals(HttpStatus.CREATED.value(), getStatus(mvcResult))
        );
    }
}