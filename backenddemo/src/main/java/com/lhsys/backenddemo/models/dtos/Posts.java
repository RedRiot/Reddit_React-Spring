package com.lhsys.backenddemo.models.dtos;

import com.lhsys.backenddemo.models.entities.Post;

import java.util.List;

public class Posts {
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
