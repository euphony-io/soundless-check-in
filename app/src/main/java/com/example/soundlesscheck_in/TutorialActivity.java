package com.example.soundlesscheck_in;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mPhoneNumber;
    private EditText mLivingCity;
    private Button mGetInfoBtn;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);

        setUI();

    }

    protected void setUI() {
        mPhoneNumber = findViewById(R.id.phonenumberEditText);
        mLivingCity = findViewById(R.id.citynameEditText);
        mGetInfoBtn = findViewById(R.id.btnGetFirstInfo);
        mGetInfoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        prefs = getSharedPreferences("Pref", MODE_PRIVATE);

        prefs.edit().putString("phone", mPhoneNumber.getText().toString()).apply();
        prefs.edit().putString("city", mLivingCity.getText().toString()).apply();

        finish();
    }
}
