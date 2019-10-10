package br.com.challenger.backendchallenger.controller;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@Api(value = "Stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @ApiOperation(value = "Save a storage")
    public void save(@RequestBody StoreDTO storeDTO) {
        this.storeService.save(storeDTO);
    }
}
