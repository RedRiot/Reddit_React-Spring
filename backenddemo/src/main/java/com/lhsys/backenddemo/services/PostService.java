package com.lhsys.backenddemo.services;
import com.lhsys.backenddemo.models.entities.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostService {
    Post postSave(Post post);
    List<Post> getAllPost();
    Post getPostById(Long Id);
    Post postUpvote(Post post);
    Post postDownvote(Post post);
    void postDelete(Long id);
    List<Post> sortedByTrendingPosts();



}
