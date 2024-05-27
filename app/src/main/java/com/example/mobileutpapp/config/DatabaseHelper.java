package com.example.mobileutpapp.config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aquí se crearán las tablas necesarias
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí se manejarán las actualizaciones de la base de datos
    }

    public SQLiteDatabase openReadableDatabase() {
        return this.getReadableDatabase();
    }

    public SQLiteDatabase openWritableDatabase() {
        return this.getWritableDatabase();
    }
}
