package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.dtos.requests.ProductRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;
import com.nextecommerce.commerce.dtos.responses.PhotoResponseDTO;
import com.nextecommerce.commerce.dtos.responses.ProductResponseDTO;
import com.nextecommerce.commerce.entities.Categories;
import com.nextecommerce.commerce.entities.Products;
import com.nextecommerce.commerce.entities.Ratings;
import com.nextecommerce.commerce.exceptions.EntityNotFoundException;
import com.nextecommerce.commerce.mappers.CategoryMapper;
import com.nextecommerce.commerce.mappers.ProductMapper;
import com.nextecommerce.commerce.repositories.CategoryRepository;
import com.nextecommerce.commerce.repositories.ProductRepository;
import com.nextecommerce.commerce.repositories.RatingsRepository;
import com.nextecommerce.commerce.services.PhotoService;
import com.nextecommerce.commerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PhotoService photoService;
    private final ProductMapper productMapper;
    private final RatingsRepository ratingsRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<Products> products = productRepository.findAll();

        return products.stream().map(productMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {

        Products product=productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id ));

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request, MultipartFile file , HttpServletRequest http) throws IOException {

        Products products  = productMapper.toObjFromRequest(request);
        PhotoResponseDTO photo= photoService.addPhoto(file,http);
        products.setImage(photo.getUrl());
        Products response = productRepository.save(products);

        return productMapper.toResponse(response);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request ,MultipartFile file ,  HttpServletRequest http) throws IOException {

        Products product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id ));

        productMapper.convert(product,request);

        PhotoResponseDTO photo= photoService.addPhoto(file,http);
        product.setImage(photo.getUrl());

        Products response = productRepository.save(product);

        return productMapper.toResponse(response);
    }

    @Override
    public ProductResponseDTO addRating(Long id, Long ratingId) {

        Products product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id ));
        Ratings rating = ratingsRepository.findById(id)
                .orElse(null);

        product.setRatings(rating);
        Products response = productRepository.save(product);
        return productMapper.toResponse(response);
    }

    @Override
    public void deleteProduct(Long id) {

        Products product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id ));

        product.setIsDeleted(true);
        productRepository.save(product);

    }

    public CategoryResponseDTO addCategory(Long productId, Categories newCategory) {

        Categories categories = productRepository.findById(productId).map(product -> {
            long categoryId = newCategory.getId()!=null? newCategory.getId() : -1;

            if (categoryId >=0) {
                Categories _category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new EntityNotFoundException("Not found Category with id = " + categoryId));
                product.addCategory(_category);
                productRepository.save(product);
                return _category;
            }


            product.addCategory(newCategory);
            Categories response = categoryRepository.save(newCategory);
            return response;

        }).orElseThrow(() -> new EntityNotFoundException("Not found Product with id = " + productId));
        return  categoryMapper.toResponse(categories);
    }

    public ProductResponseDTO deleteCategoryFromProduct(Long productId, Long categoryId) {
        return productRepository.findById(productId).map(product -> {
            product.removeCategory(categoryId);
            productRepository.save(product);
            return productMapper.toResponse(product);
        }).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId ));
    }

}
