package com.example.test.rest.service;

import static java.util.stream.Collectors.toList;

import com.example.test.rest.dto.ProductDto;
import com.example.test.rest.dto.ProductResponseDto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Service;

/**
 * Created by s2it_ebueno on 10/5/17.
 */
@Service
public class ProductService {

    public List<ProductResponseDto> process(List<ProductDto> products) {

        List<ProductResponseDto> groupedList = aggregateProducts(products, ProductDto::getEan, ProductDto::getTitle);

        groupByField(groupedList, ProductDto::getTitle, ProductDto::getTitle);
        groupByField(groupedList, ProductDto::getBrand, ProductDto::getBrand);

        return groupedList;
    }

    private void groupByField(List<ProductResponseDto> groupedList, Function<ProductDto, String> groupField,
                              Function<ProductDto, String> orderField) {
        List<ProductResponseDto> ungrouped = groupedList.stream()
                .filter(p -> p.getItems().size() == 1)
                .collect(toList());

        if (!ungrouped.isEmpty()) {
            groupedList.removeAll(ungrouped);

            List<ProductResponseDto> ungroupedGrouped = aggregateProducts(ungrouped.stream()
                                                                .flatMap(u -> u.getItems().stream())
                                                                .collect(toList()),groupField, orderField);
            groupedList.addAll(ungroupedGrouped);
        }
    }

    private List<ProductResponseDto> aggregateProducts(List<ProductDto> products,
                                                       Function<ProductDto, String> groupField,
                                                       Function<ProductDto, String> orderField) {
        List<String> descriptions = products.stream()
                .map(groupField)
                .collect(toList());
        Map<String, List<ProductDto>> productMap = new HashMap<>();
        descriptions.forEach(ean -> productMap.put(ean, products.stream()
                .filter(p -> groupField.apply(p).equals(ean))
                .collect(toList())));

        List<ProductResponseDto> responseList = new ArrayList<>();
        productMap.forEach((k, v) -> {
            ProductResponseDto pResponse = new ProductResponseDto(getDescription(v, orderField), v);
            responseList.add(pResponse);
        });

        return responseList;
    }


    private String getDescription(List<ProductDto> v, Function<ProductDto, String> descField) {
        v.sort(Comparator.comparing(descField));
        String title = descField.apply(v.get(0));
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
