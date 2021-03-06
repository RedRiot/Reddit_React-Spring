package com.lhsys.backenddemo.repositories;

import com.lhsys.backenddemo.models.entities.Comment;
import com.lhsys.backenddemo.models.entities.Post;
import com.lhsys.backenddemo.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


    User findByName(String username);
    User findUserByComments(Comment comment);
    User findUserByPosts(Post post);
}
