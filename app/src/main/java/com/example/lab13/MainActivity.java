package com.example.lab13;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab13.Classes.UserModels.GetClientsList;
import com.example.lab13.Classes.MetalModels.GetMetalStructures;
import com.example.lab13.Classes.MetalModels.Metal;
import com.example.lab13.Classes.SupplierModel.GetSupplier;
import com.example.lab13.Classes.SupplierModel.Supplier;
import com.example.lab13.Classes.UserModels.User;
import com.example.lab13.Classes.WarehouseModel.GetWarehouseList;
import com.example.lab13.Classes.WarehouseModel.Warehouse;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    private static List<User> Users = GetClientsList.GetUsers();
    private static List<Metal> MetalStructures = GetMetalStructures.GetStuctures();
    private static List<Warehouse> WarehouseStocks = GetWarehouseList.GetWarehouseStocks();
    private static List<Supplier> Suppliers = GetSupplier.GetSuppliers();

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

        for(Warehouse wh : WarehouseStocks) {
            cv.put(DBHelper.WAREHOUSES_STOCKS_ID, wh.RecordId);
            cv.put(DBHelper.WAREHOUSES_STOCKS_METAL_ID, wh.MetalStructureId);
            cv.put(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT, wh.MetalStructureCount);
            cv.put(DBHelper.WAREHOUSES_STOCKS_UPDATE_DATE, wh.UpdateDate);
            db.insert(DBHelper.TABLE_WAREHOUSES_STOCKS, null, cv);
            cv.clear();
        }

        for(Supplier sup : Suppliers) {
            cv.put(DBHelper.SUPPLIERS_ID, sup.SupplierId);
            cv.put(DBHelper.SUPPLIERS_NAME, sup.SupplierName);
            db.insert(DBHelper.TABLE_SUPPLIERS, null, cv);
        }
    }
}