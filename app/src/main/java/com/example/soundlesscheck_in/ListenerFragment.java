package com.example.soundlesscheck_in;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.soundlesscheck_in.data.Store;
import com.example.soundlesscheck_in.data.Visitor;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import euphony.lib.receiver.EuRxManager;

public class ListenerFragment extends Fragment implements View.OnClickListener {
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
    Date mDate;
    long mNow;
    private CustomToast toast;

    Button mBtnSetting;
    Button mBtnGetInfo;
    TextView mTextTime;
    TextView mTextPhoneNumber;
    TextView mTextViewCity;

    TextView tvName;
    TextView tvLoc;

    private final int PERMISSION_REQUEST_RECORD_AUDIO = 2021;

    EuRxManager mReceiver = new EuRxManager();
    private boolean isRunning = false;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference visitorRef = db.collection("visitor");
    private final CollectionReference storeRef = db.collection("store");

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listener, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        toast = new CustomToast(this.getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void initUI(View v) {
        mBtnSetting = v.findViewById(R.id.btnSetting_Listener);
        mBtnGetInfo = v.findViewById(R.id.btnGetInfo);
        mTextTime = v.findViewById(R.id.textViewTime_Listener);
        mTextPhoneNumber = v.findViewById(R.id.textViewNum_Listener);
        mTextViewCity = v.findViewById(R.id.textViewCity_Listener);

        mBtnSetting.setOnClickListener(this);
        mBtnGetInfo.setOnClickListener(this);

        tvName = v.findViewById(R.id.companyName);
        tvLoc = v.findViewById(R.id.companyLoc);


        requestRecorderPermission();

        mReceiver.setAcousticSensor(letters -> {
            setUserInformation(letters);
            isRunning = false;
        });
    }

    private void getData() {
        String comName = EncryptedSPManager.getString(requireContext(), "name");
        String comLoc = EncryptedSPManager.getString(requireContext(), "city") + " " + EncryptedSPManager.getString(requireContext(), "town");

        if(!comName.equals(EncryptedSPManager.DEFAULT_VALUE_STRING) && !comLoc.contains(EncryptedSPManager.DEFAULT_VALUE_STRING)){
            tvName.setText(comName);
            tvLoc.setText(comLoc);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetInfo) {
            if (checkRecordAudioPermission()) {
                if(EncryptedSPManager.getString(getContext(), "licenseNumber").equals(EncryptedSPManager.DEFAULT_VALUE_STRING)) {
                //    Toast.makeText(requireActivity(), "Please check-in after registering the store information.", Toast.LENGTH_LONG).show();
                    toast.showToast("Please check-in after registering the store information.", Toast.LENGTH_LONG);
                } else {
                    controlReceiver();
                }
            } else {
                requestRecorderPermission();
            }
        }
        if (v.getId() == R.id.btnSetting_Listener) {
            Intent intent = new Intent(requireActivity(), SetCompanyActivity.class);
            startActivity(intent);
        }
    }

    private void setUserInformation(String data){
        String[] userInfo = data.split("/");

        if(userInfo.length == 2) {
            String currentTime = getTime();

            Store store = new Store(
                    EncryptedSPManager.getString(requireContext(), "licenseNumber"),
                    EncryptedSPManager.getString(requireContext(), "name"),
                    EncryptedSPManager.getString(requireContext(), "city"),
                    EncryptedSPManager.getString(requireContext(), "town")
                    );

            Visitor visitor = new Visitor(userInfo[0], userInfo[1], store.getTradeName(), currentTime);

            updateVisitorInformation(visitor);
            updateStoreInformation(store, visitor);

            mTextTime.setText(String.format(getString(R.string.check_in_time), currentTime));
            mTextPhoneNumber.setText(String.format(getString(R.string.customer_phone_number), userInfo[0]));
            mTextViewCity.setText(String.format(getString(R.string.customer_city), userInfo[1]));

        //    Toast.makeText(requireActivity(), "Check-in is complete!", Toast.LENGTH_LONG).show();
            toast.showToast("Check-in is complete!", Toast.LENGTH_LONG);
        }else{
        //    Toast.makeText(requireActivity(), "Failed to get user information. : "+data, Toast.LENGTH_LONG).show();
            toast.showToast("Failed to get user information. : "+data, Toast.LENGTH_LONG);
        }
    }

    private void updateVisitorInformation(Visitor visitor){
        visitorRef.document(visitor.getPhoneNumber())
                .collection(""+mNow)
                .document(visitor.getDate())
                .set(visitor)
                .addOnSuccessListener( unused ->
                        Log.d("ListenerFragment", "Success to save visitor information.")
                )
                .addOnFailureListener( e ->
                        Log.w("ListenerFragment", "Failure to save visitor information :" + e.getMessage())
                );
    }

    private void updateStoreInformation(Store store, Visitor visitor){
        storeRef.document(store.getLicenseNumber())
                .set(store)
                .addOnSuccessListener( unused ->
                        Log.d("ListenerFragment", "Success to save store information.")
                )
                .addOnFailureListener( e ->
                        Log.w("ListenerFragment", "Failure to save store information :" + e.getMessage())
                );

        storeRef.document(store.getLicenseNumber()).collection("visitor")
                .document(""+mNow)
                .set(visitor)
                .addOnSuccessListener( unused ->
                        Log.d("ListenerFragment", "Success to save store's visitor information.")
                )
                .addOnFailureListener( e ->
                        Log.w("ListenerFragment", "Failure to save store's visitor information :" + e.getMessage())
                );
    }

    private void controlReceiver(){
        if(isRunning){
            mReceiver.finish();
            isRunning = false;
        }else{
            mReceiver.listen();
            isRunning = true;
        //    Toast.makeText(requireActivity(), "Getting user information...", Toast.LENGTH_LONG).show();
            toast.showToast("Getting user informaion...", Toast.LENGTH_LONG);
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