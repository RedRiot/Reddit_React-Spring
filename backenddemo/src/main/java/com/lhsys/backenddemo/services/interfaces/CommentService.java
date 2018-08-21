package com.lhsys.backenddemo.services.interfaces;

import com.lhsys.backenddemo.models.entities.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment saveComment(Comment comment);
    List<Comment> allComment();
    Comment saveReply(Long id, Comment comment);

    Comment getCommentbyId(Long id);
}
