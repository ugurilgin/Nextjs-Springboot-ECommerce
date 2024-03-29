package com.nextecommerce.commerce.apis;

import com.nextecommerce.commerce.annotation.ActivityLog;
import com.nextecommerce.commerce.dtos.requests.CategoryRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;
import com.nextecommerce.commerce.enums.ActivityLogScope;
import com.nextecommerce.commerce.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {

        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {

        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);

    }

    @PostMapping
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Category Created",subjectKey="CREATE")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO request) {

        return new ResponseEntity<>(categoryService.createCategory(request),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Category Updated",subjectKey="UPDATE")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryRequestDTO request) {

        return new ResponseEntity<>(categoryService.updateCategory(id,request),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Category DELETED",subjectKey="DELETE")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
