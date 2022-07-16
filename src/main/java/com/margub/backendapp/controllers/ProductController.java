package com.margub.backendapp.controllers;

import com.margub.backendapp.services.CategoryService;
import com.margub.backendapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
}
