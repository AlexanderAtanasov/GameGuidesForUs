package com.example.gameGuidesForUs.model.entity;

import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private List<Comment> allUserComments = new ArrayList<>();
    private Set<UserRoleEntity> roles = new HashSet<>();

    public User() {
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @OneToMany
    public List<Comment> getAllUserComments() {
        return allUserComments;
    }

    public User setAllUserComments(List<Comment> allUserComments) {
        this.allUserComments = allUserComments;
        return this;
    }

    @OneToMany
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public User setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
