package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;
import br.com.challenger.backendchallenger.exception.GeneralErrorException;
import br.com.challenger.backendchallenger.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreService storeService;

    @Test
    void shouldReturnException() {
        StoreDTO storeDTO = null;
        Assertions.assertThrows(GeneralErrorException.class, () -> storeService.save(storeDTO));
    }

    @Test
    void shouldInsert() {
        StoreDTO storeDTO = buildStoreDTO();
        Mockito.when(storeRepository.save(Mockito.any(Store.class))).thenReturn(new Store());
        storeService.save(storeDTO);
    }

    private StoreDTO buildStoreDTO() {
        return new StoreDTO(1L, "StoreName");
    }

}