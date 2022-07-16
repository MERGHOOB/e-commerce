package com.margub.backendapp.controllers;

import com.margub.backendapp.config.APIResponse;
import com.margub.backendapp.dto.product.ProductDto;
import com.margub.backendapp.models.Category;
import com.margub.backendapp.services.CategoryService;
import com.margub.backendapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addProduct(@RequestBody ProductDto productDto) {
        Category category = categoryService.readCategory(productDto.getCategoryId());
        if (Objects.isNull(category)) {
            return new ResponseEntity<>(new APIResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }

        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new APIResponse(true, "product is added"), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProducts() {
        // should list all the products
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<APIResponse> updateProduct(@PathVariable("productID") Integer productID,
                                                     @RequestBody @Valid ProductDto productDto) {
        Category category = categoryService.readCategory(productDto.getCategoryId());
        if (Objects.isNull(category)) {
            return new ResponseEntity<>(new APIResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }



        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<>(new APIResponse(true, "product is updated"), HttpStatus.OK);

    }
}
