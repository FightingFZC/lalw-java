package com.fzc.lalw.domain;

import lombok.Data;

/**
 * 
 * @TableName alg
 */
@Data
public class Alg extends Entity {
    /**
     * 所属用户id
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 最后修改时间
     */
    private String lastModified;


}