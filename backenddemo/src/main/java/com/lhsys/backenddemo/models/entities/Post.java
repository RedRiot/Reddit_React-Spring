package com.lhsys.backenddemo.models.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2,message = "It has to be minimum than 2 characters")
    private String title;
    @Size(min = 6,message = "It has to minimum than 6 character")
    private String url;
    private String timestamp;
    private int score;
    private String reddit;

    public Post() {
        timestamp = String.valueOf(LocalDateTime.now());
        this.score = 0;
    }

    public Post(String title, String url ,String reddit) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.score = score;
        timestamp = String.valueOf(LocalDateTime.now()).replace('T', ' ');
        this.reddit= reddit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReddit() {
        return reddit;
    }

    public void setReddit(String reddit) {
        this.reddit = reddit;
    }
}
