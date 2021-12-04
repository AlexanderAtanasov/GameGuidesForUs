package com.example.gameGuidesForUs.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserPasswordUpdateBindingModel {

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    public String getCurrentPassword() {
        return currentPassword;
    }

    public UserPasswordUpdateBindingModel setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    public String getNewPassword() {
        return newPassword;
    }

    public UserPasswordUpdateBindingModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public UserPasswordUpdateBindingModel setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
        return this;
    }
}
