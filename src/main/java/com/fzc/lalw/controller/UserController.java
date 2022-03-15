package com.fzc.lalw.controller;

import com.fzc.lalw.domain.Record;
import com.fzc.lalw.domain.*;
import com.fzc.lalw.model.Response;
import com.fzc.lalw.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/13
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private AlgService algService;

    @Resource
    private QuestionService questionService;

    @Resource
    private CommentService commentService;

    @Resource
    private RecordService recordService;

    @PostMapping
    public Response register(@RequestBody User user) {
        Response response = new Response();
        System.out.println(user);
        Boolean state = userService.add(user);
        if (state) {
            response.setMsg("添加成功");
        }else {
            response.setMsg("添加失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }

        return response;
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id){
        Response response = new Response();
        Integer deleteNum = userService.delete(new Integer[]{id});
        if (deleteNum != 1) {
            response.setCode("401");
            response.setType(Response.ERROR);
            response.setMsg("删除失败");
        }else {
            response.setMsg("删除成功");
        }
        return response;
    }

    @DeleteMapping
    public Response delete(@RequestBody Integer[] ids) {
        Response response = new Response();
        Integer deleteNum = userService.delete(ids);
        if (ids.length != deleteNum) {
            response.setCode("401");
            response.setType(Response.ERROR);
            response.setMsg("删除失败");
        }else {
            response.setMsg("删除成功");
        }
        return response;
    }

    @PutMapping
    public Response modify(@RequestBody User modifiedUser) {
        Response response = new Response();
        Boolean state = userService.modify(modifiedUser);
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
        User user = userService.get(id);
        response.setData(user);
        if (user != null) {
            response.setMsg("查询id为：" + id + " 的用户成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("")
    public Response get(Integer page, Integer length) {
        Response response = new Response();
        User[] users = userService.get((page - 1) * length, length);
        response.setData(users);
        if (users.length != 0) {
            response.setMsg("查询用户成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/totalNum")
    public Response getTotalNum() {
        Response response = new Response();
        Integer n = userService.getTotalNum();
        response.setData(n);
        if (n != null) {
            response.setMsg("查询用户数量成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/alg")
    public Response getAlg(@PathVariable Integer id, Integer page, Integer length) {
        Response response = new Response();
        Alg[] alg = algService.getByUserId(id, (page - 1) * length,
                length);
        response.setData(alg);
        if (alg != null) {
            response.setMsg("查询用户算法成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/alg/totalNum")
    public Response getAlgTotalNum(@PathVariable Integer id) {
        Response response = new Response();
        Integer n = algService.getTotalNum(id);
        response.setData(n);
        if (n != null) {
            response.setMsg("查询用户算法数量成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/question")
    public Response getQuestion(@PathVariable Integer id, Integer page, Integer length) {
        Response response = new Response();
        Question[] questions = questionService.get(id, page, length);
        response.setData(questions);
        if (questions != null) {
            response.setMsg("查询用户提问成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/question/totalNum")
    public Response getQuestionTotalNum(@PathVariable Integer id) {
        Response response = new Response();
        Integer n = questionService.getTotalNum(id);
        response.setData(n);
        if (n != null) {
            response.setMsg("查询用户提问数量成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/comment")
    public Response getComment(@PathVariable Integer id, Integer page,
                        Integer length) {
        Response response = new Response();
        Comment[] comments = commentService.getByUserId(id, page, length);
        response.setData(comments);
        if (comments != null) {
            response.setMsg("查询用户回答成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/comment/totalNum")
    public Response getCommentTotalNum(@PathVariable Integer id) {
        Response response = new Response();
        Integer n = commentService.getTotalNum(id);
        response.setData(n);
        if (n != null) {
            response.setMsg("查询用户评论数量成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}/record")
    public Response getRecord(@PathVariable Integer id, Integer page,
                              Integer length) {
        Response response = new Response();
        Record[] records = recordService.get(id, page, length);
        response.setData(records);
        if (records != null) {
            response.setMsg("查询用户计算历史成功");
        }else {
            response.setMsg("查询失败");
            response.setCode("401");
            response.setType(Response.ERROR);
        }
        return response;
    }
}
