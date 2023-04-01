package com.example.ecommerce.services.impl;

import com.example.ecommerce.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile imageFile) throws IOException {

        //File name
        String fileName = imageFile.getOriginalFilename();

        //random name generate file
        String randomID = UUID.randomUUID().toString();
        String newFileName = randomID.concat(fileName.substring(fileName.indexOf(".")));

        String filePath = path + File.separator + newFileName;

        //create folder if not exist
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }


        //file copy

        Files.copy(imageFile.getInputStream(), Paths.get(filePath));

        return newFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
