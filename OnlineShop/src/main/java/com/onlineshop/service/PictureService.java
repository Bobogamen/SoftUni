package com.onlineshop.service;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class PictureService {

    private final Path root = Paths.get("src/main/resources/static/images/items");

    public void init() throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectory(root);
        }
    }

    public String save(MultipartFile file, String name) throws IOException {

        Path filePath = Path.of(root + "/" + name.trim());

        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());

        Path renamedFile = Path.of(filePath + "." + fileExtension);

        Files.copy(file.getInputStream(),
                renamedFile,
                StandardCopyOption.REPLACE_EXISTING);

        return renamedFile.toString();
    }

    public void deletePicture(Path picturePath) throws IOException {
        Files.delete(picturePath);
    }
}
