package br.com.challenger.backendchallenger.converter;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreConverterTest {

    @Test
    void shouldConvertExactly() {
        StoreDTO storeDTO = buildStoreDTO();
        Store store = StoreConverter.convert(storeDTO);
        assertAll("attributes of converter",
                () -> assertEquals(storeDTO.getId(), store.getId()),
                () -> assertEquals(storeDTO.getName(), store.getName())
        );
    }

    @Test
    void shouldReturnNullStoreDTO() {
        StoreDTO storeDTO = null;
        Store store = StoreConverter.convert(storeDTO);
        assertNull(store);
    }

    @Test
    void shouldReturnNullStore() {
        Store store = null;
        StoreDTO storeDTO = StoreConverter.convert(store);
        assertNull(storeDTO);
    }

    private StoreDTO buildStoreDTO() {
        return new StoreDTO(1L, "StoreName");
    }
}