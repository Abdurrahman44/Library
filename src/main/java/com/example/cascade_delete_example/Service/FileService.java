package com.example.cascade_delete_example.Service;

import com.example.cascade_delete_example.Entities.FileEntity;
import com.example.cascade_delete_example.Repositories.FileR;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class FileService {

    @Autowired
    private FileR fileRepository;

    public void saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileData(IOUtils.toByteArray(file.getInputStream()));
        fileRepository.save(fileEntity);
    }
}