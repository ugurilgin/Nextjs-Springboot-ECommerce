package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.dtos.requests.CategoryRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;
import com.nextecommerce.commerce.entities.Categories;
import com.nextecommerce.commerce.exceptions.EntityNotFoundException;
import com.nextecommerce.commerce.mappers.CategoryMapper;
import com.nextecommerce.commerce.repositories.CategoryRepository;
import com.nextecommerce.commerce.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> getAllCategories() {

        List<Categories> categories = categoryRepository.findAll();

        return categories.stream().map(categoryMapper::toResponse).collect(Collectors.toList());

    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {

        Categories category=categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id ));

        return categoryMapper.toResponse(category);

    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        Categories categories  = categoryMapper.toObjFromRequest(request);
        Categories response = categoryRepository.save(categories);

        return categoryMapper.toResponse(response);

    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {

        Categories categories = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id ));

        categoryMapper.convert(categories,request);

        Categories response = categoryRepository.save(categories);

        return categoryMapper.toResponse(response);

    }

    @Override
    public void deleteCategory(Long id) {

        Categories categories = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categories not found with id " + id ));

        categories.setIsDeleted(true);
        categoryRepository.save(categories);

    }
}
