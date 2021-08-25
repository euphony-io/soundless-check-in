package com.example.soundlesscheck_in;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class EncryptedSPManager {

    private static String masterKeyAlias;
    private static SharedPreferences prefs;

    public static final String DEFAULT_VALUE_STRING = "No Data";

    private static SharedPreferences getPrefs(Context context) {
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            prefs = EncryptedSharedPreferences.create(
                    "info", masterKeyAlias, context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            return prefs;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPrefs(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);
        return value;
    }



}
