package controller.database;

import javafx.scene.layout.Pane;
import model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import view.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Database {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<User> allUsersBackUp = new ArrayList<>();
    private static final String DATABASE_PATH = "C:\\Users\\ASUS\\Documents\\University\\AP\\tasks\\task2\\aaGame\\src\\main\\resources\\Database";
    private static final String usersDatabasePath = DATABASE_PATH + "\\users.json";
    private static Gson gson = new Gson();

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        Database.allUsers = allUsers;
    }

    public static ArrayList<User> getAllUsersBackUp() {
        return allUsersBackUp;
    }

    public static void setAllUsersBackUp(ArrayList<User> allUsersBackUp) {
        Database.allUsersBackUp = allUsersBackUp;
    }

    public static void saveUsers() {
        try {
            System.out.println("im saving to json:");
            System.out.println(allUsers);
            for (User user : allUsers) {
                System.out.println("name: " + user.getUsername());
                System.out.println("highScore: " + user.getHighscore());
            }
            if (allUsers.size() == 0) {
                setAllUsers(allUsersBackUp);
                return;
            }
            FileWriter fileWriter = new FileWriter(usersDatabasePath);
            String json = new Gson().toJson(allUsers);
            setAllUsersBackUp(allUsers);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUsers() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(usersDatabasePath)));
            ArrayList<User> savedUsers = new Gson().fromJson(json,new TypeToken<List<User>>(){}.getType());
            if (savedUsers != null) {
                setAllUsers(savedUsers);
                setAllUsersBackUp(savedUsers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}