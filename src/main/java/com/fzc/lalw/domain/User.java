package com.fzc.lalw.domain;

/**
 * 
 * @TableName user
 */

public class User extends Entity implements SignAble {
    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String avatar;

    /**
     * 回复消息数
     */
    private Integer commentNewMsgNum;

    /**
     * 系统消息数
     */
    private Integer sysNewMsgNum;

    public User() {
        setAvatar("http://localhost:8080/img/avatar/default.png");
        setCommentNewMsgNum(0);
        setSysNewMsgNum(0);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getCommentNewMsgNum() {
        return commentNewMsgNum;
    }

    public void setCommentNewMsgNum(Integer commentNewMsgNum) {
        this.commentNewMsgNum = commentNewMsgNum;
    }

    public Integer getSysNewMsgNum() {
        return sysNewMsgNum;
    }

    public void setSysNewMsgNum(Integer sysNewMsgNum) {
        this.sysNewMsgNum = sysNewMsgNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", commentNewMsgNum=" + commentNewMsgNum +
                ", sysNewMsgNum=" + sysNewMsgNum +
                '}';
    }
}