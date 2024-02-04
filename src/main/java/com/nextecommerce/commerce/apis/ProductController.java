package com.nextecommerce.commerce.apis;

import com.nextecommerce.commerce.annotation.ActivityLog;
import com.nextecommerce.commerce.dtos.requests.ProductRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;
import com.nextecommerce.commerce.dtos.responses.ProductResponseDTO;
import com.nextecommerce.commerce.entities.Categories;
import com.nextecommerce.commerce.enums.ActivityLogScope;
import com.nextecommerce.commerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class ProductController  {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {

        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);

    }

    @PostMapping
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Product Created",subjectKey="CREATE")
    public ResponseEntity<ProductResponseDTO> createProduct( @RequestParam( value = "title",required = true) String title,
                                                             @RequestParam( value = "price",required = true) Double price,
                                                             @RequestParam( value = "description",required = true)  String description,
                                                             @RequestParam(value = "image", required = false) MultipartFile file,
                                                             HttpServletRequest http) throws IOException {

        ProductRequestDTO request=ProductRequestDTO.builder().
                title(title).
                price(price).
                description(description).build();

        return new ResponseEntity<>(productService.createProduct(request,file,http),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Product Updated",subjectKey="UPDATE")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,
                                                            @RequestParam( value = "title",required = false) String title,
                                                            @RequestParam( value = "price",required = false) Double price,
                                                            @RequestParam( value = "description",required = false)  String description,
                                                            @RequestParam(value = "image", required = false) MultipartFile file,
                                                            HttpServletRequest http) throws IOException {

        ProductRequestDTO request=ProductRequestDTO.builder().
                title(title).
                price(price).
                description(description).build();

        return new ResponseEntity<>(productService.updateProduct(id,request,file,http),HttpStatus.OK);

    }

    @PostMapping("/{id}/addRating/{ratingId}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Rating Added",subjectKey="ADD")
    public ResponseEntity<ProductResponseDTO> addRating(@PathVariable Long id,@PathVariable Long ratingId) {

        return new ResponseEntity<>(productService.addRating(id,ratingId),HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Product Deleted",subjectKey="DELETE")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/{productId}/addCategory")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Category Added",subjectKey="ADD")
    public ResponseEntity<CategoryResponseDTO> addCategory(@PathVariable Long productId,@Valid @RequestBody Categories newCategory) {

        return new ResponseEntity<>(productService.addCategory(productId,newCategory),HttpStatus.CREATED);

    }

    @DeleteMapping("/{productId}/category/{categoryId}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Product Deleted",subjectKey="DELETE")
    public ResponseEntity<ProductResponseDTO> deleteCategoryFromProduct(@PathVariable Long productId,@PathVariable Long categoryId) {

        return new ResponseEntity<>(productService.deleteCategoryFromProduct(productId,categoryId),HttpStatus.OK);

    }
}
