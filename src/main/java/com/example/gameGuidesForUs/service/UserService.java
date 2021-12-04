package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.service.UserRegistrationServiceModel;
import com.example.gameGuidesForUs.model.view.UserViewModel;
import com.example.gameGuidesForUs.service.impl.OnlineUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    boolean isUserNameFree(String username);

    boolean isEmailFree(String email);

    void registerUserAndLogIn(UserRegistrationServiceModel userServiceModel);

    void initializeUsersAndRoles();

    void initUserRoles();

    void initAdminAndTestUser();

    boolean checkIfUserIsAdmin(OnlineUser currentUser);

    List<UserViewModel> getAllUsers(OnlineUser currentUser);

    void deleteUser(Long id);

    void makeUserAnAdmin(Long id);

    void removeAdminRole(Long id);

    Long findUserId(String userIdentifier);

    void changePassword(OnlineUser onlineUser, String newPassword);

    boolean checkIfPasswordMatch(OnlineUser onlineUser, String newPassword);
}
