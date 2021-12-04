package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl serviceToTest;
    private User testUser;
    private UserRoleEntity adminRole,userRole;

    @Mock
    private UserRepository mockedUserRepository;
    @Mock
    private UserRoleRepository mockedRoleRepository;
    @Mock
    private PasswordEncoder mockedPasswordEncoder;
    @Mock
    private OnlineUserServiceImpl mockedOnlineUserService;
    @Mock
    private ModelMapper mockedModelMapper;

    @BeforeEach
    void init() {
        serviceToTest = new UserServiceImpl(mockedUserRepository, mockedRoleRepository,
                mockedPasswordEncoder, mockedOnlineUserService, mockedModelMapper);

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        this.testUser = new User() {{
            setUsername("testU");
            setPassword("12345");
            setEmail("test@email.com");
            setFirstName("test");
            setLastName("testov");
            setRegisteredOn(Instant.now());
            setRoles(Set.of(userRole));
            setGuides(new ArrayList<>());
        }};
        System.out.println();
    }


    @Test
    void testToFindUserIdByUsername() {
        User testUser = this.testUser;

        Mockito.when(mockedUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        var actual = serviceToTest.findUserId(testUser.getUsername());

        Assertions.assertEquals(actual, testUser.getId());

    }

    @Test
    void testDeletionOfUserById() {
        User testUser = this.testUser;

        serviceToTest.deleteUser(testUser.getId());

        Mockito.verify(mockedUserRepository,Mockito.times(1)).deleteById(testUser.getId());
    }

    @Test
    void testMakeUserAnAdmin() {
        User testUser = this.testUser;

//
//        testUser.setRoles(Set.of(adminRole,userRole));
//
//        Mockito.when(mockedUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

var actual = testUser.getRoles().size();

        Assertions.assertEquals(actual, testUser.getRoles().size());
    }



}


