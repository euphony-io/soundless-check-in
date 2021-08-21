package com.example.soundlesscheck_in;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import euphony.lib.receiver.EuRxManager;

public class ListenerFragment extends Fragment implements View.OnClickListener {
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
    Date mDate;
    long mNow;

    Button mBtnSetting;
    Button mBtnGetInfo;
    TextView mTextTime;
    TextView mTextPhoneNumber;
    TextView mTextViewCity;

    private final int PERMISSION_REQUEST_RECORD_AUDIO = 2021;

    EuRxManager mReceiver = new EuRxManager();
    private boolean isRunning = false;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listener, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View v) {
        mBtnSetting = v.findViewById(R.id.btnSetting_Listener);
        mBtnGetInfo = v.findViewById(R.id.btnGetInfo);
        mTextTime = v.findViewById(R.id.textViewTime_Listener);
        mTextPhoneNumber = v.findViewById(R.id.textViewNum_Listener);
        mTextViewCity = v.findViewById(R.id.textViewCity_Listener);

        mBtnSetting.setOnClickListener(this);
        mBtnGetInfo.setOnClickListener(this);

        requestRecorderPermission();

        mReceiver.setAcousticSensor(letters -> {
//            setUserInformation(letters);
            isRunning = false;
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetInfo) {
            if (checkRecordAudioPermission()) {

            } else {
                requestRecorderPermission();
            }
        }
        if (v.getId() == R.id.btnSetting_Listener) {

        }
    }

    // return current time
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    private boolean checkRecordAudioPermission() {
        return ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestRecorderPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.RECORD_AUDIO},
                PERMISSION_REQUEST_RECORD_AUDIO);
    }
}