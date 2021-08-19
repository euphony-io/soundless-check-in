package com.example.soundlesscheck_in;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import euphony.lib.transmitter.EuTxManager;

public class SpeakerFragment extends Fragment implements View.OnClickListener {

    // Variables related to getting current time
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    Date mDate;
    long mNow;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_speaker, container,false);

        TextView tvTime = v.findViewById(R.id.textViewTimeEdit_Speaker);
        TextView tvNumber = v.findViewById(R.id.textViewNumEdit_Speaker);
        TextView tvCity = v.findViewById(R.id.textViewCityEdit_Speaker);
        Button btnCheckIn = v.findViewById(R.id.btnCheckIn);

        // necessary information
        String currentTime;
        String phoneNumber;
        String livingCity;
        String data;        // data that gonna be sent.

        // it will be updated after add tutorial function.
        currentTime = getTime();
        phoneNumber = "01012341234";
        livingCity = "Gwangjin-gu, Seoul";

        data = currentTime+"/"+phoneNumber+"/"+livingCity;

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

    }
}
