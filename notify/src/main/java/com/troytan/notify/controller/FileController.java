package com.troytan.notify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.troytan.notify.aspect.NoAuth;
import com.troytan.notify.service.FileService;

@RestController
@RequestMapping(value = "/file", consumes = "application/json;charset=UTF-8")
public class FileController {

    @Autowired
    private FileService fileService;

    @NoAuth
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            return null;
        }
        return fileService.saveFiles(file);
    }
}
