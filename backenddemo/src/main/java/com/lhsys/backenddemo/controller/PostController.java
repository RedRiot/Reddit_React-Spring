package com.lhsys.backenddemo.controller;

import com.lhsys.backenddemo.models.dtos.PostsDto;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.models.entities.Vote;
import com.lhsys.backenddemo.repositories.UserRepository;
import com.lhsys.backenddemo.services.interfaces.PostService;
import com.lhsys.backenddemo.services.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;
    private final VoteService voteService;

    @Autowired
    public PostController(PostService postService, UserRepository userRepository, VoteService voteService) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.voteService = voteService;
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<?> getAllPosts(){
        PostsDto postsDto = new PostsDto();
        postsDto.setPosts(postService.getAllPost());
       return ResponseEntity.status(HttpStatus.OK).body(postsDto);
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
        User user = userRepository.findUserByPosts(post);
        Vote vote = voteService.findVoteByUserAndPost(post, user);
        voteService.downVote(vote,user,post);
        post.setScore(voteService.findByPost(post).stream().mapToInt(Vote::getScore).sum());
       return ResponseEntity.status(HttpStatus.OK).body(postService.postSave(post));
    }

    @PutMapping(value = "/posts/{id}/downvote")
    public ResponseEntity downVotePost(@PathVariable("id") long id) {
        Post post = postService.getPostById(id);
        User user = userRepository.findUserByPosts(post);
        Vote vote = voteService.findVoteByUserAndPost(post, user);
        voteService.downVote(vote,user,post);
        post.setScore(voteService.findByPost(post).stream().mapToInt(Vote::getScore).sum());

        return ResponseEntity.status(HttpStatus.OK).body( postService.postSave(post));
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
        postService.postSave(updatedPost);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

}
