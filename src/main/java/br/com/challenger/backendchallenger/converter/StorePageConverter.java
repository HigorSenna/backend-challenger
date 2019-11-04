package br.com.challenger.backendchallenger.converter;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;
import org.springframework.data.domain.Page;

public class StorePageConverter {

    public static Page<StoreDTO> convert(Page<Store> page) {
        return page.map(StoreConverter::convert);
    }
}
