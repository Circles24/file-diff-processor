package com.circles.JobGateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    public String uploadFile(MultipartFile file, String fileName) {
        return "dummy-path";
    }
}
