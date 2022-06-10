package com.productservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
//    @JoinTable(name = "category_products")
    private List<Product> products = new ArrayList<>();

    //@TODO not working cascade
    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
    @JoinColumn(name = "parent_id")
    private Category parent;
}
