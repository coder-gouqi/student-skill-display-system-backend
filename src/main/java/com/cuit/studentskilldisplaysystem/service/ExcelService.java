package com.cuit.studentskilldisplaysystem.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Excel导入导出接口
 */
public interface ExcelService {

    /**
     * 导入数据
     *
     * @param file
     * @return
     */
    Boolean importData(MultipartFile file);

    /**
     * 导出数据
     *
     * @return
     */
    Boolean exportData();

}
