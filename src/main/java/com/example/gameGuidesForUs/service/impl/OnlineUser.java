package com.example.gameGuidesForUs.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class OnlineUser extends User {


    public OnlineUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getUserIdentifier() {
        return getUsername();
    }





}
