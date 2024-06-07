package com.example.lab13.Classes.SupplierModel;

import java.util.ArrayList;
import java.util.List;

public class PutSuppliersToDB {
    private static List<Supplier> SupplierList = new ArrayList<>();

    public static List<Supplier> GetSuppliers() {
        if(SupplierList.isEmpty()) {
            SupplierList.add(new Supplier(0, "KoMTTaHu9I LLIypuKa"));
            SupplierList.add(new Supplier(1, "HuKuTKuHbI MeTaJLbI"));
        }

        return SupplierList;
    }
}
