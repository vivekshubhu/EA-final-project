package com.productservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Inventory inventory;
}
