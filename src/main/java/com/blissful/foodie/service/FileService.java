package com.blissful.foodie.service;

import com.blissful.foodie.dto.FileData;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileData uploadFile(MultipartFile file,String path);
}
