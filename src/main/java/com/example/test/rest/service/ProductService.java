package com.example.test.rest.service;

import static java.util.stream.Collectors.toList;

import com.example.test.rest.dto.ProductDto;
import com.example.test.rest.dto.ProductResponseDto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Created by s2it_ebueno on 10/5/17.
 */
@Service
public class ProductService {

    public List<ProductResponseDto> process(List<ProductDto> products) {
        List<String> eans = products.stream()
                .map(ProductDto::getEan)
                .collect(toList());
        Map<String, List<ProductDto>> productMap = new HashMap<>();
        eans.forEach(ean -> productMap.put(ean, products.stream()
                                                    .filter(p -> p.getEan().equals(ean))
                                                    .collect(toList())));

        List<ProductResponseDto> responseList = new ArrayList<>();
        productMap.forEach((k, v) -> {
            ProductResponseDto pResponse = new ProductResponseDto(getDescription(v), v);
            responseList.add(pResponse);
        });
        return responseList;
    }

    private String getDescription(List<ProductDto> v) {
        v.sort(Comparator.comparing(ProductDto::getTitle));
        String title = v.get(0).getTitle();
        v.sort(productComparator());
        return title;
    }

    private Comparator<ProductDto> productComparator() {
        return (p1, p2) -> {
            if (p1.getStock() < p2.getStock()) {
                return 1;
            } else if (p1.getStock().equals(p2.getStock())) {
                return p1.getPrice().compareTo(p2.getPrice());
            }
            return -1;
        };
    }
}
