package com.example.test.rest.controller;

import com.example.test.rest.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @PostMapping("/insert")
    public ResponseEntity<List<ProductDto>> insertProducts(@RequestBody List<ProductDto> products) {
        //TODO
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
