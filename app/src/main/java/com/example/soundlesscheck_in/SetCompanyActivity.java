package com.example.soundlesscheck_in;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class SetCompanyActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mCompanyNum;
    private TextView mCompanyCity;
    private TextView mCompanySigungu;
    private EditText mCompanyName;
    private Button mGetInfoBtn;
    private Button mCancleBtn;

    private Spinner spinnerCity;
    private Spinner spinnerSigungu;

    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_company);
        setUI();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[])getResources().getStringArray(R.array.spinner_region));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(arrayAdapter);

        initAddressSpinner();

        if(!EncryptedSPManager.getString(getApplicationContext(), "licenseNumber").equals(EncryptedSPManager.DEFAULT_VALUE_STRING)){
            mCompanyNum.setText(EncryptedSPManager.getString(getApplicationContext(), "licenseNumber"));
            mCompanyName.setText(EncryptedSPManager.getString(getApplicationContext(), "name"));
            spinnerCity.setSelection(arrayAdapter.getPosition(EncryptedSPManager.getString(getApplicationContext(), "city")));
            spinnerSigungu.setSelection(arrayAdapter.getPosition(EncryptedSPManager.getString(getApplicationContext(), "town")));
        }
    }

    protected void setUI() {
        mCompanyNum = findViewById(R.id.companyNumEditText);
        mCompanyName = findViewById(R.id.companyNameEditText);
        mCompanyCity = findViewById(R.id.companyCity);
        mCompanySigungu = findViewById(R.id.companySigungu);
        mGetInfoBtn = findViewById(R.id.btnGetCompanyInfo);
        mCancleBtn = findViewById(R.id.btnCancleCompanyInfo);

        mGetInfoBtn.setOnClickListener(this);
        mCancleBtn.setOnClickListener(this);

        spinnerCity = findViewById(R.id.spin_city);
        spinnerSigungu = findViewById(R.id.spin_sigungu);
    }


    @Override
    public void onClick(View v) {
        if (v == mCancleBtn) {
            finish();
        }

        if (v == mGetInfoBtn) {
            EncryptedSPManager.setString(this, "licenseNumber", mCompanyNum.getText().toString());
            EncryptedSPManager.setString(this, "name", mCompanyName.getText().toString());
            EncryptedSPManager.setString(this, "city", mCompanyCity.getText().toString());
            EncryptedSPManager.setString(this, "town", mCompanySigungu.getText().toString());

            finish();
        }
    }

    private void initAddressSpinner() {
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCompanyCity.setText(spinnerCity.getSelectedItem().toString());

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
                mCompanySigungu.setText(spinnerSigungu.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    private void setSigunguSpinnerAdapterItem(int array_resource) {
        if (arrayAdapter != null) {
            spinnerSigungu.setAdapter(null);
            arrayAdapter = null;
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[])getResources().getStringArray(array_resource));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSigungu.setAdapter(arrayAdapter);
    }

}