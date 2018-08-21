package com.lhsys.backenddemo.services;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.repositories.CommentRepository;
import com.lhsys.backenddemo.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    private  CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> allComment() {


        List<Comment> commentList = null;
        try {
            commentList = (List<Comment>) commentRepository.findByParentIsNull();
        } catch (Exception e) {
            System.err.println(e);
        }
        return commentList;
    }

    @Override
    public Comment saveReply(Long id, Comment comment) {
        Comment parent = null;
        try {
            String body = comment.getBody();
            parent = commentRepository.findById(id).get();
            Comment newComment = new Comment(parent, body);
            commentRepository.save(newComment);
        } catch (Exception e) {
            System.err.println(e);
        }
        return parent;
    }

    @Override
    public Comment getCommentbyId(Long id) {
        return commentRepository.findById(id).get();
    }

}
