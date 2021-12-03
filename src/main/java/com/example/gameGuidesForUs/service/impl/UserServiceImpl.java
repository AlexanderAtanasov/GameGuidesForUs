package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.model.service.UserRegistrationServiceModel;
import com.example.gameGuidesForUs.model.view.UserViewModel;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.repository.UserRoleRepository;
import com.example.gameGuidesForUs.service.UserService;
import com.example.gameGuidesForUs.web.exception.ObjectNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OnlineUserServiceImpl onlineUserService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, OnlineUserServiceImpl onlineUserService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.onlineUserService = onlineUserService;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public void registerUserAndLogIn(UserRegistrationServiceModel userServiceModel) {
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        User newUser = new User();

        newUser.setUsername(userServiceModel.getUsername())
                .setEmail(userServiceModel.getEmail())
                .setFirstName(userServiceModel.getFirstName())
                .setLastName(userServiceModel.getLastName())
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setRegisteredOn(Instant.now())
                .setRoles(Set.of(userRole));

        userRepository.save(newUser);

        UserDetails principal = onlineUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);

    }

    @Override
    public void initializeUsersAndRoles() {
        initUserRoles();
        initAdminAndTestUser();
    }

    @Override
    public void initUserRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }


    @Override
    public void initAdminAndTestUser() {

        if (userRepository.count() == 0) {
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);

            User admin = new User();
            admin.setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setEmail("admin@project.com")
                    .setRegisteredOn(Instant.now())
                    .setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            User testUser = new User();
            testUser.setUsername("test")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Test")
                    .setLastName("Testov")
                    .setEmail("test@project.com")
                    .setRegisteredOn(Instant.now())
                    .setRoles(Set.of(userRole));
            userRepository.save(testUser);
        }
    }

    @Override
    public boolean checkIfUserIsAdmin(OnlineUser currentUser) {
        User user = userRepository.getByUsername(currentUser.getUserIdentifier());

        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).anyMatch(r -> r.equals(UserRoleEnum.ADMIN));
    }

    @Override
    public List<UserViewModel> getAllUsers(OnlineUser currentUser) {

        List<UserViewModel> userViewModels = userRepository.findAll()
                .stream().map(user -> {
                    UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);
                    userViewModel
                            .setCurrentUserId(userRepository
                                    .findByUsername(currentUser.getUserIdentifier()).orElse(null).getId());
                    return userViewModel;
                })
                .collect(Collectors.toList());

        return  userRepository.findAll()
                .stream().map(user -> modelMapper.map(user,UserViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public void makeUserAnAdmin(Long id) {
        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        User user = userRepository.findById(id).orElse(null);
        user.setRoles(Set.of(adminRole,userRole));
        userRepository.save(user);
    }

    @Override
    public void removeAdminRole(Long id) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        User user = userRepository.findById(id).orElseThrow(ObjectNotFound::new);
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }
}
