package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.requests.ProductRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;
import com.nextecommerce.commerce.dtos.responses.ProductResponseDTO;
import com.nextecommerce.commerce.entities.Categories;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
     List<ProductResponseDTO> getAllProducts( );
     ProductResponseDTO getProductById (Long id);
     ProductResponseDTO createProduct (ProductRequestDTO request, MultipartFile file  , HttpServletRequest http) throws IOException;
     ProductResponseDTO updateProduct(Long id, ProductRequestDTO request,MultipartFile file,  HttpServletRequest http) throws IOException;

     ProductResponseDTO addRating(Long id, Long ratingId);

     void deleteProduct(Long id);

     CategoryResponseDTO addCategory(Long productId, Categories newCategory);

     ProductResponseDTO deleteCategoryFromProduct(Long productId, Long categoryId);

}
