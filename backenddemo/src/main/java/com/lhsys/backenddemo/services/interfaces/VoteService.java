package com.lhsys.backenddemo.services.interfaces;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.models.entities.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {
    Vote findVoteByUserAndPost(Post post, User user);
    List<Vote> findByPost(Post post);
    Vote saveVote(Vote vote);
    void deleteVote(Vote vote);
    void downVote(Vote vote, User user, Post post);
    void upVote(Vote vote, User user, Post post);

    Vote findVoteByUserAndComment(User user, Comment comment);

    void downVoteComment(Vote vote, User user, Comment comment);

    void upVoteComment(Vote vote, User user, Comment comment);
}
