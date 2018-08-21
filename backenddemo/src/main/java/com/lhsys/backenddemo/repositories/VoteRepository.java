package com.lhsys.backenddemo.repositories;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.models.entities.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote,Long> {
    Vote findByPostAndUser(Post post, User user);

    List<Vote> findByPost(Post post);

    Vote findByPostAndUser(User user, Comment comment);
}
