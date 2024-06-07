package com.example.lab13.Classes.UserModels;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class GetClientsFromDB {
    private DBHelper helper;

    public GetClientsFromDB(DBHelper helper) {
        this.helper = helper;
    }

    public List<User> GetUsers() {
        List<User> Users = new ArrayList<>();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor usersCursor = db.query(DBHelper.TABLE_CLIENTS, null, null, null, null, null, null);

        int idIndex = usersCursor.getColumnIndex(DBHelper.CLIENTS_ID);
        int nameIndex = usersCursor.getColumnIndex(DBHelper.CLIENTS_NAME);

        while (usersCursor.moveToNext()) {
            int idValue = usersCursor.getInt(idIndex);
            String nameValue = usersCursor.getString(nameIndex);

            Users.add(new User(idValue, nameValue));
        }
        usersCursor.close();
        return Users;
    }
}
