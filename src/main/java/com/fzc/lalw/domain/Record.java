package com.fzc.lalw.domain;

import lombok.Data;

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
    private String matrixs;

    /**
     * 计算结果
     */
    private String result;

    /**
     * 计算类型
     */
    private String type;
}