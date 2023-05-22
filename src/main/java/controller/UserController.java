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

    public static void removeUser(User user) {
        Database.getAllUsers().remove(user);
        Database.saveUsers();
    }

    public static ArrayList<User> getAllUsersSorted() {
        Database.loadUsers();
        ArrayList<User> allUsers = Database.getAllUsers();
        Collections.sort(allUsers, (user1, user2) -> {
            if (user1.getHighscore() == user2.getHighscore()) {
                return user1.getTotalTimeInHighscore() - user2.getTotalTimeInHighscore();
            } else {
                return user2.getHighscore() - user1.getHighscore();
            }
        });
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
