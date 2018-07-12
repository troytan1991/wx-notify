package com.troytan.notify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.troytan.notify.domain.Comment;
import com.troytan.notify.dto.CommentDto;
import com.troytan.notify.repository.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public void createComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<CommentDto> listComment(Integer notifyId) {
        return commentMapper.listByNotifyId(notifyId);
    }

}
