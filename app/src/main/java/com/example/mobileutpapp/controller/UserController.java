package com.example.mobileutpapp.controller;

import android.content.Context;

import com.example.mobileutpapp.entity.User;
import com.example.mobileutpapp.model.UserModel;

import java.util.List;

public class UserController {
    private UserModel userModel;

    public UserController(Context context) {
        userModel = new UserModel(context);
    }

    public boolean loginUser(String username, String password) {
        return userModel.checkUser(username, password);
    }

    public void registerUser(String username, String password) {
        User user = new User(username, password);
        userModel.addUser(user);
    }

    public List<User> getAllUsers() {
        return userModel.getAllUsers();
    }
}
