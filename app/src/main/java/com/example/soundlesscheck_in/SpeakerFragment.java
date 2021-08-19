package com.example.soundlesscheck_in;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;

import euphony.lib.transmitter.EuTxManager;

public class SpeakerFragment extends Fragment implements View.OnClickListener {

    boolean speak = false;
    // Variables of UI component
    TextView tvTime;
    TextView tvNumber;
    TextView tvCity;
    Button btnCheckIn;
    // Variables related to getting current time
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date mDate;
    long mNow;
    //
    String data;        // data that gonna be sent.
    // Euphony Library
    EuTxManager mTxManager = new EuTxManager();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_speaker, container,false);

        tvTime = v.findViewById(R.id.textViewTimeEdit_Speaker);
        tvNumber = v.findViewById(R.id.textViewNumEdit_Speaker);
        tvCity = v.findViewById(R.id.textViewCityEdit_Speaker);
        btnCheckIn = v.findViewById(R.id.btnCheckIn);

        // necessary information
        String currentTime;
        String phoneNumber;
        String livingCity;

        // it will be updated after add tutorial function.
        currentTime = getTime();
        phoneNumber = "010-1234-1234";
        livingCity = "Gwangjin-gu, Seoul";

        data = currentTime+"/"+phoneNumber+"/"+livingCity;
        // Data format : yyyy-MM-dd HH:mm/010-xxxx-xxxx/City(English)
        // ex) 2021-08-19 17:36/010-1234-1234/Seoul

        //
        btnCheckIn.setOnClickListener(this);
        tvTime.setText(currentTime);
        tvNumber.setText(phoneNumber);
        tvCity.setText(livingCity);

        return v;
    }

    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Override
    public void onClick(View v) {
        if (speak) {
            Toast.makeText(getActivity(), "Stop", Toast.LENGTH_SHORT).show();
            mTxManager.stop();
            speak = false;
        } else {
            Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            mTxManager.euInitTransmit(data);
            mTxManager.process(-1);      // -1 : generate sound infinite
            speak = true;
        }
    }

}
