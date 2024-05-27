package com.example.mobileutpapp.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.UserController;
import com.example.mobileutpapp.entity.User;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private UserController userController;
    private ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userController = new UserController(this);
        listViewUsers = findViewById(R.id.listViewUsers);

        List<User> users = userController.getAllUsers();
        UserListAdapter adapter = new UserListAdapter(this, users);
        listViewUsers.setAdapter(adapter);
    }
}
