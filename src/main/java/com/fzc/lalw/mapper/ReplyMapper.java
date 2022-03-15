package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.Comment;
import org.apache.ibatis.annotations.Param;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface ReplyMapper {
    Integer addReply(@Param("replied") Comment replied,
                     @Param("comment") Comment comment);
    Integer deleteReplyByRepliedId(Integer repliedId);

}
