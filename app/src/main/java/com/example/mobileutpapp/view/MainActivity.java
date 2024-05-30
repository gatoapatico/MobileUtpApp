package com.example.mobileutpapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.config.DatabaseHelper;
import com.example.mobileutpapp.controller.UserController;
import com.example.mobileutpapp.utils.InputValidator;

public class MainActivity extends AppCompatActivity {

    private UserController userController;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userController = new UserController(this);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (InputValidator.isValid(username, password)) {
                    boolean isValid = userController.loginUser(username, password);
                    if (isValid) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("USERNAME", username);
                        editor.apply();

                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        userController.registerUser("admin","admin");
//        userController.registerUser("miguel","miguel");

//        TextView etUsername = findViewById(R.id.et_username);
//
//        Button boton = findViewById(R.id.btn_login);
//        boton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.activity_screen_welcome);
//                TextView txtWelcome = findViewById(R.id.txt_welcome);
//                String plainWelcome = "Hola, " + etUsername.getText() + "!";
//                txtWelcome.setText(plainWelcome);
//                setupLayout2Listener();
//            }
//        });

    }

//    private void setupLayout2Listener() {
//        Button btnOrdenarInsumo = findViewById(R.id.btn_ordenar_insumo);
//        Button btnIngresarInsumo = findViewById(R.id.btn_ingresar_insumo);
//        Button btnReporte = findViewById(R.id.btn_reporte);
//        Button btnCarta = findViewById(R.id.btn_carta);
//        Button btnDetalleIngreso = findViewById(R.id.btn_detalle_ingreso);
//        Button btnAlmacen = findViewById(R.id.btn_almacen);

//        btnOrdenarInsumo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.ordenar_insumo);
//                setupLayout3Listener();
//            }
//        });
//        btnIngresarInsumo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.add_supply);
//                setupLayout3Listener();
//            }
//        });
//        btnReporte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.reportes);
//                setupLayout3Listener();
//            }
//        });
//        btnCarta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.carta);
//                setupLayout3Listener();
//            }
//        });
//        btnDetalleIngreso.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.detalle_ingreso);
//                setupLayout3Listener();
//            }
//        });
//        btnAlmacen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.almacen);
//                setupLayout3Listener();
//            }
//        });
//
//    }

//    private void setupLayout3Listener() {
//        Button btnBack = findViewById(R.id.btn_back);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.activity_screen_welcome);
//                setupLayout2Listener();
//            }
//        });
//    }
}