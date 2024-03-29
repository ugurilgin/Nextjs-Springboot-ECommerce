package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.responses.FileDTO;
import com.nextecommerce.commerce.dtos.responses.PhotoResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {

    PhotoResponseDTO addPhoto( MultipartFile file , HttpServletRequest request) throws IOException ;
    FileDTO getPhoto(String id) throws IOException;
    void deletePhoto(String id);
    PhotoResponseDTO getPhotoInfo(String id);

}
