package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.Question;
import com.fzc.lalw.mapper.QuestionMapper;
import com.fzc.lalw.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionMapper questionMapper;
    @Override
    public Boolean add(Question question) {
        int num = questionMapper.addQuestion(question);
        return num != 0;
    }

    @Override
    public Integer delete(Integer[] ids) {
        return questionMapper.deleteQuestion(ids);
    }

    @Override
    public Boolean modify(Question modifiedQuestion) {
        int num = questionMapper.modifyQuestion(modifiedQuestion);
        return num != 0;
    }

    @Override
    public Question get(Integer id) {
        return questionMapper.getQuestionById(id);
    }

    @Override
    public Question[] get(String titlePattern, String beginDatetime,
                          String endDatetime, Integer index, Integer length) {
        return questionMapper.getQuestion(index, length);
    }

    @Override
    public Question[] get(Integer userId, Integer index, Integer length) {
        return questionMapper.getQuestionByUserId(userId, index, length);
    }

    @Override
    public Integer getTotalNum(Integer userId) {
        return questionMapper.getTotalNum(userId);
    }
}
