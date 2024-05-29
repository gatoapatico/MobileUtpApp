package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.UserController;
import com.example.mobileutpapp.entity.User;
import com.example.mobileutpapp.utils.UserListAdapter;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private UserController userController;
    private ListView listViewUsers;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userController = new UserController(this);
        listViewUsers = findViewById(R.id.listViewUsers);

        List<User> users = userController.getAllUsers();
        adapter = new UserListAdapter(this, users);
        listViewUsers.setAdapter(adapter);

        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                if (user != null) {
                    Intent intent = new Intent(UserListActivity.this, EditUserActivity.class);
                    intent.putExtra("USER_ID", user.getId());
                    intent.putExtra("USER_NAME", user.getUsername());
                    intent.putExtra("USER_PASSWORD", user.getPassword());
                    startActivity(intent);
                }
            }
        });
    }
}
