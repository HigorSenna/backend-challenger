package br.com.challenger.backendchallenger.bdd.steps;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.exception.BusinessException;
import br.com.challenger.backendchallenger.exception.NotFoundException;
import br.com.challenger.backendchallenger.service.StoreService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public class StoreSteps {

    StoreDTO storeDTO;

    @Autowired
    private StoreService storeService;

    @Given("Store is new")
    public void isNewStore() {
        storeDTO = new StoreDTO();
        Assertions.assertNull(storeDTO.getId());
    }

    @And("The store doesn't exists in database")
    public void storeDoesNotExistsInDatabase() {
        Assertions.assertThrows(NotFoundException.class, () -> this.storeService.find(storeDTO.getId(), null, null));
    }

    @Then("Insert the store")
    public void insertStore() throws BusinessException {
        StoreDTO storeDtoSaved = this.storeService.save(this.storeDTO);
        Page<StoreDTO> storesDtoFound = this.storeService.find(storeDtoSaved.getId(), null, null);
        Optional<StoreDTO> storeDtoFoundOptional = storesDtoFound.getContent().stream().findFirst();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(storeDtoSaved),
                () -> Assertions.assertNotNull(storesDtoFound),
                () -> Assertions.assertFalse(storesDtoFound.isEmpty()),
                () -> Assertions.assertEquals(1, storesDtoFound.getContent().size()),
                () -> Assertions.assertEquals(storeDtoSaved.getId(), storeDtoFoundOptional.get().getId()),
                () -> Assertions.assertEquals(storeDtoSaved.getName(), storeDtoFoundOptional.get().getName())
        );
    }
}
