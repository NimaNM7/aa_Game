package controller;

import utils.PasswordProblem;
import utils.Validation;
import view.enums.SignUpMenuMessages;

import java.util.ArrayList;

public class SignUpController {
    public static ArrayList<PasswordProblem> passwordProblems;
    public static SignUpMenuMessages signUp(String username, String password, String passwordConfirmation) {
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

        //TODO real signing up
        return SignUpMenuMessages.SIGN_UP_SUCCESSFUL;
    }
}
