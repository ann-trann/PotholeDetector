package com.masterandroid.potholedetector.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masterandroid.potholedetector.Activity.CreateAccountActivity;
import com.masterandroid.potholedetector.Adapter.SettingAdapter;
import com.masterandroid.potholedetector.Event.SettingItemInteface;
import com.masterandroid.potholedetector.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements SettingItemInteface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ArrayList<String> settingNameList;
    private AppCompatButton btnLogout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnLogout = view.findViewById(R.id.profileButtonLogOut);

        initData();
        setUpBtnLogout();
        RecyclerView recyclerView = view.findViewById(R.id.profileAllSetting);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SettingAdapter adapter = new SettingAdapter(settingNameList, getContext(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private static void initData() {
        settingNameList = new ArrayList<String>();
        settingNameList.add("Notifications");
        settingNameList.add("Appearance");
        settingNameList.add("Language");
        settingNameList.add("Privacy & Security");
        settingNameList.add("Storage");
    }

    @Override
    public void click(int position) {
        if (position == 0) {
            switchFragment(new NotificationFragment());
        } else if (position == 1) {
            switchFragment(new AppearanceFragment());
        } else if (position == 2) {
            switchFragment(new LanguageFragment());
        }
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    private void setUpBtnLogout() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_logout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(view);

        AppCompatButton btnCancel = view.findViewById(R.id.dialogButtonCancel);
        AppCompatButton btnAccept = view.findViewById(R.id.dialogButtonLogout);

        GradientDrawable newBackground = (GradientDrawable) getResources().getDrawable(R.drawable.medium_border).mutate();
        newBackground.setColor(ContextCompat.getColor(requireContext(), R.color.white));
        view.setBackground(newBackground);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
}