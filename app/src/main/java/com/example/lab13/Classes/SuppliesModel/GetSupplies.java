package com.example.lab13.Classes.SuppliesModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GetSupplies {
    private static List<Supplies> Supplies = new ArrayList<>();

    public static List<Supplies> GetSupplies() {
        Supplies.add(new Supplies(0, 0, 1, 200, LocalDate.now().plus(1, ChronoUnit.DAYS)));
        Supplies.add(new Supplies(0, 1, 2, 200, LocalDate.now().plus(2, ChronoUnit.DAYS)));
        Supplies.add(new Supplies(0, 0, 3, 200, LocalDate.now().plus(3, ChronoUnit.DAYS)));
        Supplies.add(new Supplies(0, 1, 4, 200, LocalDate.now().plus(4, ChronoUnit.DAYS)));

        return Supplies;
    }
}
