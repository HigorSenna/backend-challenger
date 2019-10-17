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
        Assertions.assertThrows(NotFoundException.class, () -> this.storeService.find(storeDTO.getId(), null));
    }

    @Then("Insert the store")
    public void insertStore() throws BusinessException {
        StoreDTO storeDtoSaved = this.storeService.save(this.storeDTO);
        StoreDTO storeDtoFound = this.storeService.find(storeDtoSaved.getId(), null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(storeDtoSaved),
                () -> Assertions.assertNotNull(storeDtoFound),
                () -> Assertions.assertEquals(storeDtoSaved.getId(), storeDtoFound.getId()),
                () -> Assertions.assertEquals(storeDtoSaved.getName(), storeDtoFound.getName())
        );
    }
}
