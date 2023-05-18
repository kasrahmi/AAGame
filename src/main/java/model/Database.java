package model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Database {
    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Database.users = users;
    }

    public static void saveUsers() {
        try {
            FileWriter fileWriter = new FileWriter("./src/main/resources/database/users.json");
            String gson = new Gson().toJson(getUsers());
            fileWriter.write(gson);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUsers() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/database/users.json")));
            ArrayList<User> savedUsers;
            savedUsers = new Gson().fromJson(json, new TypeToken<List<User>>() {}.getType());
            if (savedUsers != null) setUsers(savedUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUserName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) return user;
        }
        return null;
    }
}
