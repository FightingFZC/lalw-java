package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.Comment;

/**
* @author PerfectFu
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2022-03-11 18:57:41
* @Entity com.fzc.lalw.domain.Comment
*/
public interface CommentMapper {

    Integer addComment(Comment comment);
    Comment getCommentById(Integer id);
    Comment[] getComment(Integer index,Integer length); //这个给出基本信息就行
    Comment[] getCommentByQuestionId(Integer questionId, Integer index,
        Integer length);
    //仅查出直接回复该问题的回答
    Comment[] getCommentByCommentId(Integer commentId, Integer index,
        Integer length);
    //仅查出直接回复该回答的回复
    Comment getCommentByReplyId(Integer replyId);
    Comment[] getCommentByUserId(Integer userId, Integer index, Integer length);
    Integer getTotalNum(Integer userId);
    Integer modifyComment(Comment newComment);
    Integer updateReplyNumber(Comment comment);
    Integer deleteComment(Integer[] ids);
    Integer deleteCommentById(Integer id);


}
