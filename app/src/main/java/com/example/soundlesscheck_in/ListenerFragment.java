package com.example.soundlesscheck_in;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerFragment extends Fragment {

    // Variables related to getting current time
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date mDate;
    long mNow;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_listener, container,false);

        return v;

    }

    // return current time
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}