package com.margub.backendapp.services;

import com.margub.backendapp.models.Category;
import com.margub.backendapp.reposistories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * To use findAll and save method for categories
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }
}
