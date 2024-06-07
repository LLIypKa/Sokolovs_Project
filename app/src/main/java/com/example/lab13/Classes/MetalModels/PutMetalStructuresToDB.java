package com.example.lab13.Classes.MetalModels;

import java.util.ArrayList;
import java.util.List;

public class PutMetalStructuresToDB {
    private static List<Metal> MetalStructures = new ArrayList<>();

    public static List<Metal> GetStructures() {
        if(MetalStructures.isEmpty()) {
            MetalStructures.add(new Metal(0, "Алюминиевый уголок", "Серый металический"));
            MetalStructures.add(new Metal(1, "Лист железа (б)", "Белый"));
            MetalStructures.add(new Metal(2, "Лист железа (с)", "Синий"));
            MetalStructures.add(new Metal(3, "Лист железа (к)", "Красный"));
        }

        return MetalStructures;
    }
}
