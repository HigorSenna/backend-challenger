package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import br.com.challenger.backendchallenger.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class StoreServiceTest extends BaseServiceTest {

    @Autowired
    private StoreService storeService;

    @Test
    void shouldInsert() throws BusinessException {
        StoreDTO storeDTOSaved = this.storeService.save(new StoreDTO(super.randomString()));
        Assertions.assertNotNull(storeDTOSaved);
    }

    @Test
    void shouldReturnBusinessExceptionWhenStoreAlreadyExists() throws BusinessException {
        StoreDTO storeDTO = new StoreDTO(super.randomString());
        this.storeService.save(storeDTO);
        Assertions.assertThrows(BusinessException.class, () -> storeService.save(storeDTO));
    }

    @Test
    void shouldUpdate() throws BusinessException {
        StoreDTO storeDtoSaved = this.storeService.save(new StoreDTO(super.randomString()));
        storeDtoSaved.setName("Another Name");

        StoreDTO storeDtoUpdated = this.storeService.update(storeDtoSaved.getId(), storeDtoSaved);
        Assertions.assertEquals(storeDtoUpdated.getName(), "Another Name");
    }

    @Test
    void shouldReturnNotFoundExceptionWhenStoreNameNotFound() throws BusinessException {
        this.storeService.save(new StoreDTO("Store 4"));
        Assertions.assertThrows(NotFoundException.class, () ->  this.storeService.find(null, "Loja458", null));
    }

    @Test
    void shouldReturnStoreByName() throws BusinessException {
        String storeName = super.randomString();
        this.storeService.save(new StoreDTO(storeName));
        Page<StoreDTO> storesDTO = this.storeService.find(null, storeName, null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(storesDTO),
                () -> Assertions.assertEquals(1, storesDTO.getContent().size()),
                () -> Assertions.assertEquals(storeName, storesDTO.stream().findFirst().get().getName())
        );
    }

    @Test
    void shouldReturnStoreById() throws BusinessException {
        StoreDTO storeDtoSaved = this.storeService.save(new StoreDTO(super.randomString()));
        Page<StoreDTO> storesDtoFound = this.storeService.find(storeDtoSaved.getId(), null, null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(storesDtoFound),
                () -> Assertions.assertEquals(1, storesDtoFound.getContent().size()),
                () -> Assertions.assertEquals(storeDtoSaved.getId(), storesDtoFound.stream().findFirst().get().getId())
        );
    }

    @Test
    void shouldReturnNotFoundExceptionWhenSearchByIdAndDoesNotExists() throws NotFoundException {
        Assertions.assertThrows(NotFoundException.class, () ->  this.storeService.find(58958L));
    }

    @Test
    void shouldReturnBusinessExceptionWhenAllParamsAreNull() {
        Assertions.assertThrows(BusinessException.class, () ->  this.storeService.find(null, null, null));
    }

    @Test
    void testeFalhaParaInterromperBuild(){
        Assertions.assertNull(new StoreDTO());
    }
}