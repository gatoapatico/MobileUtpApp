package com.example.mobileutpapp.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.entity.User;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    public UserListAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvUsername = convertView.findViewById(R.id.tvUsername);
        TextView tvPassword = convertView.findViewById(R.id.tvPassword);

        if (user != null) {
            tvId.setText(String.valueOf(user.getId()));
            tvUsername.setText(user.getUsername());
            tvPassword.setText(user.getPassword());
        }

        return convertView;
    }
}
