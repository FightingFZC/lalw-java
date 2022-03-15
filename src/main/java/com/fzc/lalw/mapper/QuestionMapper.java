package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.Question;

/**
* @author PerfectFu
* @description 针对表【question】的数据库操作Mapper
* @createDate 2022-03-11 18:57:08
* @Entity com.fzc.lalw.domain.Question
*/
public interface QuestionMapper {
    Integer addQuestion(Question question);
    Question getQuestionById(Integer id);
    Question[] getQuestion(Integer index, Integer length); //这个给出基本信息就行，content
    // 不需要给，按时间排
    Question[] getQuestionByUserId(Integer userId, Integer index,
                                   Integer length);
    Integer modifyQuestion(Question newQuestion);
    Integer updateCommentNumber(Question question);
    Integer deleteQuestion(Integer[] ids);
    Integer deleteQuestionById(Integer id);
    Integer getTotalNum(Integer userId);

}
