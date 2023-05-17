package controller;

import controller.database.Database;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserController {
    private static User currentUser;

    public static void addUser(User user) {
        Database.getAllUsers().add(user);
        Database.saveUsers();
    }

    public static ArrayList<User> getAllUsersSorted() {
        ArrayList<User> allUsers = Database.getAllUsers();
        Collections.sort(allUsers, Comparator.comparingInt(User::getScore));
        return allUsers;
    }

    public static User findUserWithUsername(String username) {
        Database.loadUsers();
        for (User user : Database.getAllUsers()) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserController.currentUser = currentUser;
    }
}
