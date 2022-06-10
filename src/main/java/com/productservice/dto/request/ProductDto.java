package com.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productservice.domain.Inventory;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Inventory inventory;
//    @JsonIgnore
    private Long categoryId;
}
