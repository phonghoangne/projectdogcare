package com.app.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SaveFileUntil {
    public static void save(MultipartFile file ,Path root )
    {
        System.out.println("filePath : "+root.toString());
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
