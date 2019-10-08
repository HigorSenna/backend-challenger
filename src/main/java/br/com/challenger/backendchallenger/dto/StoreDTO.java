package br.com.challenger.backendchallenger.dto;

public class StoreDTO {

    private Long id;
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
