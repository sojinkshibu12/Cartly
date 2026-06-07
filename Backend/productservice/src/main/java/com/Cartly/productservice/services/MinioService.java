package com.Cartly.productservice.services;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

  private final MinioClient minioClient;

  @Value("${minio.bucket}")
  private String bucketName;

  public String upload(MultipartFile file) throws Exception {

    String objectName = UUID.randomUUID() + "-" + file.getOriginalFilename();

    minioClient.putObject(
        PutObjectArgs.builder()
            .bucket(bucketName)
            .object(objectName)
            .stream(
                file.getInputStream(),
                file.getSize(),
                -1)
            .contentType(file.getContentType())
            .build());

    return objectName;
  }

  public String getImageUrl(String objectName) {

    return String.format(
        "http://localhost:9000/%s/%s",
        bucketName,
        objectName);
  }
}
