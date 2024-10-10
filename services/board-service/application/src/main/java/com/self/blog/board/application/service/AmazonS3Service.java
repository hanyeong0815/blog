package com.self.blog.board.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.usecase.S3ImageSaveUseCase;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class AmazonS3Service implements S3ImageSaveUseCase {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String BUCKET;
    private static final String OG_PREFIX = "og/";

    private static final List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

    @Override
    public Map<String, String> imageSave(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        // 파일 타입 검증(이미지 타입)
        validateFileTypeException(originalFileName);

        // 파일 이름 생성 -> 이름 중복 방지
        String newFileName = this.createFileName(originalFileName);

        try {
            // 동시 진행을 위한 원본 파일 업로드
            CompletableFuture<String> originalUploadFuture = uploadOriginalFile(file, newFileName);
            // 동시 진행을 위한 썸네일 업로드
            CompletableFuture<String> thumbnailUploadFuture = uploadThumbnail(file, newFileName);

            // 두 작업이 완료 될 때까지 대기
            CompletableFuture.allOf(originalUploadFuture, thumbnailUploadFuture).join();

            // 결과 반환을 위한 Map -> 필요에 따라서 DTO 고려
            Map<String, String> result = new HashMap<>();
            result.put("original", originalUploadFuture.join());
            result.put("thumbnail", thumbnailUploadFuture.join());

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw BoardErrorCode.BOARD_NOT_FOUND.defaultException();
        }
    }

    // 파일 타입 추출
    private void validateFileTypeException(String originalFileName) {
        validate(
                originalFileName != null,
                BoardErrorCode.DEFAULT
        );

        String fileType;
        try {
            fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            throw BoardErrorCode.DEFAULT.defaultException();
        }

        validate(
                allowedExtentionList.contains(fileType.toLowerCase()),
                BoardErrorCode.DEFAULT
        );
    }

    private String createFileName(String originalFileName) {
        return UUID.randomUUID() + originalFileName;
    }

    @Async
    public CompletableFuture<String> uploadThumbnail(MultipartFile originalFile, String newFileName) throws IOException {
        ByteArrayOutputStream thumbnailOutputStream = new ByteArrayOutputStream();
        Thumbnails.of(originalFile.getInputStream())
                .size(200, 100)
                .toOutputStream(thumbnailOutputStream);

        // 저장할 파일 이름 생성 -> 중복 이름 방지
        String newThumbnailFileName = OG_PREFIX + "t_" + newFileName;

        return CompletableFuture.supplyAsync(() -> uploadToS3(newThumbnailFileName, thumbnailOutputStream.toByteArray(), originalFile.getContentType()));
    }

    @Async
    public CompletableFuture<String> uploadOriginalFile(MultipartFile originalFile, String newFileName) throws IOException {
        byte[] originalData = originalFile.getBytes();
        return CompletableFuture.supplyAsync(() -> uploadToS3(OG_PREFIX + newFileName, originalData, originalFile.getContentType()));
    }

    private String uploadToS3(String fileName, byte[] data, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(data.length);
            metadata.setContentType(contentType);

            System.out.println(amazonS3.getRegionName() + "\n" + BUCKET);

            amazonS3.putObject(BUCKET, fileName, new ByteArrayInputStream(data), metadata);

            return fileName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw BoardErrorCode.BOARD_NOT_FOUND.defaultException();
        }
    }
}
