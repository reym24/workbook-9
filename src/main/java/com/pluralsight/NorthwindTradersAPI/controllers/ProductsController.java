package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private List<Product> products = new ArrayList<>();

    public ProductsController() {
        products.add(new Product(1, "Chai", 10, 18.0));
        products.add(new Product(2, "Chang", 10, 19.0));
        products.add(new Product(3, "Aniseed Syrup", 11, 10.0));
        products.add(new Product(4, "Chef Anton's Cajun Seasoning", 11, 22.0));
        products.add(new Product(5, "Ikura", 12, 31.0));
    }

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double price
    ) {
        return products.stream()
                .filter(p -> name == null || p.getProductName().toLowerCase().contains(name.toLowerCase()))
                .filter(p -> categoryId == null || p.getCategoryId() == categoryId)
                .filter(p -> price == null || p.getUnitPrice() == price)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
    }
}
