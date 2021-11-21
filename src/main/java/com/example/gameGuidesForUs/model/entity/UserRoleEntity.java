package com.example.gameGuidesForUs.model.entity;

import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{

    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
