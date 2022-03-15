package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.Comment;
import com.fzc.lalw.domain.Question;
import com.fzc.lalw.mapper.CommentMapper;
import com.fzc.lalw.mapper.QuestionMapper;
import com.fzc.lalw.mapper.ReplyMapper;
import com.fzc.lalw.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;

    @Resource
    ReplyMapper replyMapper;

    @Resource
    QuestionMapper questionMapper;

    @Override
    public Boolean add(Comment comment) {
        int num = commentMapper.addComment(comment);
        return num != 0;
    }

    @Override
    public Boolean add(Integer repliedId, Comment comment) {
        Comment replied = commentMapper.getCommentById(repliedId);
        Question question = questionMapper.getQuestionById(replied.getQuestionId());
        int num1 = commentMapper.addComment(comment);
        int num2 = replyMapper.addReply(replied, comment);
        int num3 = commentMapper.updateReplyNumber(replied);
        int num4 = questionMapper.updateCommentNumber(question);
        return num1 != 0 && num2 != 0 && num3 != 0 && num4 != 0;
    }

    @Override
    public Integer delete(Integer[] ids) {

        for (Integer id: ids) {
            Comment comment = commentMapper.getCommentByReplyId(id);
            if (comment != null) {
                int num2 = commentMapper.updateReplyNumber(comment);
            }
            Question question =
                    questionMapper.getQuestionById(commentMapper.getCommentById(id).getQuestionId());
            if (question != null) {
                int num3 = questionMapper.updateCommentNumber(question);
            }
        }
        return commentMapper.deleteComment(ids);
    }

    @Override
    public Boolean modify(Comment modifiedComment) {
        int num = commentMapper.modifyComment(modifiedComment);
        return num != 0;
    }

    @Override
    public Comment[] getByQuestionId(Integer questionId, Integer index,
                             Integer length) {
        return commentMapper.getCommentByQuestionId(questionId, index, length);
    }

    @Override
    public Comment[] getByCommentId(Integer commentId, Integer index,
                              Integer length) {
        return commentMapper.getCommentByCommentId(commentId, index, length);
    }

    @Override
    public Comment[] getByUserId(Integer userId, Integer index,
                                 Integer length) {
        return commentMapper.getCommentByUserId(userId, index, length);
    }

    @Override
    public Comment get(Integer id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public Integer getTotalNum(Integer id) {
        return commentMapper.getTotalNum(id);
    }
}
