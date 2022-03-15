package com.fzc.lalw.model;

import lombok.Data;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */
@Data
public class Response {
    public static final String SUCCESS = "success";
    public static final String WARNING = "warning";
    public static final String ERROR = "error";
    public String code;
    public String msg;
    public String type;
    public Object data;

    public Response(String code, String msg, String type, Object data) {
        this.code = code;
        this.msg = msg;
        this.type = type;
        this.data = data;
    }

    public Response() {
        this.code = "200";
        this.type = SUCCESS;
    }
}
