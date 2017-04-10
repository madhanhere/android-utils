package com.android.calibraint.android_utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AndroidUtils extends Application {

    private static final boolean YES = true;
    private static final boolean NO = false;

    private static final String EMAIL_PATTERN_1 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static final String EMAIL_PATTERN_2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

    public static AndroidUtils androidUtils;

    public static boolean nonEmpty(EditText editText) {
        if (editText != null && !(TextUtils.isEmpty(editText.getText().toString().trim()))) {
            return YES;
        } else {
            Log.d("SERI_PAR->Error", "edit text object is null");
            return NO;
        }
    }

    public static boolean nonEmpty(Context context, EditText editText, String msg) {
        if (nonEmpty(editText)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean validateEmail(EditText editText) {
        if (nonEmpty(editText)) {
            String emailAsString = removeBlankSpace(editText.getText().toString());
            return emailAsString.matches(EMAIL_PATTERN_1)
                    || emailAsString.matches(EMAIL_PATTERN_2);

        } else {
            Log.d("SERI_PAR->Error", "edit text object is null");
            return NO;
        }
    }

    public static boolean validateEmail(Context context, EditText editText, String msg) {
        if (validateEmail(editText)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean matchMinLength(EditText editText, int length) {
        if (nonEmpty(editText)) {
            String content = removeBlankSpace(editText.getText().toString());
            return content.length() >= length ? YES : NO;
        } else {
            Log.e("SERI_PAR->Error", "edit text object is null");
            return NO;
        }
    }

    public static boolean matchMinLength(Context context, EditText editText, int length, String msg) {
        if (matchMinLength(editText, length)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean noSpecialCharacters(EditText editText) {
        if (nonEmpty(editText)) {
            String content = removeBlankSpace(editText.getText().toString());
            return content.matches("[a-zA-Z0-9.? ]*");
        } else {
            Log.e("SERI_PAR->Error", "edit text object is null");
            return NO;
        }
    }

    public static boolean noSpecialCharacters(Context context, EditText editText, String msg) {
        if (noSpecialCharacters(editText)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean matchLength(EditText editText, int length) {
        if (nonEmpty(editText)) {
            String content = removeBlankSpace(editText.getText().toString());
            return content.length() == length;
        } else {
            Log.d("SERI_PAR->Error", "edit text object is null");
            return NO;
        }
    }

    public static boolean matchLength(Context context, EditText editText, int length, String msg) {
        if (matchLength(editText, length)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean mobileNumberValidation(EditText editText) {
        if (nonEmpty(editText)) {
            String mobileNumber = removeBlankSpace(editText.getText().toString().trim());
            return Patterns.PHONE.matcher(mobileNumber).matches();
        } else {
            Log.d("SERI_PAR->Error", "edit text object is null");
            return NO;
        }
    }

    public static boolean mobileNumberValidation(Context context, EditText editText, String msg) {
        if (mobileNumberValidation(editText)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean mobileNumberValidation(EditText editText, Pattern pattern) {
        if (nonEmpty(editText)) {
            String mobileNumber = removeBlankSpace(editText.getText().toString());
            return pattern.matcher(mobileNumber).matches();
        } else {
            return NO;
        }
    }

    public static boolean mobileNumberValidation(Context context, EditText editText, Pattern pattern, String msg) {
        if (mobileNumberValidation(editText, pattern)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean mobileNumberValidation(EditText editText, int minLength, int maxLength) {
        if (minLength > 0 && maxLength > 0 && nonEmpty(editText)) {
            String mobileNumber = removeBlankSpace(editText.getText().toString().trim());
            return mobileNumber.length() >= minLength && mobileNumber.length() <= maxLength;
        } else {
            return NO;
        }
    }

    public static boolean mobileNumberValidation(Context context, EditText editText, int minLength, int maxLength, String msg) {
        if (mobileNumberValidation(editText, minLength, maxLength)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean matchText(EditText baseEditText, EditText... editTexts) {
        if (nonEmpty(baseEditText)) {
            String matchString = baseEditText.getText().toString();
            for (EditText editText : editTexts) {
                if (editText == null || !(matchString.equals(editText.getText().toString()))) {
                    return NO;
                }
            }
        } else {
            return NO;
        }
        return YES;
    }

    public static boolean matchText(Context context, String msg, EditText baseEditText, EditText... editTexts) {
        if (matchText(baseEditText, editTexts)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean isInternetAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
        } else {
            return NO;
        }
    }

    public static boolean isInternetAvailable(Context context, String msg) {
        if (isInternetAvailable(context)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static boolean checkAllEditTexts(EditText... editTexts) {
        for (EditText edit : editTexts) {
            if (edit == null || !(edit.getText().toString().trim().length() > 0)) {
                return NO;
            }
        }
        return YES;
    }

    public static boolean checkAllEditTexts(Context context, String msg, EditText... editTexts) {
        if (checkAllEditTexts(editTexts)) {
            return YES;
        } else {
            showToast(context, msg);
            return NO;
        }
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String removeBlankSpace(String value) {
        value = value.replace(" ", "");
        return value;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        androidUtils = this;
    }

}
