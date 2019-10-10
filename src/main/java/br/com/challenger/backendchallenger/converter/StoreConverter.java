package br.com.challenger.backendchallenger.converter;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;

public final class StoreConverter {

    private StoreConverter() {}

    public static Store convert(StoreDTO storeDTO) {
        if(storeDTO != null) {
            Store store = new Store();
            store.setId(storeDTO.getId());
            store.setName(storeDTO.getName());

            return store;
        }

        return null;
    }

    public static StoreDTO convert(Store store) {
        if(store != null) {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setId(store.getId());
            storeDTO.setName(store.getName());

            return storeDTO;
        }

        return null;
    }
}
