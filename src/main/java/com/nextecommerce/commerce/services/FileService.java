package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.responses.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

     String addFile( MultipartFile file) throws IOException ;
     FileDTO getFile(String id) throws IllegalStateException, IOException;
     void deleteFileById(String id) throws IllegalStateException;
     void deleteFilesByIds(List<String> ids) throws IllegalStateException;

    }
