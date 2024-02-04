package com.nextecommerce.commerce.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.nextecommerce.commerce.dtos.responses.FileDTO;
import com.nextecommerce.commerce.services.FileService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;


    public String addFile(  MultipartFile file) throws IOException {

            DBObject metaData = new BasicDBObject();
            metaData.put("type", file.getContentType());
            metaData.put("size", file.getSize());
            ObjectId id = gridFsTemplate.store(
                    file.getInputStream(), file.getName(), file.getContentType(), metaData);

            return id.toString();

    }
    public FileDTO getFile(String id) throws IllegalStateException, IOException  {

        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        FileDTO response = new FileDTO();
        response.setStream(operations.getResource(file).getInputStream());

        return response;

    }
    public void deleteFileById(String id) throws IllegalStateException  {

        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));

    }
    public void deleteFilesByIds(List<String> ids) throws IllegalStateException  {

        gridFsTemplate.delete(new Query(Criteria.where("_id").in(ids)));

    }
}
