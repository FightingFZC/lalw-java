package com.fzc.lalw.controller;

import com.fzc.lalw.domain.Record;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */

@RestController
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordService recordService;

    @PostMapping()
    public Response add(@RequestBody Record record) {
        Response response = new Response();
        Boolean state = recordService.add(record);
        if (state) {
            response.setMsg("添加成功");
        } else {
            response.setMsg("添加失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @DeleteMapping
    public Response delete(@RequestBody Integer[] ids) {
        Response response = new Response();
        Integer n = recordService.delete(ids);
        if (ids.length == n) {
            response.setMsg("删除成功");
        } else {
            response.setMsg("删除失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        Response response = response = new Response();
        Integer n = recordService.delete(new Integer[]{id});
        if (n == 1) {
            response.setMsg("删除成功");
        } else {
            response.setMsg("删除失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }


}
