package com.example.mobileutpapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
//    private static final String TABLE_USERS = "users";
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_USERNAME = "username";
//    private static final String COLUMN_PASSWORD = "password";
//
//    private static final String TABLE_CREATE =
//            "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_USERNAME + " TEXT, " +
//            COLUMN_PASSWORD + " TEXT);";

    private DatabaseHelper dbHelper;

    public UserModel(Context context) {
        dbHelper = new DatabaseHelper(context);
//        createTableIfNotExists();
    }

//    private void createTableIfNotExists() {
//        SQLiteDatabase db = dbHelper.openWritableDatabase();
//        db.execSQL(TABLE_CREATE);
//        db.close();
//    }

    public void addUser(User user) {
        SQLiteDatabase db = dbHelper.openWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        db.insert("users", null, values);
        db.close();
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = dbHelper.openReadableDatabase();
        String[] columns = {"id"};
        String selection = "username=? AND password=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public User getUserById(int userId) {
        SQLiteDatabase db = dbHelper.openReadableDatabase();
        Cursor cursor = db.query("users",
                null,
                "id" + " = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
            cursor.close();
            db.close();
            return user;
        } else {
            db.close();
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.openReadableDatabase();
            cursor = db.query("users", null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    users.add(new User(id, username, password));
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
        return users;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("password", user.getPassword());
            db.update("users", values, "id" + " = ?", new String[]{String.valueOf(user.getId())});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.openWritableDatabase();
            db.delete("users", "id" + " = ?", new String[]{String.valueOf(id)});
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
