package com.lhsys.backenddemo.controller;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.models.entities.Vote;
import com.lhsys.backenddemo.repositories.UserRepository;
import com.lhsys.backenddemo.services.interfaces.CommentService;
import com.lhsys.backenddemo.services.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteService voteService;

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

    @PostMapping("comment/{id}/upvote")
    public ResponseEntity upVote(@PathVariable Long id) {
        Comment comment = commentService.getCommentbyId(id);
        User user = userRepository.findUserByComments(comment);
        Vote vote = voteService.findVoteByUserAndComment(user, comment);
        voteService.upVoteComment(vote, user, comment);
        comment.setScore(comment.getVotes().stream().mapToInt(Vote::getScore).sum());
        return ResponseEntity.ok(comment);
    }
    @PostMapping("comment/{id}/downvote")
    public ResponseEntity downVote(@PathVariable Long id) {
        Comment comment = commentService.getCommentbyId(id);
        User user = userRepository.findUserByComments(comment);
        Vote vote = voteService.findVoteByUserAndComment(user, comment);
        voteService.downVoteComment(vote, user, comment);
        comment.setScore(comment.getVotes().stream().mapToInt(Vote::getScore).sum());
        return ResponseEntity.ok(comment);
    }



}
