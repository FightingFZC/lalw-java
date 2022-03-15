package com.fzc.lalw.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends Entity implements SignAble {
    /**
     * 管理员账户名
     */
    private String account;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员头像地址
     * 有默认位置http://localhost:8080/img/avatar/admin/default.png
     */
    private String avatar;

    public Admin() {
        setAvatar("http://localhost:8080/img/avatar/default.png");
    }
}