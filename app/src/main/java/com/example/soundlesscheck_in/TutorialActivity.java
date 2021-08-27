package com.example.soundlesscheck_in;

import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int btnCancel = 11111;
    private boolean isFirst;
    private boolean isBtnMade = false;

    private LinearLayout mLayout;
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

    @Override
    protected void onResume() {
        super.onResume();
        if(!isFirst) updateEditTextUI();
    }

    protected void setUI() {
        mLayout = findViewById(R.id.layoutTutorialButtons);
        mPhoneNumber = findViewById(R.id.phonenumberEditText);
        mLivingCity = findViewById(R.id.citynameEditText);
        mGetInfoBtn = findViewById(R.id.btnGetFirstInfo);

        mPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mGetInfoBtn.setOnClickListener(this);

        isFirst = getIntent().getBooleanExtra("boolean_checkFirst", false);
        if(!isFirst&&!isBtnMade) addNewButton();
    }

    protected void addNewButton() {
        mCancelBtn = new Button(this);
        LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pm.gravity = Gravity.RIGHT;
        mCancelBtn.setId(btnCancel);
        mCancelBtn.setText("CANCEL");
        mCancelBtn.setTextColor(Color.parseColor("#FFFFFF"));   //white
        mCancelBtn.setTextSize(25);
        mCancelBtn.setBackgroundResource(R.drawable.btn_background);
        mCancelBtn.setLayoutParams(pm);

        mLayout.addView(mCancelBtn);

        mCancelBtn.setOnClickListener(this);
        isBtnMade = true;
    }

    protected void updateEditTextUI() {
        mPhoneNumber.setText(EncryptedSPManager.getString(this,"phone"));
        mLivingCity.setText(EncryptedSPManager.getString(this, "city"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetFirstInfo:
                if(mPhoneNumber.getText().toString().equals("")||mLivingCity.getText().toString().equals("")) {
                    Toast.makeText(this,"Fill out the form!",Toast.LENGTH_SHORT).show();
                }
                else {
                    EncryptedSPManager.setString(this, "phone", mPhoneNumber.getText().toString());
                    EncryptedSPManager.setString(this, "city", mLivingCity.getText().toString());
                    finish();
                }
                break;
            case btnCancel:
                finish();
                break;
            default:
                break;
        }
    }

}
