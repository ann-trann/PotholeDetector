package com.masterandroid.potholedetector.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.masterandroid.potholedetector.Model.PotholeModel;
import com.masterandroid.potholedetector.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Create Adapter for recycler view in All Potholes Activity, get all pothole and sort it by date
// Recycler view will display date and what pothole was detected in that date

public class AllPotholesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Map<String, ArrayList<PotholeModel>> groupedItems;
    private List<String> dates;

    public AllPotholesAdapter(@NonNull Map<String, ArrayList<PotholeModel>> groupedItems) {
        this.groupedItems = groupedItems;
        this.dates = new ArrayList<>(groupedItems.keySet());
        Collections.sort(dates); // Sắp xếp ngày
    }

    @Override
    public int getItemViewType(int position) {
        int index = 0; // Số lượng item đã được thêm
        for (String date : dates) {
            if (position == index) {
                return 0; // Ngày
            }
            index++; // Tăng chỉ số cho ngày
            if (position < index + groupedItems.get(date).size()) {
                return 1; // Item
            }
            index += groupedItems.get(date).size(); // Tăng chỉ số cho các item
        }
        return -1; // Không hợp lệ
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.date_item, parent, false); // Layout cho ngày
            return new DateViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.porthole_item, parent, false); // Layout cho item
            return new ContentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int index = 0;
        for (String date : dates) {
            if (position == index) {
                ((DateViewHolder) holder).dateTextView.setText(date); // Hiển thị ngày
                return;
            }
            index++; // Tăng chỉ số cho ngày
            List<PotholeModel> itemsForDate = groupedItems.get(date);
            if (position < index + itemsForDate.size()) {
                int itemIndex = position - index; // Tính chỉ số item
                ((ContentViewHolder) holder).tvStatus.setText(itemsForDate.get(itemIndex).getStatus());
                ((ContentViewHolder) holder).tvAddress.setText(itemsForDate.get(itemIndex).getAddress());
                ((ContentViewHolder) holder).tvDateTime.setText(itemsForDate.get(itemIndex).getDatetime());
                return;
            }
            index += itemsForDate.size(); // Tăng chỉ số cho các item
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (List<PotholeModel> items : groupedItems.values()) {
            count += 1 + items.size(); // 1 cho ngày và số lượng item
        }
        return count;
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        DateViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateTime, tvAddress, tvStatus;

        ContentViewHolder(View itemView) {
            super(itemView);
            tvDateTime = itemView.findViewById(R.id.dateTime);
            tvAddress = itemView.findViewById(R.id.address);
            tvStatus = itemView.findViewById(R.id.status);
        }
    }
}
