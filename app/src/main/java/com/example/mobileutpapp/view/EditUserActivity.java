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

public class EditUserActivity extends AppCompatActivity {
    private EditText etEditUsername, etEditPassword;
    private Button btnSaveChanges, btnDeleteUser;
    private UserController userController;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etEditUsername = findViewById(R.id.etEditUsername);
        etEditPassword = findViewById(R.id.etEditPassword);
        btnSaveChanges = findViewById(R.id.btnActualizarUsuario);
        btnDeleteUser = findViewById(R.id.btnEliminarUsuario);
        userController = new UserController(this);

        Intent intent = getIntent();
        userId = intent.getIntExtra("USER_ID", -1);
        String username = intent.getStringExtra("USER_NAME");
        String password = intent.getStringExtra("USER_PASSWORD");

        etEditUsername.setText(username);
        etEditPassword.setText(password);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedUsername = etEditUsername.getText().toString();
                String updatedPassword = etEditPassword.getText().toString();
                if (!updatedUsername.isEmpty() && !updatedPassword.isEmpty()) {
                    userController.updateUser(userId, updatedUsername, updatedPassword);
                    Toast.makeText(EditUserActivity.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditUserActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userController.deleteUser(userId);
                Toast.makeText(EditUserActivity.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditUserActivity.this, UserListActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
