package br.com.challenger.backendchallenger.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Optional;


public class StoreDTO {

    @ApiModelProperty(notes = "id of store, not necessary to create")
    private Long id;

    @ApiModelProperty(required = true, notes = "name of storage")
    @NotBlank(message = "Name is mandatory")
    private String name;

    public StoreDTO() {
    }

    public StoreDTO(String name) {
        this.name = name;
    }

    public StoreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public static Optional<StoreDTO> resolve(StoreDTO storeDTO) {
        if(storeDTO != null) {
            return Optional.of(storeDTO);
        }

        return Optional.empty();
    }


    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }


    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
