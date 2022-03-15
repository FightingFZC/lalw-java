package com.fzc.lalw.controller;

import com.fzc.lalw.domain.Comment;
import com.fzc.lalw.domain.Question;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.CommentService;
import com.fzc.lalw.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @Resource
    private CommentService commentService;

    @PostMapping
    public Response add(@RequestBody Question question) {
        Response response = new Response();
        Boolean state = questionService.add(question);
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
        Integer n = questionService.delete(ids);
        if (n == ids.length) {
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
        Response response = new Response();
        Integer n = questionService.delete(new Integer[]{id});
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
    public Response modify(@RequestBody Question modifiedQuestion) {
        Response response = new Response();
        Boolean state = questionService.modify(modifiedQuestion);
        if (state) {
            response.setMsg("修改成功");
        } else {
            response.setMsg("修改失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping
    public Response get(String titlePattern, String beginDatetime,
                        String endDatetime, Integer page, Integer length) {
        Response response = new Response();
        Question[] questions = questionService.get(titlePattern,
                beginDatetime, endDatetime, (page - 1) * length,
                length);
        response.setData(questions);
        if (questions != null) {
            response.setMsg("获取问题成功");
        } else {
            response.setMsg("获取问题失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Integer id) {
        Response response = new Response();
        Question question = questionService.get(id);
        response.setData(question);
        if (question != null) {
            response.setMsg("获取id为：" + id + " 的问题成功");
        } else {
            response.setMsg("获取问题失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/comment")
    public Response getComment(@PathVariable Integer id, Integer page,
                               Integer length) {
        Response response = new Response();
        Comment[] comments = commentService.getByQuestionId(id,
                (page - 1) * length, length);
        response.setData(comments);
        if (comments != null) {
            response.setMsg("获取评论成功");
        } else {
            response.setMsg("获取评论失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }
}

