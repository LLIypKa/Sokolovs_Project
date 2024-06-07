package com.example.lab13.Classes.SupplierModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GetSupplier {
    private static List<Supplier> SupplierList = new ArrayList<>();

    public static List<Supplier> GetSuppliers() {
        if(SupplierList.isEmpty()) {
            SupplierList.add(new Supplier(0, "KoMTTaHu9I LLIypuKa"));
            SupplierList.add(new Supplier(1, "HuKuTKuHbI MeTaJLbI"));
        }

        return SupplierList;
    }
}
