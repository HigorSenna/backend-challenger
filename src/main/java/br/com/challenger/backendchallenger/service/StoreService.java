package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.converter.StoreConverter;
import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;
import br.com.challenger.backendchallenger.exception.GeneralErrorException;
import br.com.challenger.backendchallenger.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public StoreDTO save(StoreDTO storeDTO) {
        //TODO: Validate store already exists before persist (when is new store only)
        Store store = StoreConverter.convert(storeDTO);
        if (store != null) {
            return StoreConverter.convert(this.storeRepository.save(store));
        }
        //Logar
        throw new GeneralErrorException();
    }
}
