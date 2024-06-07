package com.example.lab13.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab13.Classes.MetalModels.PutMetalStructuresToDB;
import com.example.lab13.Classes.MetalModels.Metal;
import com.example.lab13.Classes.OrdersModel.PutOrdersToDB;
import com.example.lab13.Classes.OrdersModel.Order;
import com.example.lab13.Classes.SupplierModel.PutSuppliersToDB;
import com.example.lab13.Classes.SupplierModel.Supplier;
import com.example.lab13.Classes.SuppliesModel.PutSuppliesToDB;
import com.example.lab13.Classes.SuppliesModel.Supplies;
import com.example.lab13.Classes.UserModels.PutClientsToDB;
import com.example.lab13.Classes.UserModels.User;
import com.example.lab13.Classes.WarehouseModel.PutWarehouseStocksToDB;
import com.example.lab13.Classes.WarehouseModel.Warehouse;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class StartupActivity extends AppCompatActivity {
    DBHelper helper;
    private static List<User> Users = PutClientsToDB.GetUsers();
    private static List<Metal> MetalStructures = PutMetalStructuresToDB.GetStructures();
    private static List<Warehouse> WarehouseStocks = PutWarehouseStocksToDB.GetWarehouseStocks();
    private static List<Supplier> Suppliers = PutSuppliersToDB.GetSuppliers();
    private static List<com.example.lab13.Classes.SuppliesModel.Supplies> Supplies = PutSuppliesToDB.GetSupplies();
    private static List<Order> Orders = PutOrdersToDB.GetOrders();

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

        cv = new ContentValues();
        //создаём металлические структуры
        for(Metal structure : MetalStructures) {
            cv.put(DBHelper.METAL_STRUCTURES_ID, structure.metalStructureID);
            cv.put(DBHelper.METAL_STRUCTURES_NAME, structure.metalStructureName);
            cv.put(DBHelper.METAL_STRUCTURES_COLOR, structure.metalStructureColor);
            db.insert(DBHelper.TABLE_METAL_STRUCTURES, null, cv);
        }
        cv.clear();

        cv = new ContentValues();
        for(Warehouse wh : WarehouseStocks) {
            cv.put(DBHelper.WAREHOUSES_STOCKS_ID, wh.RecordId);
            cv.put(DBHelper.WAREHOUSES_STOCKS_METAL_ID, wh.MetalStructureId);
            cv.put(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT, wh.MetalStructureCount);
            cv.put(DBHelper.WAREHOUSES_STOCKS_UPDATE_DATE, wh.UpdateDate);
            db.insert(DBHelper.TABLE_WAREHOUSES_STOCKS, null, cv);
        }
        cv.clear();

        cv = new ContentValues();
        for(Supplier sup : Suppliers) {
            cv.put(DBHelper.SUPPLIERS_ID, sup.SupplierId);
            cv.put(DBHelper.SUPPLIERS_NAME, sup.SupplierName);
            db.insert(DBHelper.TABLE_SUPPLIERS, null, cv);
        }
        cv.clear();

        cv = new ContentValues();
        for(Supplies sup : Supplies) {
            cv.put(DBHelper.SUPPLIES_ID, sup.ID);
            cv.put(DBHelper.SUPPLIES_SUPPLIER_ID, sup.SupplierID);
            cv.put(DBHelper.SUPPLIES_METAL_ID, sup.MetalID);
            cv.put(DBHelper.SUPPLIES_METAL_COUNT, sup.MetalCount);
            cv.put(DBHelper.SUPPLIES_DATE, sup.Date.toString());
            db.insert(DBHelper.TABLE_SUPPLIES, null, cv);
        }
        cv.clear();

        cv = new ContentValues();
        for(Order order : Orders) {
            cv.put(DBHelper.ORDERS_ID, order.ID);
            cv.put(DBHelper.ORDERS_CLIENT_ID, order.ClientID);
            cv.put(DBHelper.ORDERS_METAL_ID, order.MetalID);
            cv.put(DBHelper.ORDERS_METAL_COUNT, order.MetalCount);
            cv.put(DBHelper.ORDERS_DATE, order.Date.toString());
            db.insert(DBHelper.TABLE_ORDERS, null, cv);
        }
        cv.clear();

        Log.d("info", "created database");
        Log.d("info", "route to main page");
        startActivity(new Intent(StartupActivity.this, MainActivity.class));
    }
}
