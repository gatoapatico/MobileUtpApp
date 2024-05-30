package com.example.mobileutpapp.config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_PLATOS = "Platos";
    public static final String COLUMN_PLATO_ID = "id";
    public static final String COLUMN_PLATO_NOMBRE = "nombre";
    public static final String COLUMN_PLATO_DESCRIPCION = "descripcion";
    public static final String COLUMN_PLATO_PRECIO = "precio";
    private static final String COLUMN_IMAGE_PATH = "image_path";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT);";

    private static final String TABLE_CREATE_PLATOS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PLATOS + " (" +
            COLUMN_PLATO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PLATO_NOMBRE + " TEXT, " +
            COLUMN_PLATO_DESCRIPCION + " TEXT, " +
            COLUMN_PLATO_PRECIO + " REAL, " +
            COLUMN_IMAGE_PATH + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        checkAndCreateTables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PLATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLATOS);
        onCreate(db);
    }

    public SQLiteDatabase openReadableDatabase() {
        return this.getReadableDatabase();
    }

    public SQLiteDatabase openWritableDatabase() {
        return this.getWritableDatabase();
    }

    private void checkAndCreateTables() {
        SQLiteDatabase db = openWritableDatabase();
        try {
            if (!isTableExists(db, TABLE_USERS)) {
                db.execSQL(TABLE_CREATE_USERS);
            }
            if (!isTableExists(db, TABLE_PLATOS)) {
                db.execSQL(TABLE_CREATE_PLATOS);
            }
        } finally {
            db.close();
        }
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
