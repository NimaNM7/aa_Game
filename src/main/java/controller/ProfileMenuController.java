package controller;

import controller.database.Database;
import model.Avatar;
import utils.PasswordProblem;
import utils.Validation;
import view.enums.ProfileMenuMessages;

import java.util.ArrayList;

public class ProfileMenuController {
    public static ArrayList<PasswordProblem> passwordProblems;

    public static ProfileMenuMessages change(String newUsername, String newPassword) {
        if (newUsername.equals(UserController.getCurrentUser().getUsername()) && newPassword.equals(UserController.getCurrentUser().getPassword()))
            return ProfileMenuMessages.THE_SAME;
        if (!Validation.isValidUsername(newUsername))
            return ProfileMenuMessages.USERNAME_INVALID;
        if ((passwordProblems = Validation.validatePassword(newPassword)).size() > 0)
            return ProfileMenuMessages.PASSWORD_WEAK;

        UserController.getCurrentUser().setUsername(newUsername);
        UserController.getCurrentUser().setPassword(newPassword);
        Database.saveUsers();
        return ProfileMenuMessages.DONE;
    }
}
