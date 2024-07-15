package com.example.mobileutpapp.config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 2;  // Incrementar la versión de la base de datos

    // Tabla Usuarios
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_PLATOS = "Platos";
    public static final String COLUMN_PLATO_ID = "id";
    public static final String COLUMN_PLATO_NOMBRE = "nombre";
    public static final String COLUMN_PLATO_DESCRIPCION = "descripcion";
    public static final String COLUMN_PLATO_PRECIO = "precio";
    private static final String COLUMN_IMAGE_PATH = "imagen";

    public static final String TABLE_INSUMOS = "Insumos";
    public static final String COLUMN_INSUMO_ID = "id";
    public static final String COLUMN_INSUMO_NAME = "insumo";
    public static final String COLUMN_INSUMO_COST = "precio";
    public static final String COLUMN_INSUMO_WEIGHT = "peso";

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
                    COLUMN_PLATO_PRECIO + " REAL," +
                    COLUMN_IMAGE_PATH + " BLOB);";

    // Tabla Pedidos
    public static final String TABLE_PEDIDOS = "Pedidos";
    public static final String COLUMN_PEDIDO_ID = "id";
    public static final String COLUMN_PEDIDO_ID_PRODUCTO = "id_producto";
    public static final String COLUMN_PEDIDO_PRECIO = "precio";
    public static final String COLUMN_PEDIDO_CANTIDAD = "cantidad";
    public static final String COLUMN_PEDIDO_COSTO_TOTAL = "costo_total";
    public static final String COLUMN_PEDIDO_MESA = "mesa";
    public static final String COLUMN_PEDIDO_FECHA = "fecha";

    private static final String TABLE_CREATE_PEDIDOS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PEDIDOS + " (" +
                    COLUMN_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PEDIDO_ID_PRODUCTO + " INTEGER, " +
                    COLUMN_PEDIDO_PRECIO + " REAL, " +
                    COLUMN_PEDIDO_CANTIDAD + " INTEGER, " +
                    COLUMN_PEDIDO_COSTO_TOTAL + " REAL, " +
                    COLUMN_PEDIDO_MESA + " TEXT, " +
                    COLUMN_PEDIDO_FECHA + " INTEGER);";

    private static final String TABLE_CREATE_INSUMOS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_INSUMOS + " (" +
                    COLUMN_INSUMO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_INSUMO_NAME + " TEXT, " +
                    COLUMN_INSUMO_COST + " REAL, " +
                    COLUMN_INSUMO_WEIGHT + " INTEGER);";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        checkAndCreateTables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PLATOS);
        db.execSQL(TABLE_CREATE_INSUMOS);
        db.execSQL(TABLE_CREATE_PEDIDOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLATOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSUMOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDOS);
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
            if (!isTableExists(db, TABLE_INSUMOS)) {
                db.execSQL(TABLE_CREATE_INSUMOS);
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
