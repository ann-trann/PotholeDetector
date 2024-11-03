package com.masterandroid.potholedetector.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masterandroid.potholedetector.Activity.AllPotholesActivity;
import com.masterandroid.potholedetector.Adapter.PotholeItemAdapter;
import com.masterandroid.potholedetector.Model.PotholeModel;
import com.masterandroid.potholedetector.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<PotholeModel> potholeModels;
    private PotholeItemAdapter adapter;
    private TextView tvAllPothole;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.homeTodayTotalInformation);
        GradientDrawable newBackground = (GradientDrawable) getResources().getDrawable(R.drawable.medium_border).mutate();

        newBackground.setColor(ContextCompat.getColor(requireContext(), R.color.light_sub_background_color_gray));

        constraintLayout.setBackground(newBackground);

        initData();

        RecyclerView recyclerView = view.findViewById(R.id.homeListPothole);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PotholeItemAdapter(potholeModels, getContext());
        recyclerView.setAdapter(adapter);

        tvAllPothole = view.findViewById(R.id.homeAllPotholesNavigation);
        tvAllPothole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllPotholesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initData() {
        potholeModels = new ArrayList<PotholeModel>();

        for (int i = 0; i < 6; i++){
            potholeModels.add(new PotholeModel("Detect", "24/10/2024, 4:14 PM",
                    "Tạ Quang Bửu, Khu phố 6, phường Linh Trung, thành phố Thủ Đức, TP. HCM"));
        }
    }
}