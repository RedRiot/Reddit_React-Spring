package com.lhsys.backenddemo.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonBackReference
    @ManyToOne
    private Comment parent;
    private String body;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "parent")
    private Set<Comment> comments = new HashSet<>();

  /*  @ManyToOne
    private Post post;
    @ManyToOne
    private User user;*/

    public Comment() {
    }

    public Comment( String body) {
        this.parent = null;
        this.body = body;
    }

    public Comment(Comment parent, String body) {

        if ( parent == null )
        {
            throw new IllegalArgumentException( "parent required" );
        }
        this.parent = parent;
        this.body = body;
        registerInParentsChilds();
    }

    private void registerInParentsChilds() {
        parent.comments.add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Comment addChild(String body) {
        return new Comment(body);
    }
}
