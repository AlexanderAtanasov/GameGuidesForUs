package com.example.gameGuidesForUs.model.binding;


import com.example.gameGuidesForUs.model.validator.UniqueEmail;
import com.example.gameGuidesForUs.model.validator.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20  characters.")
    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20  characters.")
    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    @NotBlank
    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters.")
    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @NotBlank
    @UniqueEmail
    @Email(message = "Enter valid email address.")
    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
