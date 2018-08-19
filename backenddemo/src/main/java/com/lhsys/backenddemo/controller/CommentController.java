package com.lhsys.backenddemo.controller;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity postComment(@RequestBody Comment comment) {
        try {
            Comment newComment = commentService.saveComment(comment);
            return ResponseEntity.ok(newComment);
        } catch (Exception e) {
            return (ResponseEntity) ResponseEntity.noContent();
        }
    }

    @GetMapping("/comments")
    public ResponseEntity getAllComment(){
        return ResponseEntity.ok(commentService.allComment());
    }

    @PostMapping("comment/{id}/reply")
    public ResponseEntity reply(@PathVariable Long id, @RequestBody Comment comment) {
        Comment reply = commentService.saveReply(id, comment);
        return ResponseEntity.ok(comment);
    }



}
