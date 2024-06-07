package com.example.lab13.Classes.SuppliesModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PutSuppliesToDB {
    private static List<Supplies> Supplies = new ArrayList<>();

    public static List<Supplies> GetSupplies() {
        if(Supplies.isEmpty()) {
            Supplies.add(new Supplies(0, 0, 0, 200, LocalDate.now().plus(1, ChronoUnit.DAYS)));
            Supplies.add(new Supplies(1, 1, 1, 200, LocalDate.now().plus(2, ChronoUnit.DAYS)));
            Supplies.add(new Supplies(2, 0, 2, 200, LocalDate.now().plus(3, ChronoUnit.DAYS)));
            Supplies.add(new Supplies(3, 1, 3, 200, LocalDate.now().plus(4, ChronoUnit.DAYS)));
        }

        return Supplies;
    }
}
