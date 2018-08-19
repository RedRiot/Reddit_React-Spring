package com.lhsys.backenddemo.models.entities;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class Comment {
    @ManyToOne
    private Comment parent;
    private String details;
    @OneToMany
    private List<Comment> comments;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
