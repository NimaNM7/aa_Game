package controller;

import model.User;

public class UserController {
    private static User currentUser;
    public static User findUserWithUsername(String username) {
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserController.currentUser = currentUser;
    }
}
