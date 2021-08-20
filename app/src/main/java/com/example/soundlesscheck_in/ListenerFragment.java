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
import java.util.Locale;

public class ListenerFragment extends Fragment{
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
    Date mDate;
    long mNow;
    Button mBtnSetting;
    Button mBtnGetInfo;
    TextView mTextTime;
    TextView mTextPhoneNumber;
    TextView mTextViewCity;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listener, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View v){
        mBtnSetting = v.findViewById(R.id.btnSetting_Listener);
        mBtnGetInfo = v.findViewById(R.id.btnGetInfo);
        mTextTime = v.findViewById(R.id.textViewTime_Listener);
        mTextPhoneNumber = v.findViewById(R.id.textViewNum_Listener);
        mTextViewCity = v.findViewById(R.id.textViewCity_Listener);
    }

    // return current time
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}