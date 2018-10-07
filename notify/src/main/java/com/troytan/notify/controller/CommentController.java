package com.troytan.notify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.troytan.notify.domain.Comment;
import com.troytan.notify.dto.CommentDto;
import com.troytan.notify.service.CommentService;
import com.troytan.notify.service.UserService;

@RestController
@RequestMapping(path = "/comment", produces = "application/json;charset=UTF-8")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService    userService;

    /**
     * 创建评论
     *
     * @author troytan
     * @date 2018年7月11日
     * @param comment
     */
    @PutMapping
    public void createComment(@RequestBody Comment comment) {
        comment.setCreateBy(userService.getCurrentUser());
        commentService.createComment(comment);
    }

    /**
     * 获取评论列表
     *
     * @author troytan
     * @date 2018年7月11日
     * @param notifyId
     * @return
     */
    @GetMapping("/comments/{notifyId}")
    public List<CommentDto> getComments(@PathVariable("notifyId") Integer notifyId) {
        return commentService.listComment(notifyId);
    }
}
