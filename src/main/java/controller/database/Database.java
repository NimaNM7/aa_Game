package controller.database;

import model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Database {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static final String DATABASE_PATH = "C:\\Users\\ASUS\\Documents\\University\\AP\\tasks\\task2\\aaGame\\src\\main\\resources\\Database";
    private static final String usersDatabasePath = DATABASE_PATH + "\\users.json";
    private static Gson gson = new Gson();

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        Database.allUsers = allUsers;
    }

    public static void saveUsers() {
        try {
            FileWriter fileWriter = new FileWriter(usersDatabasePath);
            String json = new Gson().toJson(allUsers);
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
            if (savedUsers != null) setAllUsers(savedUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}