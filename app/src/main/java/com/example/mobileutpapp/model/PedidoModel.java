package com.example.mobileutpapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoModel {
    private static final String TABLE_PEDIDOS = "Pedidos";
    private static final String COLUMN_PEDIDO_ID = "id";
    private static final String COLUMN_PEDIDO_ID_PRODUCTO = "id_producto";
    private static final String COLUMN_PEDIDO_PRECIO = "precio";
    private static final String COLUMN_PEDIDO_CANTIDAD = "cantidad";
    private static final String COLUMN_PEDIDO_COSTO_TOTAL = "costo_total";
    private static final String COLUMN_PEDIDO_MESA = "mesa";
    private static final String COLUMN_PEDIDO_FECHA = "fecha";

    private DatabaseHelper dbHelper;

    public PedidoModel(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addPedido(Pedido pedido) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PEDIDO_ID_PRODUCTO, pedido.getIdProducto());
        values.put(COLUMN_PEDIDO_PRECIO, pedido.getPrecio());
        values.put(COLUMN_PEDIDO_CANTIDAD, pedido.getCantidad());
        values.put(COLUMN_PEDIDO_COSTO_TOTAL, pedido.getCostoTotal());
        values.put(COLUMN_PEDIDO_MESA, pedido.getMesa());
        values.put(COLUMN_PEDIDO_FECHA, pedido.getFecha());
        db.insert(TABLE_PEDIDOS, null, values);
        db.close();
    }

    public void deletePedido(int pedidoId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_PEDIDOS, COLUMN_PEDIDO_ID + " = ?", new String[]{String.valueOf(pedidoId)});
        db.close();
    }

}
