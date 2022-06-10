package com.productservice.dto.request;

import com.productservice.domain.Category;
import com.productservice.domain.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private Long parentId;
    private Category parent;
    private List<ProductDto> products;
}
