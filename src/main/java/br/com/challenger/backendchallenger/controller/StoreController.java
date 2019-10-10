package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import br.com.challenger.backendchallenger.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@Api(value = "Stores")
public class StoreController {

    private static final String OPERATION_NOT_ALLOWED = "Operacao não permitida";

    @Autowired
    private StoreService storeService;

    //TODO: Cai em um problema...
    //StoreDTO é usado tanto para salvar um novo quanto para atualizar..
    // propriedade id é obrigatória somente no update..
    // Criar um DTO para update e outro para save?
    // Documentação é diferente para os casos
    // Criar documentação na mao?? pois usando o codeGen do swagger creio que devo criar dois DTOS...

    @PostMapping
    @ApiOperation(value = "Save a storage", response = StoreDTO.class)
    public ResponseEntity<StoreDTO> save(@RequestBody StoreDTO storeDTO) throws BusinessException {

        if(storeDTO.getId() == null) {
            StoreDTO storeDToCreated = this.storeService.save(storeDTO);
            return new ResponseEntity<>(storeDToCreated, HttpStatus.CREATED);
        }
        //logar -> save é aceito apenas para objetos que nao possuam ID
        throw new BusinessException(OPERATION_NOT_ALLOWED);
    }

    @PutMapping
    @ApiOperation(value = "Update a storage", response = StoreDTO.class)
    public ResponseEntity<StoreDTO> update(@RequestBody StoreDTO storeDTO) throws BusinessException {

        if(storeDTO.getId() != null) {
            StoreDTO storeDToCreated = this.storeService.save(storeDTO);
            return new ResponseEntity<>(storeDToCreated, HttpStatus.OK);
        }
        //logar -> update é aceito apenas para objetos que possuam ID
        throw new BusinessException(OPERATION_NOT_ALLOWED);
    }
}
