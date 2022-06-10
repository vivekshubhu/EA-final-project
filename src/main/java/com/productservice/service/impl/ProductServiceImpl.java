package com.productservice.service.impl;

import com.productservice.Exception.ProductNotFoundException;
import com.productservice.domain.Category;
import com.productservice.domain.Product;
import com.productservice.dto.request.ProductDto;
import com.productservice.repository.CategoryRepository;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private  final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto save(ProductDto productDto) {
        Category category = null;
        Long categoryId = productDto.getCategoryId();
        if(categoryId != null) {
            categoryRepository.findById(categoryId);
        }
        Product product = modelMapper.map(productDto, Product.class);
        if (category != null) {
            //@TODO category not setting
            category.getProducts().add(product);
        }
        product.getInventory().setProduct(product);
        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {
        var products = productRepository.findAll();
        Type listType = new TypeToken<List<ProductDto>>() {}.getType();

        return modelMapper.map(products, listType);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product Not Found ", id)));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, Long id) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setId(id);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
