package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
class StoreControllerTest extends BaseControllerTest {

    private static final String STORE_PATH = "/stores";

    @Test
    public void shouldCreateStore() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        MvcResult mvcResult = super.sendPost(requestBody, STORE_PATH);
        StoreDTO response = super.getResponseObject(mvcResult, StoreDTO.class);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId()),
                () -> assertNotNull(response.getName()),
                () -> assertEquals(HttpStatus.CREATED.value(), getStatus(mvcResult))
        );
    }

    @Test
    public void shouldNotCreateStoreWhenHasId() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(1L, super.randomString()));
        MvcResult mvcResult = super.sendPost(requestBody, STORE_PATH);
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), getStatus(mvcResult));
    }

    @Test
    public void shouldReturnBusinessExceptionWhenStoreAlreadyExists() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        super.sendPost(requestBody, STORE_PATH);
        MvcResult mvcResult = super.sendPost(requestBody, STORE_PATH);
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), getStatus(mvcResult));
    }

    @Test
    public void shouldUpdateStore() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        StoreDTO storeDtoCreated = super.sendPost(requestBody, STORE_PATH, StoreDTO.class);
        storeDtoCreated.setName("StoreUpdated");
        String requestBodyToUpdate = super.convertToJson(storeDtoCreated);
        StoreDTO storeDtoUpdate = super.sendPut(requestBodyToUpdate, getPutUrl(storeDtoCreated.getId()), StoreDTO.class);

        assertEquals(storeDtoCreated.getName(), storeDtoUpdate.getName());
    }

    @Test
    public void shouldNotUpdateStoreWhenNotHasId() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        MvcResult mvcResult = super.sendPut(requestBody, getPutUrl(null));

        assertEquals(HttpStatus.BAD_REQUEST.value(), getStatus(mvcResult));
    }

    private String getPutUrl(Long storeCreatedId) {
        String storeCreatedIdString = String.valueOf(storeCreatedId);
        return STORE_PATH.concat("/").concat(storeCreatedIdString);
    }
}