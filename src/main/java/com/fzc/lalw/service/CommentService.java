package com.fzc.lalw.service;

import com.fzc.lalw.domain.Comment;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface CommentService {
    Boolean add(Comment comment);
    Boolean add(Integer repliedId, Comment comment);
    Integer delete(Integer[] ids);
    Boolean modify(Comment modifiedComment);
    Comment[] getByQuestionId(Integer questionId, Integer index,
                              Integer length);
    Comment[] getByCommentId(Integer commentId, Integer index, Integer length);
    Comment[] getByUserId(Integer userId, Integer index, Integer length);
    Comment get(Integer id);

    Integer getTotalNum(Integer id);
}
