package br.com.challenger.backendchallenger.converter;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;

public final class StoreConverter {

    private StoreConverter() {}

    public static Store convert(StoreDTO storeDTO) {

        Store store = new Store();
        store.setId(storeDTO.getId());
        store.setName(storeDTO.getName());

        return store;
    }
}
