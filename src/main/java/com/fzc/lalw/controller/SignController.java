package com.fzc.lalw.controller;

import com.fzc.lalw.domain.SignAble;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.SignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * IntelliJ IDEA
 * PerfectFu
 * 2022/3/9
 */
@RestController
public class SignController {
    @Resource
    private HttpSession session;
    @Resource
    private SignService signService;

    @PostMapping("/token")
    public Response login(@RequestBody Map<String, String> loginInfo) {
        Response response = new Response();
        SignAble actor = signService.login(loginInfo);
        String token = null;
        if (actor != null) {
            token = UUID.randomUUID().toString();

            session.setAttribute("actor", actor);
            session.setAttribute("token", token);

            response.setMsg("登陆成功");
            response.setData(token);
        }else {
            response.setCode("401");
            response.setMsg("登陆失败");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @GetMapping("/actor")
    public Response getActor(){
        Response response = new Response();
        SignAble actor = (SignAble) session.getAttribute("actor");
        response.setData(actor);
        if (actor != null) {
            response.setMsg("查询成功");
        }else {
            response.setCode("401");
            response.setMsg("查询失败");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @DeleteMapping("/token")
    public Response loginOut(){
        Response response = new Response();
        session.removeAttribute("actor");
        session.removeAttribute("token");
        response.setMsg("已退出登录！");
        return response;
    }

    @PostMapping("/code")
    public Response createCode(String email) {
        Response response = new Response();
        System.out.println(email);
        String code = signService.createCode(email);
        session.setAttribute("code", code);
        response.setMsg("发送成功");
        response.setData(code);
        return response;
    }

    @GetMapping("/code/{code}")
    public Response checkCode(@PathVariable String code)  {
        Response response = new Response();
        String trueCode = (String) session.getAttribute("code");

        if (!trueCode.equals(code)) {
            response.setCode("401");
            response.setMsg("验证码错误");
            response.setType(Response.ERROR);
        }else {
            response.setMsg("验证成功");
        }
        return response;
    }
}
