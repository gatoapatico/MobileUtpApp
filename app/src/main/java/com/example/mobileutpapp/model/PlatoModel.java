package com.example.mobileutpapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.entity.Plato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlatoModel {
    private static final String TABLE_PLATOS = "Platos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_IMAGEN = "imagen";
    private DatabaseHelper dbHelper;

    public PlatoModel(Context context) {
        dbHelper = new DatabaseHelper(context);
        // Forzar la creación de la base de datos
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        db.close();
    }

    public void addPlato(Plato plato) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, plato.getNombre());
        values.put(COLUMN_DESCRIPCION, plato.getDescripcion());
        values.put(COLUMN_PRECIO, plato.getPrecio());
        values.put(COLUMN_IMAGEN, plato.getImagen());
        db.insert(TABLE_PLATOS, null, values);
        db.close();
    }

    public Plato getPlatoById(int platoId) {
        SQLiteDatabase db = dbHelper.openReadableDatabase();
        Cursor cursor = db.query(TABLE_PLATOS,
                null,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(platoId)},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            Plato plato = new Plato(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECIO)),
                    cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN))
            );
            cursor.close();
            db.close();
            return plato;
        } else {
            db.close();
            return null;
        }
    }

    public List<Plato> getAllPlatos() {
        List<Plato> platos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.openReadableDatabase();
            cursor = db.query(TABLE_PLATOS, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE));
                    String descripcion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION));
                    double precio = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO));
                    byte[] imagen = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGEN));
                    platos.add(new Plato(id, nombre, descripcion, precio, imagen));
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
        return platos;
    }

    public void updatePlato(Plato plato) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOMBRE, plato.getNombre());
            values.put(COLUMN_DESCRIPCION, plato.getDescripcion());
            values.put(COLUMN_PRECIO, plato.getPrecio());
            values.put(COLUMN_IMAGEN, plato.getImagen());
            db.update(TABLE_PLATOS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(plato.getId())});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void deletePlato(int id) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openWritableDatabase();
            db.delete(TABLE_PLATOS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
