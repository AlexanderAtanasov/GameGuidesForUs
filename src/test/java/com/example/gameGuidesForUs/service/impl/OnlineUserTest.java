package com.example.gameGuidesForUs.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OnlineUserTest {

    private OnlineUser servToTest;
    private String username = "testUser";


    @BeforeEach
    void init() {
        servToTest = new OnlineUser(username,"testPass",new ArrayList<>());
    }

    @Test
    void testOnlineUserGetIdentifier() {
        String userIdentifier = servToTest.getUserIdentifier();

        Assertions.assertEquals(username,userIdentifier);
    }

}