package com.onlineshop.model.entity;

import com.onlineshop.model.enums.RoleEnum;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_entity_id", referencedColumnName = "id"))
    private Set<UserEntity> users;

    public Role(RoleEnum name) {
        this.name = name;
    }

    public Role() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}

