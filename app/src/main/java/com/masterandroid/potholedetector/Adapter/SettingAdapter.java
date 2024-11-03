package com.masterandroid.potholedetector.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.masterandroid.potholedetector.R;

import java.util.ArrayList;

// Create a adapter to display list setting what the application has (notifications, appearance, ..)

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private ArrayList<String> settingNameList;
    private Context context;

    public SettingAdapter(ArrayList<String> settingNameList, Context context) {
        this.settingNameList = settingNameList;
        this.context = context;
    }

    @NonNull
    @Override
    public SettingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.setting_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.ViewHolder holder, int position) {
        holder.tvNameSetting.setText(settingNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return settingNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameSetting;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameSetting = itemView.findViewById(R.id.itemSettingName);
        }
    }
}

