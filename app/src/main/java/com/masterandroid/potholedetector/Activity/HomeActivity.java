package com.masterandroid.potholedetector.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.masterandroid.potholedetector.Fragment.HomeFragment;
import com.masterandroid.potholedetector.Fragment.MapFragment;
import com.masterandroid.potholedetector.Fragment.ProfileFragment;
import com.masterandroid.potholedetector.Fragment.ReportFragment;
import com.masterandroid.potholedetector.R;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView navBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        navBottom = findViewById(R.id.bottom_navigation);
        navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    switchFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.map) {
                    switchFragment(new MapFragment());
                    return true;
                } else if (item.getItemId() == R.id.report) {
                    switchFragment(new ReportFragment());
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    switchFragment(new ProfileFragment());
                    return true;
                }

                return false;
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}