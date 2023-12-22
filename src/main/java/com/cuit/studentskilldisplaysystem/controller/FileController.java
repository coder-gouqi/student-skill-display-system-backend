package com.cuit.studentskilldisplaysystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            String fileName = file.getOriginalFilename();
            //静态资源目录
            File uploadPath = new File(File.separator + "www" + File.separator + "wwwroot" + File.separator + "43.136.130.87" + File.separator + "file" + File.separator + "image" + File.separator + type + File.separator);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            file.transferTo(new File(uploadPath, fileName));

            //target目录，不能永久保存,后台重启数据丢失
//            String targetPath = ResourceUtils.getURL("classpath:").getPath();
//            File targetUploadPath = new File(targetPath + "static" + File.separator + "image" + File.separator + type + File.separator);
//            if (!targetUploadPath.exists()) {
//                targetUploadPath.mkdirs();
//            }
//            FileUtil.copy(uploadPath + File.separator + fileName, targetUploadPath + File.separator + fileName, true);

            savePath = "file" + File.separator + "image" + File.separator + type + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savePath;
    }
}
