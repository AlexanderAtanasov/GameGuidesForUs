package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.service.UserRegistrationServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    boolean isUserNameFree(String username);

    boolean isEmailFree(String email);

    void registerUserAndLogIn(UserRegistrationServiceModel userServiceModel);

    void initializeUsersAndRoles();

    void initUserRoles();

    void initAdminAndTestUser();

}
