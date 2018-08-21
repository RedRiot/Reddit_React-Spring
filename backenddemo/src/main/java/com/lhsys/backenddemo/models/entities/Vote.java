package com.lhsys.backenddemo.models.entities;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int score;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Comment comment;

    public Vote(int score, User user, Post post) {
        this.score = score;
        this.user = user;
        this.post = post;
    }

    public Vote() {
    }

    public Vote(int score, User user, Comment comment) {
        this.score = score;
        this.user = user;
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }
}
