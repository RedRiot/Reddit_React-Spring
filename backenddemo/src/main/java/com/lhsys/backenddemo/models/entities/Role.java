package com.lhsys.backenddemo.models.entities;

import com.lhsys.backenddemo.models.entities.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Role {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy="roles")
    private Collection<User> users;

    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}