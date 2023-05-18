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
            if (user1.getScore() == user2.getScore()) {
                return user1.getTotalTime() - user2.getTotalTime();
            } else {
                return user2.getScore() - user1.getScore();
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
