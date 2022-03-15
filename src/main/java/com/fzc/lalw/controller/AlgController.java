package com.fzc.lalw.controller;

import com.fzc.lalw.domain.Alg;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.AlgService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */
@RestController
@RequestMapping("/alg")
public class AlgController {
    @Resource
    AlgService algService;

    @PostMapping
    public Response add(@RequestBody Alg alg) {
        Response response;
        response = new Response();
        Boolean state = algService.add(alg);
        if (state) {
            response.setMsg("添加成功");
        }else {
            response.setMsg("添加失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return  response;
    }

    @PostMapping("/upload/{userId}/{title}")
    public Response upload(MultipartFile file, @PathVariable Integer userId,
                           @PathVariable String title) {
        Response response = new Response();
        Alg alg = new Alg();
        alg.setUserId(userId);
        alg.setTitle(title);
        Boolean state = algService.upload(file, alg);
        if (state) {
            response.setMsg("上传成功");
        }else {
            response.setMsg("上传失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }
    //TODO 下载准备做的
    @GetMapping("/download/{id}/{type}")
    public void download(@PathVariable Integer id,
                             @PathVariable String type, HttpServletResponse resp) {

        try {
            Boolean state = algService.download(id, type,
                    resp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @DeleteMapping
    public Response delete(@RequestBody Integer[] ids) {
        Response response = new Response();
        Integer num = algService.deleteAlg(ids);
        if (num != ids.length) {
            response.setCode("401");
            response.setMsg("删除失败");
            response.setType(Response.ERROR);
        }else {
            response.setMsg("删除成功");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        Response response = new Response();
        int num = algService.deleteAlg(new Integer[]{id});
        if (num != 1) {
            response.setCode("401");
            response.setMsg("删除失败");
            response.setType(Response.ERROR);
        }else {
            response.setMsg("删除成功");
        }
        return response;
    }

    @PutMapping
    public Response modify(@RequestBody Alg modifiedAlg) {
        Response response = new Response();
        Boolean state = algService.modifyAlg(modifiedAlg);
        if (state) {
            response.setMsg("修改成功");
        }else {
            response.setMsg("修改失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Integer id) {
        Response response = new Response();
        Alg alg = algService.get(id);
        response.setData(alg);
        if (alg != null) {
            response.setMsg("查询id为：" + id + " 的算法成功");
        }else {
            response.setCode("401");
            response.setMsg("查询失败");
            response.setType(Response.ERROR);
        }
        return response;
    }
}
