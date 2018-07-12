package com.troytan.notify.service;

import java.util.List;

import com.troytan.notify.domain.Comment;
import com.troytan.notify.dto.CommentDto;

public interface CommentService {

    void createComment(Comment comment);

    List<CommentDto> listComment(Integer notifyId);

}
