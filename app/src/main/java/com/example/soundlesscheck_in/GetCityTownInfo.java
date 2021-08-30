package com.example.soundlesscheck_in;

import android.content.Context;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

public class GetCityTownInfo extends AppCompatActivity {

    private Context mContext;
    private Resources res;
    private String[] cityArray;
    private String[] townArray;

    // return value
    private String city;
    private int cityPos;
    private int townPos;

    public GetCityTownInfo(Context context) {
        mContext = context;
        res = mContext.getResources();
    }

    protected String stringToPosition(String city, String town) {
        return null;
    }

    protected String positionToString(int cityPosition, int townPosition) {
        if(cityPosition<=0) return null;
        else {
            cityArray = res.getStringArray(R.array.spinner_region);
            foundTownList(cityPosition);
            city += " "+getTownName(townPosition);

            return city;
        }
    }

    protected String getTownName(int position) {
        if(position>=townArray.length) return null;
        else return townArray[position];
    }

    protected void foundTownList(int position) {
        city = cityArray[position];
        switch (position) {
            case 1:
                townArray = res.getStringArray(R.array.spinner_region_seoul);
                break;
            case 2:
                townArray = res.getStringArray(R.array.spinner_region_busan);
                break;
            case 3:
                townArray = res.getStringArray(R.array.spinner_region_daegu);
                break;
            case 4:
                townArray = res.getStringArray(R.array.spinner_region_incheon);
                break;
            case 5:
                townArray = res.getStringArray(R.array.spinner_region_gwangju);
                break;
            case 6:
                townArray = res.getStringArray(R.array.spinner_region_daejeon);
                break;
            case 7:
                townArray = res.getStringArray(R.array.spinner_region_ulsan);
                break;
            case 8:
                townArray = res.getStringArray(R.array.spinner_region_sejong);
                break;
            case 9:
                townArray = res.getStringArray(R.array.spinner_region_gyeonggi);
                break;
            case 10:
                townArray = res.getStringArray(R.array.spinner_region_gangwon);
                break;
            case 11:
                townArray = res.getStringArray(R.array.spinner_region_chung_buk);
                break;
            case 12:
                townArray = res.getStringArray(R.array.spinner_region_chung_nam);
                break;
            case 13:
                townArray = res.getStringArray(R.array.spinner_region_jeon_buk);
                break;
            case 14:
                townArray = res.getStringArray(R.array.spinner_region_jeon_nam);
                break;
            case 15:
                townArray = res.getStringArray(R.array.spinner_region_gyeong_buk);
                break;
            case 16:
                townArray = res.getStringArray(R.array.spinner_region_gyeong_nam);
                break;
            case 17:
                townArray = res.getStringArray(R.array.spinner_region_jeju);
                break;

        }
    }


}
