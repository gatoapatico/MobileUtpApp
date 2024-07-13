package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.UserController;
import com.example.mobileutpapp.utils.InputValidator;

public class AddUserActivity extends AppCompatActivity {

    private UserController userController;
    private EditText etNewUsername, etNewPassword;
    private Button btnAddUser;
    private Button btnViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        userController = new UserController(this);
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnViewUsers = findViewById(R.id.btnViewUsers);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etNewUsername.getText().toString();
                String newPassword = etNewPassword.getText().toString();

                if (InputValidator.isValid(newUsername, newPassword)) {
                    userController.registerUser(newUsername, newPassword);
                    Toast.makeText(AddUserActivity.this, "Usuario agregado satisfactoriamente", Toast.LENGTH_SHORT).show();
                    etNewUsername.setText("");
                    etNewPassword.setText("");
                } else {
                    Toast.makeText(AddUserActivity.this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUserActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });
    }
}
