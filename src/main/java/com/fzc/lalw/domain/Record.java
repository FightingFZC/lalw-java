package com.fzc.lalw.domain;

import lombok.Data;

import java.util.List;

/**
 * 
 * @TableName record
 */
@Data
public class Record extends Entity {
    /**
     * 
     */
    private Integer userId;

    /**
     * 计算的矩阵
     */
    private List<String[][]> matrixs;

    /**
     * 计算结果
     */
    private List<Object> result;

    /**
     * 计算类型
     */
    private String type;
}