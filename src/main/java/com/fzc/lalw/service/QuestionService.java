package com.fzc.lalw.service;

import com.fzc.lalw.domain.Question;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface QuestionService {
    Boolean add(Question question);
    Integer delete(Integer[] ids);
    Boolean modify(Question modifiedQuestion);
    Question get(Integer id);
    Question[] get(String titlePattern, String beginDatetime,
                   String endDatetime, Integer index, Integer length);
    Question[] get(Integer userId, Integer index, Integer length);
    Integer getTotalNum(Integer userId);

}
