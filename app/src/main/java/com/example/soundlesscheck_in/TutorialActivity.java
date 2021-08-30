package com.example.soundlesscheck_in;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int btnCancel = 11111;
    private boolean isFirst;
    private boolean isBtnMade = false;
    private CustomToast toast;

    private LinearLayout mLayout;
    private EditText mPhoneNumber;
    private Button mGetInfoBtn;
    private Button mCancelBtn;

    private TextView mCity;
    private TextView mTown;

    private Spinner spinnerCity;
    private Spinner spinnerSigungu;

    private ArrayAdapter<String> arrayAdapter;

    private int mCityPosition;
    private int mTownPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);

        toast = new CustomToast(this);

        setUI();

        initAddressSpinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFirst) updateEditTextUI();
    }

    protected void setUI() {
        mLayout = findViewById(R.id.layoutTutorialButtons);
        mPhoneNumber = findViewById(R.id.phonenumberEditText);

        mGetInfoBtn = findViewById(R.id.btnGetFirstInfo);

        spinnerCity = findViewById(R.id.spinner1);
        spinnerSigungu = findViewById(R.id.spinner2);

        mCity = findViewById(R.id.userCity);

        mTown = findViewById(R.id.userTown);

        mPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mGetInfoBtn.setOnClickListener(this);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_region));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(arrayAdapter);

        isFirst = getIntent().getBooleanExtra("boolean_checkFirst", false);
        if (!isFirst && !isBtnMade) addNewButton();
    }

    protected void addNewButton() {
        mCancelBtn = new Button(this);
        LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pm.gravity = Gravity.RIGHT;
        mCancelBtn.setId(btnCancel);
        mCancelBtn.setText("CANCEL");
        mCancelBtn.setTextColor(Color.parseColor("#FFFFFF"));   //white
        mCancelBtn.setTextSize(20);
        mCancelBtn.setBackgroundResource(R.drawable.btn_background);
        mCancelBtn.setLayoutParams(pm);

        mLayout.addView(mCancelBtn);

        mCancelBtn.setOnClickListener(this);
        isBtnMade = true;
    }

    protected void updateEditTextUI() {
        mPhoneNumber.setText(EncryptedSPManager.getString(this, "phone"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetFirstInfo:

                if (mCityPosition == 0) {
                    toast.showToast("Fill out the form!", Toast.LENGTH_LONG);
                } else {
                    EncryptedSPManager.setString(this, "phone", mPhoneNumber.getText().toString());
                    EncryptedSPManager.setString(this, "userCity", mCity.getText().toString());
                    EncryptedSPManager.setString(this, "userTown", mTown.getText().toString());
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

    private void initAddressSpinner() {
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCity.setText(spinnerCity.getSelectedItem().toString());
                mCityPosition = position;
                switch (position) {
                    case 0:
                        spinnerSigungu.setAdapter(null);
                        break;
                    case 1:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_seoul);
                        break;
                    case 2:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_busan);
                        break;
                    case 3:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_daegu);
                        break;
                    case 4:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_incheon);
                        break;
                    case 5:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gwangju);
                        break;
                    case 6:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_daejeon);
                        break;
                    case 7:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_ulsan);
                        break;
                    case 8:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_sejong);
                        break;
                    case 9:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gyeonggi);
                        break;
                    case 10:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gangwon);
                        break;
                    case 11:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_chung_buk);
                        break;
                    case 12:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_chung_nam);

                        break;
                    case 13:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_jeon_buk);
                        break;
                    case 14:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_jeon_nam);
                        break;
                    case 15:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gyeong_buk);
                        break;
                    case 16:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_gyeong_nam);
                        break;
                    case 17:
                        setSigunguSpinnerAdapterItem(R.array.spinner_region_jeju);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerSigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTown.setText(spinnerSigungu.getSelectedItem().toString());
                mTownPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setSigunguSpinnerAdapterItem(int array_resource) {
        if (arrayAdapter != null) {
            spinnerSigungu.setAdapter(null);
            arrayAdapter = null;
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(array_resource));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSigungu.setAdapter(arrayAdapter);
    }

}