package com.example.test.rest.controller;

import com.example.test.rest.dto.ProductDto;
import com.example.test.rest.dto.ProductResponseDto;
import com.example.test.rest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/insert")
    public ResponseEntity<List<ProductResponseDto>> insertProducts(@RequestBody List<ProductDto> products) {
        return ResponseEntity.ok(productService.process(products));
    }
}
