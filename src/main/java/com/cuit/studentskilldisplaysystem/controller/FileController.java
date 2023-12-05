package com.cuit.studentskilldisplaysystem.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {

        String savePath = "";
        try {
            String userDir = System.getProperty("user.dir");
            String fileName = file.getOriginalFilename();
            //静态资源目录
            File uploadPath = new File(userDir + File.separator + "file" + File.separator + "image" + File.separator + type + File.separator);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            file.transferTo(new File(uploadPath, fileName));

            //target目录，不能永久保存,后台重启数据丢失
            String targetPath = ResourceUtils.getURL("classpath:").getPath();
            File targetUploadPath = new File(targetPath + "static" + File.separator + "image" + File.separator + type + File.separator);
            if (!targetUploadPath.exists()) {
                targetUploadPath.mkdirs();
            }
            FileUtil.copy(uploadPath + File.separator + fileName, targetUploadPath + File.separator + fileName, true);

            savePath = "file" + File.separator + "image" + File.separator + type + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savePath;
    }
}
