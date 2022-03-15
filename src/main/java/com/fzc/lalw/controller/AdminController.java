package com.fzc.lalw.controller;

import com.fzc.lalw.domain.Admin;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.AdminService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PutMapping
    public Response modify(@RequestBody Admin admin) {
        Response response;
        response = new Response();
        Boolean state = adminService.modify(admin);
        if (state) {
            response.setMsg("修改成功");
        }else {
            response.setMsg("修改失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }
}
