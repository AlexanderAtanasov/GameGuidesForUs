package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.UserRegistrationBindingModel;
import com.example.gameGuidesForUs.model.service.UserRegistrationServiceModel;
import com.example.gameGuidesForUs.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
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


    @ModelAttribute
    public UserRegistrationBindingModel userRegistrationBindingModel() {
        return new UserRegistrationBindingModel();
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
    public String allUsers(Model model) {
        model.addAttribute("usersInfo",userService.getAllUsers());
        return "users-all";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return "redirect:/users/all";
    }

    @PatchMapping("{id}/addAdminRole")
    public String makeAdmin(@PathVariable Long id) {

        userService.makeUserAnAdmin(id);
        return "redirect:/users/all";
    }

    @PatchMapping("{id}/removeAdminRole")
    public String demoteFromAdmin(@PathVariable Long id) {

        userService.removeAdminRole(id);
        return "redirect:/users/all";
    }


    //TODO ADMIN SHOULD HAVE ACCESS TO ALL USERS AND BE ABLE TO SWAP THEIR ROLES, PUT IN DROPDOWN
    // EVENT COULD BE CHECK IF USER COMMENTS AND THE MORE COMMENTS HE HAS, SOME REWARD ETC.

}
