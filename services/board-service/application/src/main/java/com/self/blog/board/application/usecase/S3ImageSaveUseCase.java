package com.self.blog.board.application.usecase;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface S3ImageSaveUseCase {
    Map<String, String> imageSave(MultipartFile file);
}
