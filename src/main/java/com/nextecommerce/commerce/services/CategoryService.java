package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.requests.CategoryRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
     List<CategoryResponseDTO> getAllCategories( );
     CategoryResponseDTO getCategoryById (Long id);
     CategoryResponseDTO createCategory (CategoryRequestDTO request );
     CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);
     void deleteCategory(Long id);

}
