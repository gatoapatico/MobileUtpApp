package com.example.mobileutpapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        TextView tvUsername = convertView.findViewById(R.id.tvUsername);

        if (user != null) {
            tvUsername.setText(user.getUsername());
        }

        return convertView;
    }
}
