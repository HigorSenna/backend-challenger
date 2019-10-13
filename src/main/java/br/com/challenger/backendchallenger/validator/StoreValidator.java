package br.com.challenger.backendchallenger.validator;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public final class StoreValidator {

    private StoreValidator() {}

    public static void fieldsValidation(StoreDTO storeDTO) throws BusinessException {
        if(StringUtils.isBlank(storeDTO.getName()) && storeDTO.getId() == null) {
            String message = String.format("Campo '%s' ou '%s' deve ser informado", "Nome", "Id");
            throw new BusinessException(message);
        }
    }
}
