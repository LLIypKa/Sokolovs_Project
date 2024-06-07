package com.example.lab13.Classes.WarehouseModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PutWarehouseStocksToDB {
    private static List<Warehouse> WarehouseStocks = new ArrayList<>();

    public static List<Warehouse> GetWarehouseStocks() {
        if(WarehouseStocks.isEmpty()) {
            WarehouseStocks.add(new Warehouse(0, 0, 100, LocalDateTime.now().toString()));
            WarehouseStocks.add(new Warehouse(1, 1, 100, LocalDateTime.now().toString()));
            WarehouseStocks.add(new Warehouse(2, 2, 200, LocalDateTime.now().toString()));
            WarehouseStocks.add(new Warehouse(3, 3, 200, LocalDateTime.now().toString()));
        }

        return WarehouseStocks;
    }
}
