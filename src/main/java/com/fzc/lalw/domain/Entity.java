package com.fzc.lalw.domain;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/11
 */
@Data
public class Entity {
    /**
     * 实体类id
     * */
    private Integer id;
    /**
     * 创建时间
     * */
    private String creationTime;
    /**
     * 分页查询的起始位置
     * 默认为0
     * */
    private Integer index;
    /**
     * 分页查询的长度
     * 默认为10
     * */
    private Integer length;

    public Entity() {
        setIndex(0);
        setLength(10);
        setCreationTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
