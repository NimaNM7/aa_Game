package controller;

import model.User;
import utils.PasswordProblem;
import utils.Validation;
import view.enums.SignUpMenuMessages;

import java.io.IOException;
import java.util.ArrayList;

public class SignUpMenuController {
    public static ArrayList<PasswordProblem> passwordProblems;
    public static SignUpMenuMessages signUp(String username, String password, String passwordConfirmation) throws IOException {
        if (username.length() == 0)
            return SignUpMenuMessages.EMPTY_USERNAME;
        if (password.length() == 0)
            return SignUpMenuMessages.EMPTY_PASSWORD;
        if (passwordConfirmation.length() == 0)
            return SignUpMenuMessages.EMPTY_CONFIRMATION;
        if (!Validation.isValidUsername(username))
            return SignUpMenuMessages.INVALID_USERNAME;
        if (UserController.findUserWithUsername(username) != null)
            return SignUpMenuMessages.USERNAME_EXISTS;
        if (Validation.validatePassword(password).size() != 0) {
            passwordProblems = Validation.validatePassword(password);
            return SignUpMenuMessages.WEAK_PASSWORD;
        }
        if (!password.equals(passwordConfirmation))
            return SignUpMenuMessages.PASSWORD_CONFIRMATION_WRONG;

        User signedUpUser = new User(username,password,false);
        UserController.addUser(signedUpUser);
        UserController.setCurrentUser(signedUpUser);
        return SignUpMenuMessages.SIGN_UP_SUCCESSFUL;
    }
}
