package com.lhsys.backenddemo.models.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2,message = "It has to be minimum 2 characters")
    private String title;
    @Size(min = 6,message = "It has to minimum 6 character")
    private String url;
    private String timestamp;
    private int score;
    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY)
    List<Vote> votes;

    public Post() {
        timestamp = String.valueOf(LocalDateTime.now());
        this.score = 0;
    }

    public Post(String title, String url) {
        this.title = title;
        this.url = url;
        this.score = 0;
        timestamp = String.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
