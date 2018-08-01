package com.lhsys.backenddemo.repositories;

import com.lhsys.backenddemo.models.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {

    List<Post> findAll();
    List<Post> findAllByOrderByScoreDesc();


}
