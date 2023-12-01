package com.cuit.studentskilldisplaysystem.model.excel;

import com.cuit.studentskilldisplaysystem.service.ExcelService;
import com.cuit.studentskilldisplaysystem.service.impl.CourseServiceImpl;
import com.cuit.studentskilldisplaysystem.service.impl.ScoreServiceImpl;
import com.cuit.studentskilldisplaysystem.service.impl.UserServiceImpl;

/**
 * Excel工具类的工厂类（创建依据类的实例）
 */
public class ExcelServiceFactory {
    /**
     * @param type
     * @return
     */
    public static ExcelService newInstance(String type) {
        switch (type) {
            case "user":
                return new UserServiceImpl();
            case "course":
                return new CourseServiceImpl();
            case "score":
                return new ScoreServiceImpl();
            default:
                return null;
        }
    }
}
