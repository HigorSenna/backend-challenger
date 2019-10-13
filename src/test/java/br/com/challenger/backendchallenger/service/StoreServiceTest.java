package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//TODO: Testes que estão falhando nao estão interrompendo o build!! pq?
public class StoreServiceTest extends BaseServiceTest {

    @Autowired
    StoreService storeService;

    @Test
    void shouldInsert() throws BusinessException {
        StoreDTO storeDTO = buildStoreDTO();
        StoreDTO storeDTOSaved = this.storeService.save(storeDTO);
        Assertions.assertNotNull(storeDTOSaved);
    }


    @Test
    void shouldReturnBusinessException() throws BusinessException {
        StoreDTO storeDTO = buildStoreDTO();
        this.storeService.save(storeDTO);
        Assertions.assertThrows(BusinessException.class, () -> storeService.save(storeDTO));
    }

    @Test
    void testeFalhaParaInterromperBuild(){
        Assertions.assertNull(new StoreDTO());
    }

    private StoreDTO buildStoreDTO() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Store to test");

        return storeDTO;
    }

}