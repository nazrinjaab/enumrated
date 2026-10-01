package org.example.relations.service;

import org.example.relations.entity.Product;
import org.example.relations.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public void findCheapProducts(
            double maxPrice,
            int pageNumber,
            int pageSize) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("price").ascending()
        );

        Page<Product> products =
                productRepository.findByPriceLessThan(
                        maxPrice,
                        pageable
                );

        for (Product product : products.getContent()) {
            System.out.println(
                    product.getName() + " - " + product.getPrice()
            );
        }
    }
}