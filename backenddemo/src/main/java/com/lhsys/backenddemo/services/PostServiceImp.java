package com.lhsys.backenddemo.services;

import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.repositories.PostRepository;
import com.lhsys.backenddemo.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private static final String dbErrorMessage = "Unabvle to reach DB.";
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post postSave(Post post) {
        Post savedPost = null;
        try {
            savedPost = postRepository.save(post);
        } catch (Exception e) {
            System.err.println(dbErrorMessage);
        }
        return savedPost;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = null;
        try {
            posts = postRepository.findAll();
        } catch (Exception e) {

        }
        return posts;
    }

    @Override
    public Post getPostById(Long id) {
        Post post = null;
        try {
            post = postRepository.findById(id).get();
        } catch (Exception e) {
            System.err.println(dbErrorMessage);
        }
        return post;
    }

    @Override
    public Post postUpvote(Post post) {
        post.setScore(post.getScore() + 1);
       return post;
    }

    @Override
    public Post postDownvote(Post post) {
        post.setScore(post.getScore()-1);
        return post;
    }

    @Override
    public void postDelete(Long id) {
        try {
                postRepository.deleteById(id);
            } catch (Exception e) {
                System.err.println(dbErrorMessage);
        }
    }

    @Override
   public List<Post> sortedByTrendingPosts() {
        return postRepository.findAllByOrderByScoreDesc();
    }

}
