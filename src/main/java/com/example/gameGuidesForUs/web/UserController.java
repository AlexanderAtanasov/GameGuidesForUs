package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.UserPasswordUpdateBindingModel;
import com.example.gameGuidesForUs.model.binding.UserRegistrationBindingModel;
import com.example.gameGuidesForUs.model.service.UserRegistrationServiceModel;
import com.example.gameGuidesForUs.service.UserService;
import com.example.gameGuidesForUs.service.impl.OnlineUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/logout-timeout")
    public String timeout() {

        return "redirect:login";
    }

    @GetMapping("/login")
    public String log() {
        return "login";
    }

    @PostMapping("/login-error")
    public String loginFailed(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("bad_credentials", true)
                .addFlashAttribute("username", username);
        return "redirect:login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegistrationBindingModel.getPassword()
                .equals(userRegistrationBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel",
                    bindingResult);
            return "redirect:register";
        }

        UserRegistrationServiceModel userServiceModel =
                modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);
        userService.registerUserAndLogIn(userServiceModel);
        return "redirect:/";
    }


    @GetMapping("/all")
    public String allUsers(Model model,
                           @AuthenticationPrincipal OnlineUser currentUser) {
        model.addAttribute("usersInfo", userService.getAllUsers(currentUser));
        return "users-all";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id,
                             @AuthenticationPrincipal OnlineUser onlineUser) {
        if (id == 1) {
            throw new RuntimeException();
        }
        if (Objects.equals(userService.findUserId(onlineUser.getUserIdentifier()), id)) {
            userService.deleteUser(id);
            return "redirect:/users/logout";
        }

        userService.deleteUser(id);
        return "redirect:/users/all";
    }

    @PatchMapping("{id}/addAdminRole")
    public String makeAdmin(@PathVariable Long id) {

        userService.makeUserAnAdmin(id);
        return "redirect:/users/all";
    }

    @PatchMapping("{id}/removeAdminRole")
    public String demoteFromAdmin(@PathVariable Long id,
                                  @AuthenticationPrincipal OnlineUser onlineUser) {
        if (id == 1) {
            throw new RuntimeException();
        }
        if (Objects.equals(userService.findUserId(onlineUser.getUserIdentifier()), id)) {
            userService.removeAdminRole(id);
            return "redirect:/users/logout";
        }
        userService.removeAdminRole(id);
        return "redirect:/users/all";
    }


    @GetMapping("/changepassword")
    public String passwordChange(Model model) {
        if (!model.containsAttribute("passwordDoesNotMatchTheOldOne")) {
            model.addAttribute("passwordDoesNotMatchTheOldOne", false);
        }
        return "password-change";

    }

    @PostMapping("/changepassword")
    public String passwordChangeConfirm(@Valid UserPasswordUpdateBindingModel userPasswordUpdateBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                        @AuthenticationPrincipal OnlineUser onlineUser) {

        if (onlineUser != null) {

            if (bindingResult.hasErrors() || !userPasswordUpdateBindingModel.getNewPassword()
                    .equals(userPasswordUpdateBindingModel.getConfirmNewPassword())) {
                redirectAttributes.addFlashAttribute("userPasswordUpdateBindingModel",
                                userPasswordUpdateBindingModel)
                        .addFlashAttribute("org.springframework.validation.BindingResult" +
                                        ".userPasswordUpdateBindingModel",
                                bindingResult);


                return "redirect:changepassword";

            }
            if (!userService.checkIfPasswordMatch(onlineUser,
                    userPasswordUpdateBindingModel.getNewPassword())) {
                redirectAttributes.addFlashAttribute("userPasswordUpdateBindingModel",
                                userPasswordUpdateBindingModel)
                        .addFlashAttribute("passwordDoesNotMatchTheOldOne", true);
                return "redirect:changepassword";
            }

            System.out.println();
            userService.changePassword(onlineUser, userPasswordUpdateBindingModel.getNewPassword());

        }
        return "redirect:/users/logout";
    }


    @ModelAttribute
    public UserRegistrationBindingModel userRegistrationBindingModel() {
        return new UserRegistrationBindingModel();
    }

    @ModelAttribute
    public UserPasswordUpdateBindingModel userPasswordUpdateBindingModel() {
        return new UserPasswordUpdateBindingModel();
    }
}
