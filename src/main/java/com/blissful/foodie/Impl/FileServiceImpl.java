package com.blissful.foodie.Impl;

import com.blissful.foodie.dto.FileData;
import com.blissful.foodie.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public FileData uploadFile(MultipartFile file, String path) {
        if (null == path || path.isEmpty()) {
            throw new RuntimeException("invalid path value!!");
        }
        Path directory = Paths.get(path.substring(0, path.lastIndexOf("/") + 1));
        log.info("path got {}", directory);
        try {
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            Path filePath = Paths.get(path);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new FileData(file.getName(), path);
    }
}
