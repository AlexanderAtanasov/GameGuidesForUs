package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class OnlineUserServiceImplTest {

    private User testUser;
    private UserRoleEntity adminRole, userRole;
    private OnlineUserServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        serviceToTest = new OnlineUserServiceImpl(mockUserRepository);

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        testUser = new User();
        testUser.setUsername("TestMock")
                .setPassword("testmock")
                .setEmail("testmock@test.com")
                .setRoles(Set.of(adminRole,userRole));
    }



    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                ()->serviceToTest.loadUserByUsername("User-not-exist"));
    }

    @Test
    void testUserFound() {

        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

      var actual =  serviceToTest.loadUserByUsername(testUser.getUsername());

      Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
      String authorities = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining(", "));
      String expectedAuth = "ROLE_ADMIN, ROLE_USER";

      Assertions.assertEquals(expectedAuth,authorities);
    }
}
