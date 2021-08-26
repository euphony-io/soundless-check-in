package com.example.soundlesscheck_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        checkFirstRun();

        setUI();

    }

    protected void setUI() {
        tabLayout = findViewById(R.id.mainTab);
        viewPager = findViewById(R.id.mainViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Check In"));    // speaker
        tabLayout.addTab(tabLayout.newTab().setText("Get Info"));   // listener

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 2));

        // pager가 변경될 때
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // tab이 선택될 때
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public void checkFirstRun() {
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);

        if(isFirstRun) {
            prefs.edit().putBoolean("isFirstRun", false).apply();

            Intent tutorialIntent = new Intent(MainActivity.this, TutorialActivity.class);
            tutorialIntent.putExtra("boolean_checkFirst", true);
            startActivity(tutorialIntent);
        }
    }
}