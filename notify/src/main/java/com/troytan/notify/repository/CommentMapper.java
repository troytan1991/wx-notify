package com.troytan.notify.repository;

import com.troytan.notify.domain.Comment;
import com.troytan.notify.dto.CommentDto;

import java.util.List;

public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_comment
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer commentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_comment
     *
     * @mbg.generated
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_comment
     *
     * @mbg.generated
     */
    Comment selectByPrimaryKey(Integer commentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_comment
     *
     * @mbg.generated
     */
    List<Comment> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Comment record);

    List<CommentDto> listByNotifyId(Integer notifyId);
}