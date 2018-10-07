package com.troytan.notify.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.troytan.notify.exception.BusinessException;
import com.troytan.notify.exception.RequestException;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileServiceImpl implements FileService {

    private final String FILE_DIR = "E:/home/nginx";
    private final int    MB       = 1024 * 1024;

    /**
     * 保存文件
     *
     * @author troytan
     * @date 2018年10月7日
     * @param files
     * @return
     * @throws BusinessException (non-Javadoc)
     * @see com.troytan.notify.service.FileService#saveFiles(org.springframework.web.multipart.MultipartFile[])
     */
    @Override
    public String saveFiles(MultipartFile file) throws Exception {
        long fileSize = file.getSize();
        double scale = 1.0;
        if (fileSize > 2 * MB) {
            throw new RequestException("文件不能超过2M");
        } else if (fileSize > 1.5 * MB) {
            scale = 0.5;
        } else if (fileSize > MB) {
            scale = 0.75;
        }
        String name = file.getOriginalFilename();
        String filePath = "/notify/" + UUID.randomUUID().toString().replaceAll("-", "")
                          + name.substring(name.lastIndexOf("."));
        try {
            Thumbnails.of(file.getInputStream()).scale(scale).toFile(FILE_DIR + filePath);
        } catch (Exception e) {
            throw new BusinessException("保存文件失败:" + filePath);
        }
        return "http://troytan1991.club:8081" + filePath;
    }

}
