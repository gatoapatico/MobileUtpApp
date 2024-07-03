package com.example.mobileutpapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private static final String TABLE_PEDIDOS = "Pedidos";
    private static final String COLUMN_PEDIDO_ID = "id";
    private static final String COLUMN_PEDIDO_ID_PRODUCTO = "id_producto";
    private static final String COLUMN_PEDIDO_PRECIO = "precio";
    private static final String COLUMN_PEDIDO_CANTIDAD = "cantidad";
    private static final String COLUMN_PEDIDO_COSTO_TOTAL = "costo_total";
    private static final String COLUMN_PEDIDO_MESA = "mesa";

    private DatabaseHelper dbHelper;

    public PedidoController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addPedido(Pedido pedido) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PEDIDO_ID_PRODUCTO, pedido.getIdProducto());
        values.put(COLUMN_PEDIDO_PRECIO, pedido.getPrecio());
        values.put(COLUMN_PEDIDO_CANTIDAD, pedido.getCantidad());
        values.put(COLUMN_PEDIDO_COSTO_TOTAL, pedido.getCostoTotal());
        values.put(COLUMN_PEDIDO_MESA, pedido.getMesa());
        db.insert(TABLE_PEDIDOS, null, values);
        db.close();
    }

    public Pedido getPedidoById(int pedidoId) {
        SQLiteDatabase db = dbHelper.openReadableDatabase();
        Cursor cursor = db.query(TABLE_PEDIDOS,
                null,
                COLUMN_PEDIDO_ID + " = ?",
                new String[]{String.valueOf(pedidoId)},
                null,
                null,
                null);

        Pedido pedido = null;
        if (cursor != null && cursor.moveToFirst()) {
            pedido = new Pedido(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_ID_PRODUCTO)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_PRECIO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_CANTIDAD)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_COSTO_TOTAL)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_MESA))
            );
            cursor.close();
        }
        db.close();
        return pedido;
    }

    public List<Pedido> getAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.openReadableDatabase();
        Cursor cursor = db.query(TABLE_PEDIDOS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Pedido pedido = new Pedido(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_ID_PRODUCTO)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_PRECIO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_CANTIDAD)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_COSTO_TOTAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEDIDO_MESA))
                );
                pedidos.add(pedido);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return pedidos;
    }

    public void updatePedido(Pedido pedido) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PEDIDO_ID_PRODUCTO, pedido.getIdProducto());
        values.put(COLUMN_PEDIDO_PRECIO, pedido.getPrecio());
        values.put(COLUMN_PEDIDO_CANTIDAD, pedido.getCantidad());
        values.put(COLUMN_PEDIDO_COSTO_TOTAL, pedido.getCostoTotal());
        values.put(COLUMN_PEDIDO_MESA, pedido.getMesa());
        db.update(TABLE_PEDIDOS, values, COLUMN_PEDIDO_ID + " = ?", new String[]{String.valueOf(pedido.getId())});
        db.close();
    }

    public void deletePedido(int pedidoId) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        db.delete(TABLE_PEDIDOS, COLUMN_PEDIDO_ID + " = ?", new String[]{String.valueOf(pedidoId)});
        db.close();
    }
}
