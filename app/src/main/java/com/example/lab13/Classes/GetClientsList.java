package com.example.lab13.Classes;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

public class GetClientsList {
    private static List<User> Users = new ArrayList<>();

    public static List<User> GetUsers() {
        Users.add(new User(0, "LLIypuK"));
        Users.add(new User(1, "HukuTa"));
        Users.add(new User(2, "Toxa"));

        return Users;
    }
}
