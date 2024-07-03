package com.example.mobileutpapp.controller;

import android.content.Context;

import com.example.mobileutpapp.entity.Supply;
import com.example.mobileutpapp.model.SupplyModel;

import java.util.List;

public class SupplyController {
    private SupplyModel supplyModel;

    public SupplyController(Context context) {
        supplyModel = new SupplyModel(context);
    }

    public Supply addSupply(String insumo, double precio, double peso) {
        Supply supply = new Supply(insumo, (float) precio, (float) peso);
        int id = supplyModel.addSupply(supply);
        supply.setId(id);
        return supply;
    }

    public List<Supply> getAllSupply() {
        return supplyModel.getAllSupply();
    }

    public void updateSupply(int id, String insumo, double precio, double peso) {
        Supply supply = new Supply(id, insumo, (float) precio, (float) peso);
        supplyModel.updateSupply(supply);
    }

    public void deleteSupply(int id) {
        supplyModel.deleteSupply(id);
    }
}
