package com.fzc.lalw.domain;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName question
 */
@Data
public class Question extends Entity {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date lastModified;

    /**
     * 评论数
     */
    private Integer commentNumber;

    /**
     * 阅读数
     */
    private Integer readNumber;
}