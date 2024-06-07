package com.example.lab13.Classes.UserModels;

import java.util.ArrayList;
import java.util.List;

public class PutClientsToDB {
    private static List<User> Users = new ArrayList<>();

    public static List<User> GetUsers() {
        if(Users.isEmpty()) {
            Users.add(new User(0, "LLIypuK"));
            Users.add(new User(1, "HukuTa"));
            Users.add(new User(2, "Toxa"));
        }

        return Users;
    }
}
