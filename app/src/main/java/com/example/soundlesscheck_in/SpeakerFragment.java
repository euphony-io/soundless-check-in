package com.example.soundlesscheck_in;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import euphony.lib.transmitter.EuTxManager;

public class SpeakerFragment extends Fragment implements View.OnClickListener {

    private boolean speak = false;
    private CustomToast toast;
    private GetCityTownInfo getCityTownInfo;
    // Variables of UI component
    private TextView tvNumber;
    private TextView tvCity;
    private Button btnCheckIn;
    private Button btnSetting;
    //
    private String data;        // data that gonna be sent.
    private String[] positions;
    private String phoneNumber;
    private String livingCity;
    private String livingTown;
    private int mCityPosition;
    private int mTownPosition;
    // Euphony Library
    private EuTxManager mTxManager = new EuTxManager();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_speaker, container,false);
        setUI(v);
        toast = new CustomToast(this.getContext());
        getCityTownInfo = new GetCityTownInfo(this.getContext());
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
        livingCity = EncryptedSPManager.getString(requireContext(), "userCity");
        livingTown = EncryptedSPManager.getString(requireContext(), "userTown");

        positions = getCityTownInfo.stringToPosition(livingCity, livingTown).split("/");
        mCityPosition = Integer.parseInt(positions[0]);
        mTownPosition = Integer.parseInt(positions[1]);
        data = phoneNumber+"/"+mCityPosition+"/"+mTownPosition;
        // Data format : 010-xxxx-xxxx/x/x
        // ex) 010-1234-1234/1/3 (1:city position, 3:town position)

        tvNumber.setText(phoneNumber);
        tvCity.setText(livingCity+" "+livingTown);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCheckIn :
                if (speak) {
                    toast.showToast("Stop Check-in", Toast.LENGTH_SHORT);
                    mTxManager.stop();
                    speak = false;
                } else {
                    toast.showToast("Check In !", Toast.LENGTH_SHORT);
                    mTxManager.euInitTransmit(data);
                    mTxManager.process(-1);      // -1 : generate sound infinite
                    speak = true;
                }
                break;
            case R.id.btnSetting_Speaker :
                Intent tutorialIntent = new Intent(getActivity(), TutorialActivity.class);
                tutorialIntent.putExtra("boolean_checkFirst", false);
                startActivity(tutorialIntent);
                break;
            default:
                break;

        }
    }

}