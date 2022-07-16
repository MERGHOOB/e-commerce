package com.margub.backendapp.controllers;

import com.margub.backendapp.config.APIResponse;
import com.margub.backendapp.models.Category;
import com.margub.backendapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCategory(@Valid @RequestBody Category category) {
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<>(new APIResponse(false, "Category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new APIResponse(true, "created the category"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.listCategories(), HttpStatus.OK);
    }
}
