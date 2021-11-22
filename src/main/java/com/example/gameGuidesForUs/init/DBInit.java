package com.example.gameGuidesForUs.init;

import com.example.gameGuidesForUs.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private UserService userService;

    public DBInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
    }
}
