package com.example.mobileutpapp.utils;

public class InputValidator {
    public static boolean isValid(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
}
