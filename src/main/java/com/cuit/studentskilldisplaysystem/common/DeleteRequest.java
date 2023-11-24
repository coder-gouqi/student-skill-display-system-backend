package com.cuit.studentskilldisplaysystem.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     *
     */
    private String id;
}