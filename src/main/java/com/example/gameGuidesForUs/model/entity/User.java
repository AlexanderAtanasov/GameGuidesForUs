package com.example.gameGuidesForUs.model.entity;

import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;

import javax.persistence.*;
import java.time.Instant;
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
    private Instant registeredOn;
    private List<Guide> guides;


    @OneToMany(mappedBy = "guideCreatedBy", cascade = CascadeType.REMOVE)
    public List<Guide> getGuides() {
        return guides;
    }

    public User setGuides(List<Guide> guides) {
        this.guides = guides;
        return this;
    }

    public Instant getRegisteredOn() {
        return registeredOn;
    }

    public User setRegisteredOn(Instant registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

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

    @OneToMany(mappedBy = "commentCreatedBy", cascade = CascadeType.REMOVE)
    public List<Comment> getAllUserComments() {
        return allUserComments;
    }

    public User setAllUserComments(List<Comment> allUserComments) {
        this.allUserComments = allUserComments;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public User setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Integer totalComments() {
       return getAllUserComments().size();
    }
}
