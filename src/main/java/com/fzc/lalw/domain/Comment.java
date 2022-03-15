package com.fzc.lalw.domain;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName comment
 */
@Data
public class Comment extends Entity {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer questionId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date lastModified;

    /**
     * 回复数
     */
    private Integer replyNumber;
}