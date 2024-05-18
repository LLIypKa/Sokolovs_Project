package com.example.lab13;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab13.Classes.GetClientsList;
import com.example.lab13.Classes.MetalModels.GetMetalStructures;
import com.example.lab13.Classes.MetalModels.Metal;
import com.example.lab13.Classes.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    private static List<User> Users = GetClientsList.GetUsers();
    private static List<Metal> MetalStructures = GetMetalStructures.GetStuctures();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //Создаём клиентов
        for(User user : Users) {
            cv.put(DBHelper.CLIENTS_ID, user.ClientId);
            cv.put(DBHelper.CLIENTS_NAME, user.ClientName);
            db.insert(DBHelper.TABLE_CLIENTS, null, cv);
            cv.clear();
        }

        //создаём металлические структуры
        for(Metal structure : MetalStructures) {
            cv.put(DBHelper.METAL_STRUCTURES_ID, structure.metalStructureID);
            cv.put(DBHelper.METAL_STRUCTURES_NAME, structure.metalStructureName);
            cv.put(DBHelper.METAL_STRUCTURES_COLOR, structure.metalStructureColor);
            db.insert(DBHelper.TABLE_METAL_STRUCTURES, null, cv);
            cv.clear();
        }

        Cursor usersCursor = db.query(DBHelper.TABLE_CLIENTS, null, null, null, null, null, null);
        Cursor metalCursor = db.query(DBHelper.TABLE_METAL_STRUCTURES, null, null, null, null, null, null);

        //выводим данные
        while (usersCursor.move(1)) {
            Log.d("mLog", "User");
            int nameIndex = usersCursor.getColumnIndex(DBHelper.CLIENTS_NAME);
            String name = usersCursor.getString(nameIndex);
            Log.d("mLog", name);
        }

        while(metalCursor.move(1)) {
            Log.d("mLog", "Structure");
            int nameIndex = metalCursor.getColumnIndex(DBHelper.METAL_STRUCTURES_NAME);
            int colorIndex = metalCursor.getColumnIndex(DBHelper.METAL_STRUCTURES_COLOR);
            String name = metalCursor.getString(nameIndex);
            String color = metalCursor.getString(colorIndex);
            Log.d("mLog", String.format("name: %s, color: %s", name, color));
        }
    }
}