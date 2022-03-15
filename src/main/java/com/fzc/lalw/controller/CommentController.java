package com.fzc.lalw.controller;

import com.fzc.lalw.domain.Comment;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping
    public Response add(@RequestBody Comment comment) {
        Response response = new Response();
        Boolean state = commentService.add(comment);
        if (state) {
            response.setMsg("添加成功");
        } else {
            response.setMsg("添加失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @PostMapping("/{id}/reply")
    public Response addReply(@PathVariable Integer id, Comment comment) {
        Response response = new Response();
        Boolean state = commentService.add(id, comment);
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
        Integer n = commentService.delete(ids);
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
        Response response;
        response = new Response();
        Integer n = commentService.delete(new Integer[]{id});
        if (n == 1) {
            response.setMsg("删除成功");
        } else {
            response.setMsg("删除失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @PutMapping
    public Response modify(@RequestBody Comment modifiedComment) {
        Response response = new Response();
        Boolean state = commentService.modify(modifiedComment);
        if (state) {
            response.setMsg("修改成功");
        } else {
            response.setMsg("修改失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Integer id) {
        Response response = new Response();
        Comment comment = commentService.get(id);
        response.setData(comment);
        if (comment != null) {
            response.setMsg("查询id为：" + id + " 的评论成功");
        } else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }

    @GetMapping("/{id}/comment")
    public Response get(@PathVariable Integer id, Integer page,
                        Integer length) {
        Response response = new Response();
        Comment[] comments = commentService.getByCommentId(id,
                (page - 1) * length, length);
        response.setData(comments);
        if (comments != null) {
            response.setMsg("查询评论成功");
        } else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }


}
