package br.com.challenger.backendchallenger.dto;

import io.swagger.annotations.ApiModelProperty;

public class StoreDTO {

    @ApiModelProperty(notes = "id of store, not necessary to create")
    private Long id;

    @ApiModelProperty(required = true, notes = "name of storage")
    private String name;

    public StoreDTO() {
    }

    public StoreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

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
