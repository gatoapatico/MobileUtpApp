package com.example.mobileutpapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.entity.Pedido;

public class PedidoModel {
    private static final String TABLE_PEDIDOS = "Pedidos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_PRODUCTO = "id_producto";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_CANTIDAD = "cantidad";
    private static final String COLUMN_COSTO_TOTAL = "costo_total";
    private static final String COLUMN_MESA = "mesa";

    private DatabaseHelper dbHelper;

    public PedidoModel(Context context) {
        dbHelper = new DatabaseHelper(context);
        // Forzar la creación de la base de datos
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        db.close();
    }

    public void addPedido(Pedido pedido) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_PRODUCTO, pedido.getIdProducto());
        values.put(COLUMN_PRECIO, pedido.getPrecio());
        values.put(COLUMN_CANTIDAD, pedido.getCantidad());
        values.put(COLUMN_COSTO_TOTAL, pedido.getCostoTotal());
        values.put(COLUMN_MESA, pedido.getMesa());
        db.insert(TABLE_PEDIDOS, null, values);
        db.close();
    }

    // Puedes implementar métodos adicionales según tus necesidades, como obtener todos los pedidos, actualizar, eliminar, etc.
}
