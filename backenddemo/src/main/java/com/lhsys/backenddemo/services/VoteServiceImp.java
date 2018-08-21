package com.lhsys.backenddemo.services;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.models.entities.Vote;
import com.lhsys.backenddemo.repositories.VoteRepository;
import com.lhsys.backenddemo.services.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImp implements VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImp(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote findVoteByUserAndPost(Post post, User user) {
        return voteRepository.findByPostAndUser(post, user);
    }

    @Override
    public List<Vote> findByPost(Post post) {
        return voteRepository.findByPost(post);
    }

    @Override
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public void deleteVote(Vote vote) {
        voteRepository.deleteById(vote.getId());
    }

    @Override
    public void downVote(Vote vote, User user, Post post) {

        if (vote == null) {
            Vote downvote = new Vote(-1, user, post);
            saveVote(downvote);
        } else if (vote.getScore() == 1) {
            vote.setScore(-1);
            saveVote(vote);
        } else {
            post.getVotes().remove(vote);
            deleteVote(vote);
        }
    }
    @Override
    public void upVote(Vote vote, User user, Post post) {

        if (vote == null) {
            Vote upvote = new Vote(1, user, post);
            saveVote(upvote);
        } else if (vote.getScore() == -1) {
            vote.setScore(1);
            saveVote(vote);
        } else {
            post.getVotes().remove(vote);
            deleteVote(vote);
        }
    }

    @Override
    public Vote findVoteByUserAndComment(User user, Comment comment) {
        return voteRepository.findByPostAndUser(user, comment);
    }
    @Override
    public void downVoteComment(Vote vote, User user, Comment comment) {

        if (vote == null) {
            Vote downvote = new Vote(-1, user, comment);
            saveVote(downvote);
        } else if (vote.getScore() == 1) {
            vote.setScore(-1);
            saveVote(vote);
        } else {
            comment.getVotes().remove(vote);
            deleteVote(vote);
        }
    }

    @Override
    public void upVoteComment(Vote vote, User user, Comment comment) {

        if (vote == null) {
            Vote upvote = new Vote(1, user, comment);
            saveVote(upvote);
        } else if (vote.getScore() == -1) {
            vote.setScore(1);
            saveVote(vote);
        } else {
            comment.getVotes().remove(vote);
            deleteVote(vote);
        }
    }
}
