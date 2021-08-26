package com.example.soundlesscheck_in;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mPhoneNumber;
    private EditText mLivingCity;
    private Button mGetInfoBtn;
    private Button mCancelBtn;

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
        mCancelBtn = findViewById(R.id.btnCancel);

        mPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mGetInfoBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetFirstInfo:
                EncryptedSPManager.setString(this, "phone", mPhoneNumber.getText().toString());
                EncryptedSPManager.setString(this, "city", mLivingCity.getText().toString());

                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
            default:
                break;
        }
    }

}
