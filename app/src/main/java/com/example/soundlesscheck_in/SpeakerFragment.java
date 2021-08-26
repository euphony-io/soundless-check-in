package com.example.soundlesscheck_in;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

import euphony.lib.transmitter.EuTxManager;

public class SpeakerFragment extends Fragment implements View.OnClickListener {

    private boolean speak = false;
    // Variables of UI component
    private TextView tvNumber;
    private TextView tvCity;
    private Button btnCheckIn;
    private Button btnSetting;
    //
    private String data;        // data that gonna be sent.
    private String phoneNumber;
    private String livingCity;
    // Euphony Library
    private EuTxManager mTxManager = new EuTxManager();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_speaker, container,false);
        setUI(v);
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void setUI(View v) {
        tvNumber = v.findViewById(R.id.textViewNumEdit_Speaker);
        tvCity = v.findViewById(R.id.textViewCityEdit_Speaker);
        btnCheckIn = v.findViewById(R.id.btnCheckIn);
        btnCheckIn.setOnClickListener(this);
        btnSetting = v.findViewById(R.id.btnSetting_Speaker);
        btnSetting.setOnClickListener(this);
    }

    private void getData() {
        // necessary information
        phoneNumber = EncryptedSPManager.getString(this.getActivity(), "phone");
        livingCity = EncryptedSPManager.getString(this.getActivity(), "city");

        data = phoneNumber+"/"+livingCity;
        // Data format : 010-xxxx-xxxx/City(English)
        // ex) 010-1234-1234/Seoul

        tvNumber.setText(phoneNumber);
        tvCity.setText(livingCity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCheckIn :
                if (speak) {
                    // Toast.makeText(getActivity(), "Stop", Toast.LENGTH_SHORT).show();
                    mTxManager.stop();
                    speak = false;
                } else {
                    // Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                    mTxManager.euInitTransmit(data);
                    mTxManager.process(-1);      // -1 : generate sound infinite
                    speak = true;
                }
                break;
            case R.id.btnSetting_Speaker :
                Intent tutorialIntent = new Intent(this.getActivity().getApplicationContext(), TutorialActivity.class);
                tutorialIntent.putExtra("boolean_checkFirst", false);
                getActivity().startActivity(tutorialIntent);
                break;
            default:
                break;

        }
    }

}
