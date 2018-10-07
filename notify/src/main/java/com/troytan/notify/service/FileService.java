package com.troytan.notify.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String saveFiles(MultipartFile file) throws Exception;
}
