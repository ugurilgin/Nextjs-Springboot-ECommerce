package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.dtos.responses.FileDTO;
import com.nextecommerce.commerce.dtos.responses.PhotoResponseDTO;
import com.nextecommerce.commerce.entities.Photo;
import com.nextecommerce.commerce.exceptions.EntityNotFoundException;
import com.nextecommerce.commerce.mappers.PhotoMapper;
import com.nextecommerce.commerce.repositories.PhotoRepository;
import com.nextecommerce.commerce.services.FileService;
import com.nextecommerce.commerce.services.PhotoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;


@Service
@AllArgsConstructor
public class ImpPhotoService implements PhotoService {

    private final PhotoRepository photoRepo;
    private final FileService fileService;
    private final PhotoMapper photoMapper;

    @Override
    public  ResponseEntity<PhotoResponseDTO> addPhoto( MultipartFile file, HttpServletRequest request) throws IOException {

            String fileId=fileService.addFile(file);
            Photo photo = new Photo();
            photo.setId(fileId);
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath(null)
                    .build()
                    .toUriString();
            photo.setUrl(baseUrl+"/api/images/stream/"+fileId);
            photo = photoRepo.save(photo);

            return new ResponseEntity<>(photoMapper.toResponse(photo),HttpStatus.CREATED);

    }

    @Override
    public FileDTO getPhoto(String id) throws IOException {

            return  fileService.getFile(id);

    }

    @Override
    public ResponseEntity<HttpStatus> deletePhoto(String id) {

            Photo saved=photoRepo.findById(id)
                    .orElseThrow(()->new EntityNotFoundException("Photo Not Found with This Id"));

                fileService.deleteFileById(saved.getId());
                photoRepo.delete(saved);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<PhotoResponseDTO> getPhotoInfo(String id) {

        Photo photo=  photoRepo.findById(id)
               .orElseThrow(()->new EntityNotFoundException("Photo Not Found with This Id"));

        return new ResponseEntity<>(photoMapper.toResponse(photo),HttpStatus.OK);

    }



}

