package com.lhsys.backenddemo.repositories;

import com.lhsys.backenddemo.models.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByParentIsNull();

}
