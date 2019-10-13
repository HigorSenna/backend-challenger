package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//TODO: Testes que estão falhando nao estão interrompendo o build!! pq?
//TODO: Resposa: versão do JUNIT 5.5.2 está com problema.., usando a 5.3.2  foi OK
public class StoreServiceTest extends BaseServiceTest {

    @Autowired
    StoreService storeService;

    @Test
    void shouldInsert() throws BusinessException {
        StoreDTO storeDTOSaved = this.storeService.save(new StoreDTO("Store 1"));
        Assertions.assertNotNull(storeDTOSaved);
    }

    @Test
    void shouldReturnBusinessException() throws BusinessException {
        StoreDTO storeDTO = new StoreDTO("Store 2");
        this.storeService.save(storeDTO);
        Assertions.assertThrows(BusinessException.class, () -> storeService.save(storeDTO));
    }

    @Test
    void shouldUpdate() throws BusinessException {
        StoreDTO storeDtoSaved = this.storeService.save(new StoreDTO("Store 3"));
        storeDtoSaved.setName("Another Name");

        StoreDTO storeDtoUpdated = this.storeService.update(storeDtoSaved.getId(), storeDtoSaved);
        Assertions.assertEquals(storeDtoUpdated.getName(), "Another Name");
    }

    @Test
    void testeFalhaParaInterromperBuild(){
        Assertions.assertNull(new StoreDTO());
    }
}