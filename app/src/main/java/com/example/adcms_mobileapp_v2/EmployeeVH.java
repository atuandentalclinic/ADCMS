package com.example.adcms_mobileapp_v2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class EmployeeVH extends RecyclerView.ViewHolder {
    public TextView txt_name, txt_position;
    public EmployeeVH(@NonNull View itemView)
    {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_position = itemView.findViewById(R.id.txt_position);

    }
}
