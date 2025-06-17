package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private List<Category> categories = new ArrayList<>();

    public CategoriesController() {
        categories.add(new Category(10, "Beverages"));
        categories.add(new Category(11, "Condiments"));
        categories.add(new Category(12, "Seafood"));
        categories.add(new Category(17, "Produce"));
    }

    @GetMapping
    public List<Category> getAllCategories(@RequestParam(required = false) String name) {
        return categories.stream()
                .filter(c -> name == null || c.getCategoryName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categories.stream()
                .filter(c -> c.getCategoryId() == id)
                .findFirst()
                .orElse(null);
    }
}

