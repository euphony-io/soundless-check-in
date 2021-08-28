package com.example.soundlesscheck_in;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class CustomToast extends Toast {
    private Context mContext;

    private LayoutInflater inflater;
    private View v;
    private TextView text;

    public CustomToast(Context context) {
        super(context);
        mContext = context;

    //    LayoutInflater inflater;
    //    View v;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.custom_toast_layout, null);
        text = (TextView) v.findViewById(R.id.toast_text);

    }

    protected void showToast(String msg, int duration) {

        text.setText(msg);
        show(this, v, duration);

    }

    private void show(Toast toast, View v, int duration) {
        toast.setGravity(Gravity.CENTER,0, 400);
        toast.setDuration(duration);
        toast.setView(v);
        toast.show();
    }

}
