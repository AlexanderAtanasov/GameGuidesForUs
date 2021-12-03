package com.example.gameGuidesForUs.model.view;

import com.example.gameGuidesForUs.model.entity.UserRoleEntity;

import java.time.Instant;
import java.util.List;

public class UserViewModel {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Long id;
    private List<UserRoleEntity> roles;
    private Instant registeredOn;
    private Long currentUserId;

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Instant getRegisteredOn() {
        return registeredOn;
    }

    public UserViewModel setRegisteredOn(Instant registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public UserViewModel setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
        return this;
    }
}
