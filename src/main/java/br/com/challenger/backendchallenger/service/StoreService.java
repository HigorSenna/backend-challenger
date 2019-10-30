package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.converter.StoreConverter;
import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;
import br.com.challenger.backendchallenger.exception.BusinessException;
import br.com.challenger.backendchallenger.exception.NotFoundException;
import br.com.challenger.backendchallenger.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    public static final String STORE_NOT_FOUND = "Loja não encontrada!";
    @Autowired
    private StoreRepository storeRepository;

    public StoreDTO save(StoreDTO storeDTO) throws BusinessException {
        this.storeValidations(storeDTO);
        Store store = StoreConverter.convert(storeDTO);
        return StoreConverter.convert(this.storeRepository.save(store));
    }

    public StoreDTO update(Long id, StoreDTO storeDTO) throws BusinessException{
        storeDTO.setId(id);
        return this.save(storeDTO);
    }

    public Page<StoreDTO> find(Long id, String name, Pageable pageable) {
        Page<StoreDTO> storeDtoPage = this.storeRepository.find(id, name, pageable);
        if((id != null || name != null) && storeDtoPage.getContent().isEmpty()) {
            throw new NotFoundException(STORE_NOT_FOUND);
        }

        return storeDtoPage;
    }

    public StoreDTO find(Long id) {
        Store store = this.storeRepository.find(id);
        if(store != null) {
            return StoreConverter.convert(store);
        }

        throw new NotFoundException(STORE_NOT_FOUND);
    }

    private void storeValidations(StoreDTO storeDTO) throws BusinessException {
        if(storeDTO.isNew() && hasStore(storeDTO.getName())) {
            String message = String.format("Loja %s já cadastrada", storeDTO.getName().toUpperCase());
            throw new BusinessException(message);
        }
    }

    private boolean hasStore(String name) {
        return this.storeRepository.find(name) != null;
    }
}
