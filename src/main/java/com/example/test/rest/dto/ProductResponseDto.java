package com.example.test.rest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by s2it_ebueno on 10/5/17.
 */
public class ProductResponseDto implements Serializable {

    private static final long serialVersionUID = 2286813379076833014L;

    private String description;
    private List<ProductDto> items;

    public ProductResponseDto() {
        super();
    }

    public ProductResponseDto(String description, List<ProductDto> items) {
        this.description = description;
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductDto> getItems() {
        return items;
    }

    public void setItems(List<ProductDto> items) {
        this.items = items;
    }
}
