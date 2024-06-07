package com.example.lab13.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab13.Classes.SuppliesModel.GetSuppliesFromDB;
import com.example.lab13.Classes.SuppliesModel.Supplies;
import com.example.lab13.Classes.SuppliesModel.SuppliesAdapter;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class SupplyListActivity extends AppCompatActivity {
    DBHelper dbHelper;
    List<Supplies> supplies;
    ListView suppliesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_list);

        dbHelper = new DBHelper(this);
        supplies = new GetSuppliesFromDB(dbHelper).GetSupplies();

        suppliesListView = findViewById(R.id.suppliesList);
        SuppliesAdapter suppliesAdapter = new SuppliesAdapter(this, supplies, dbHelper);
        suppliesListView.setAdapter(suppliesAdapter);
    }
}
