package com.troytan.notify.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.troytan.notify.aspect.NoAuth;
import com.troytan.notify.domain.Comment;
import com.troytan.notify.dto.CommentDto;
import com.troytan.notify.service.CommentService;
import com.troytan.notify.service.UserService;

@Controller
@Path("/comment")
@Consumes("application/json;charset=utf-8")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
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
    @PUT
    public void createComment(Comment comment) {
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
    @GET
    @Path("/comments/{notifyId}")
    @NoAuth
    public List<CommentDto> getComments(@PathParam("notifyId") Integer notifyId) {
        return commentService.listComment(notifyId);
    }
}
