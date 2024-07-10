package com.example.mobileutpapp.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.entity.Supply;

import java.util.ArrayList;
import java.util.List;

public class SupplyModel {

    private DatabaseHelper dbHelper;

    public SupplyModel(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public int addSupply(Supply supplies) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("insumo", supplies.getInsumo());
        values.put("precio", supplies.getPrecio());
        values.put("peso", supplies.getPeso());
        db.insert("Insumos", null, values);
        db.close();
        return 0;
    }

    public Supply getSupplyById(int supplyId) {
        SQLiteDatabase db = dbHelper.openReadableDatabase();
        Cursor cursor = db.query("Insumos",
                null,
                "id" + " = ?",
                new String[]{String.valueOf(supplyId)},
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
            Supply supply = new Supply(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("insumo")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("precio")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("peso"))
            );
            cursor.close();
            db.close();
            return supply;
        } else {
            db.close();
            return null;
        }
    }

    public List<Supply> getAllSupply() {
        List<Supply> supplies = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.openReadableDatabase();
            cursor = db.query("Insumos", null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String insumo = cursor.getString(cursor.getColumnIndex("insumo"));
                    @SuppressLint("Range") float precio = cursor.getFloat(cursor.getColumnIndex("precio"));
                    @SuppressLint("Range") float peso = cursor.getFloat(cursor.getColumnIndex("peso"));
                    supplies.add(new Supply(id, insumo, precio, peso));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return supplies;
    }

    public void updateSupply(Supply supplies) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("insumo", supplies.getInsumo());
            values.put("precio", supplies.getPrecio());
            values.put("peso", supplies.getPeso());
            db.update("Insumos", values, "id" + " = ?", new String[]{String.valueOf(supplies.getId())});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void deleteSupply(int id) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openWritableDatabase();
            db.delete("Insumos", "id" + " = ?", new String[]{String.valueOf(id)});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
