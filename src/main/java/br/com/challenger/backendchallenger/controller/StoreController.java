package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import br.com.challenger.backendchallenger.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stores")
@Api(value = "Stores")
public class StoreController {

    private static final String OPERATION_NOT_ALLOWED = "Operacao não permitida";

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    @ApiOperation(value = "Save a storage")
    public ResponseEntity<StoreDTO> save(@RequestBody @Valid StoreDTO storeDTO) throws BusinessException {
        if(storeDTO.getId() == null) {
            StoreDTO storeDToCreated = this.storeService.save(storeDTO);
            return new ResponseEntity<>(storeDToCreated, HttpStatus.CREATED);
        }

        throw new BusinessException(OPERATION_NOT_ALLOWED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a storage")
    public ResponseEntity<StoreDTO> update(@PathVariable("id") Long id, @RequestBody @Valid StoreDTO storeDTO) throws BusinessException {
        StoreDTO storeDToUpdated = this.storeService.update(id, storeDTO);
        return new ResponseEntity<>(storeDToUpdated, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Find pageable store(s)")
    public ResponseEntity<Page<StoreDTO>> find(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "name", required = false) String name,
                                               Pageable pageable) {

        Page<StoreDTO> storesDTO = this.storeService.find(id, name, pageable);
        return new ResponseEntity<>(storesDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a store")
    public ResponseEntity<StoreDTO> find(@PathVariable("id") Long id)  {
        StoreDTO storeDTO = this.storeService.find(id);
        return new ResponseEntity<>(storeDTO, HttpStatus.OK);
    }

    //TODO: Extrair para uma classe?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
