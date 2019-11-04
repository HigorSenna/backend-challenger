package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.dto.ResponseDTO;
import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.pagination.RestPageImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
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

        ResponseDTO responseDTO = super.getResponseObject(mvcResult, ResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseDTO),
                () -> assertEquals(HttpStatus.PRECONDITION_FAILED.value(), getStatus(mvcResult))
        );
    }

    @Test
    public void shouldUpdateStore() throws Exception {
        StoreDTO storeDtoCreated = this.createRandomStore();
        storeDtoCreated.setName("StoreUpdated");
        String requestBodyToUpdate = super.convertToJson(storeDtoCreated);

        MvcResult mvcResult = super.sendPut(requestBodyToUpdate, getPutUrl(storeDtoCreated.getId()));
        StoreDTO storeDtoUpdate = super.getResponseObject(mvcResult,StoreDTO.class);

        assertAll(
                () -> assertNotNull(storeDtoUpdate),
                () -> assertEquals(storeDtoCreated.getName(), storeDtoUpdate.getName()),
                () -> assertEquals(HttpStatus.OK.value(), super.getStatus(mvcResult))
        );
    }

    @Test
    public void shouldNotUpdateStoreWhenNotHasId() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        MvcResult mvcResult = super.sendPut(requestBody, getPutUrl(null));

        assertEquals(HttpStatus.BAD_REQUEST.value(), getStatus(mvcResult));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnThePageableStoreByName() throws Exception {
        StoreDTO storeDtoCreated = this.createRandomStore();
        String path = STORE_PATH.concat("?").concat("name=").concat(storeDtoCreated.getName());
        MvcResult mvcResult = super.sendGet(path);
        Page<StoreDTO> storeDtoFoundPage = super.getPage(mvcResult, getTypeReference());
        StoreDTO storeDtoFound = storeDtoFoundPage.stream().findFirst().get();
        assertAll(
                () -> assertNotNull(storeDtoFoundPage),
                () -> assertEquals(storeDtoCreated.getName(), storeDtoFound.getName()),
                () -> assertEquals(storeDtoCreated.getId(), storeDtoFound.getId()),
                () -> assertEquals(HttpStatus.OK.value(), super.getStatus(mvcResult))
        );
    }

    @Test
    public void shouldReturnThePageableStoreById() throws Exception {
        StoreDTO storeDtoCreated = this.createRandomStore();
        String path = STORE_PATH.concat("?").concat("id=").concat(String.valueOf(storeDtoCreated.getId()));
        MvcResult mvcResult = super.sendGet(path);
        Page<StoreDTO> storeDtoFoundPage = super.getPage(mvcResult, getTypeReference());
        StoreDTO storeDtoFound = storeDtoFoundPage.stream().findFirst().get();

        assertAll(
                () -> assertNotNull(storeDtoFoundPage),
                () -> assertNotNull(storeDtoFound),
                () -> assertEquals(storeDtoCreated.getName(), storeDtoFound.getName()),
                () -> assertEquals(storeDtoCreated.getId(), storeDtoFound.getId()),
                () -> assertEquals(HttpStatus.OK.value(), super.getStatus(mvcResult))
        );
    }

    private TypeReference<RestPageImpl<StoreDTO>> getTypeReference() {
        return new TypeReference<RestPageImpl<StoreDTO>>() {
        };
    }

    @Test
    public void shouldReturnNotFoundExceptionWhenSearchByIdAndDoesNotExists() throws Exception {
        Long storeNotValidId = 5568L;
        String path = STORE_PATH.concat("?").concat("id=").concat(String.valueOf(storeNotValidId));
        MvcResult mvcResult = super.sendGet(path);
        ResponseDTO responseDTO = super.getResponseObject(mvcResult, ResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseDTO),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), super.getStatus(mvcResult))
        );
    }

    @Test
    public void shouldNotFoundStatusWhenStoreNameNotFound() throws Exception {
        String storeNotValidName = "NotFoundName";
        String path = STORE_PATH.concat("?").concat("name=").concat(storeNotValidName);
        MvcResult mvcResult = super.sendGet(path);
        ResponseDTO responseDTO = super.getResponseObject(mvcResult, ResponseDTO.class);
        assertAll(
                () -> assertNotNull(responseDTO),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), super.getStatus(mvcResult))
        );
    }

    @Test
    public void shouldReturnAllStoresPageableWhenAllParamsGetAreNull() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        super.sendPost(requestBody, STORE_PATH);
        MvcResult mvcResult = super.sendGet(STORE_PATH);
        Page<StoreDTO> storeDTOPage = super.getPage(mvcResult, getTypeReference());

        assertAll(
                () -> assertNotNull(storeDTOPage),
                () -> assertTrue(storeDTOPage.getContent().size() >= 1)
        );
    }

    @Test
    public void shouldReturnTheStoreById() throws Exception {
        StoreDTO storeDtoCreated = this.createRandomStore();
        String path = this.getFindOnlyUrl(storeDtoCreated.getId());
        MvcResult mvcResult = super.sendGet(path);
        StoreDTO storeDtoFound = this.getResponseObject(mvcResult, StoreDTO.class);

        assertAll(
                () -> assertNotNull(storeDtoFound),
                () -> assertEquals(storeDtoCreated.getName(), storeDtoFound.getName()),
                () -> assertEquals(storeDtoCreated.getId(), storeDtoFound.getId()),
                () -> assertEquals(HttpStatus.OK.value(), super.getStatus(mvcResult))
        );
    }

    @Test
    public void shouldReturnNotFoundWhenStoreIdNotExists() throws Exception {
        String path = this.getFindOnlyUrl(1235L);
        MvcResult mvcResult = super.sendGet(path);
        ResponseDTO responseDTO = this.getResponseObject(mvcResult, ResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseDTO),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), responseDTO.getCode())
        );
    }

    private StoreDTO createRandomStore() throws Exception {
        String requestBody = super.convertToJson(new StoreDTO(super.randomString()));
        return super.sendPost(requestBody, STORE_PATH, StoreDTO.class);
    }

    private String getPutUrl(Long storeCreatedId) {
        String storeCreatedIdString = String.valueOf(storeCreatedId);
        return STORE_PATH.concat("/").concat(storeCreatedIdString);
    }

    private String getFindOnlyUrl(Long storeCreatedId) {
        String storeCreatedIdString = String.valueOf(storeCreatedId);
        return STORE_PATH.concat("/").concat(storeCreatedIdString);
    }
}