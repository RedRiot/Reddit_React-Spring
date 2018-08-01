package com.lhsys.backenddemo.controller;

import com.lhsys.backenddemo.models.dtos.Posts;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<?> getAllPosts(){
        Posts posts= new Posts();
        posts.setPosts(postService.getAllPost());
       return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(id));
    }

    @GetMapping(value = "/trending")
    public ResponseEntity<?> trendingPost(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.sortedByTrendingPosts());
    }


    @PostMapping(value = "/post")
    public ResponseEntity postAPost(@Valid @RequestBody Post post) {
        Post newPost = postService.postSave(post);
        if (newPost == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/posts/{id}/upvote")
    public ResponseEntity upVotePost(@PathVariable("id") long id){
       Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.noContent().build();
        }
       return ResponseEntity.status(HttpStatus.OK).body(postService.postSave(postService.postUpvote(post)));
    }

    @PutMapping(value = "/posts/{id}/downvote")
    public ResponseEntity downVotePost(@PathVariable("id") long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body( postService.postSave(postService.postDownvote(post)));
    }
    @DeleteMapping(value = "posts/{id}")
    public ResponseEntity deletePost(@PathVariable("id") long id) {
        postService.postDelete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/posts/{id}")
    public ResponseEntity updatePost(@PathVariable("id") long id,@Valid @RequestBody Post post) throws Exception {
        Post updatedPost = postService.getPostById(id);
        updatedPost.setTitle(post.getTitle());
        updatedPost.setUrl(post.getUrl());
        updatedPost.setReddit(post.getReddit());
        postService.postSave(updatedPost);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

}
