package com.example.lab13.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab13.Classes.MetalModels.GetMetalStructuresFromDB;
import com.example.lab13.Classes.MetalModels.Metal;
import com.example.lab13.Classes.MetalModels.MetalAdapter;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class MetalListActivity extends AppCompatActivity {
    DBHelper dbHelper;
    List<Metal> metals;
    ListView metalsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_list);

        dbHelper = new DBHelper(this);
        metals = new GetMetalStructuresFromDB(dbHelper).GetStructuresFromDB();

        metalsListView = findViewById(R.id.metalsList);
        MetalAdapter metalAdapter = new MetalAdapter(this, metals, dbHelper);
        metalsListView.setAdapter(metalAdapter);
    }
}
