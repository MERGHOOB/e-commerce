package com.margub.backendapp.services;

import com.margub.backendapp.dto.product.ProductDto;
import com.margub.backendapp.models.Category;
import com.margub.backendapp.models.Product;
import com.margub.backendapp.reposistories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    private Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::getProductDtoFromProduct)
                .collect(Collectors.toList());

    }

    private ProductDto getProductDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public ProductDto readProduct(Integer productID) {
        Optional<Product> productOptional = productRepository.findById(productID);
        return productOptional.isEmpty() ? null : getProductDtoFromProduct(productOptional.get());
    }

    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);

        product.setId(productID);
        productRepository.save(product);
    }
}
