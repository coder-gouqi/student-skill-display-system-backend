package com.cuit.studentskilldisplaysystem.controller;

import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.excel.ExcelServiceFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 导入信息
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public StatusResponse importExcel(@RequestPart(value = "file") MultipartFile file, @RequestParam("type") String type) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = Objects.requireNonNull(ExcelServiceFactory.newInstance(type)).importData(file);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }
}
