package org.example.relations.controller;

import org.example.relations.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cheap")
    public String findCheapProducts(
            @RequestParam double maxPrice,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        productService.findCheapProducts(
                maxPrice,
                pageNumber,
                pageSize
        );

        return "finalsrc";
    }
}