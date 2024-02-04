package com.nextecommerce.commerce.apis;

import com.nextecommerce.commerce.annotation.ActivityLog;
import com.nextecommerce.commerce.dtos.responses.FileDTO;
import com.nextecommerce.commerce.enums.ActivityLogScope;
import com.nextecommerce.commerce.services.PhotoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;


@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class PhotoController   {
    private final PhotoService photoService;


    @GetMapping("/{id}")
    public FileDTO getPhoto(@PathVariable String id) throws IOException {
        return photoService.getPhoto(id);
    }

    @DeleteMapping("/{id}")
    @ActivityLog(scope = ActivityLogScope.DETAILED, messageKey = "Photo Deleted",subjectKey="DELETE")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable String id ) {
         photoService.deletePhoto(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/stream/{id}")
    public void showImage(@PathVariable String id, HttpServletResponse response) throws Exception {
        FileDTO photo = photoService.getPhoto(id);
        FileCopyUtils.copy(photo.getStream(), response.getOutputStream());
    }


}
