package com.app.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SaveFileUntil {
    public static void save(MultipartFile file ,Path root ) {
        Path filePath = root;
        System.out.println("filePath : "+filePath.toString());
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectory(filePath);
            }
            Files.copy(file.getInputStream(), filePath.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
