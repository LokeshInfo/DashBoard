package com.impetrostock.im.utility;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.impetrostock.im.R;

public class Utilview {

    public static ArrayAdapter<String> setupAdapter(Activity activity, Spinner spinner, String[] stringArray, String spinner_title) {

        spinner.setPadding(0,0,0,0);
        String[] adapter_array = new String[0];
        if (stringArray.length > 0) {
            adapter_array = new String[stringArray.length + 1];
            adapter_array[0] = spinner_title;
            for (int i = 0; i < stringArray.length; i++) {
                adapter_array[i + 1] = stringArray[i];
            }
        }
        if (stringArray.length <= 0) {
            adapter_array = new String[1];
            adapter_array[0] = spinner_title;
        }
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_textitem, adapter_array) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(R.id.textView1);
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;
    }

    public static boolean isAppBack(FragmentActivity activity)
    {
        FragmentManager manager = activity.getSupportFragmentManager();
        Log.e("Fragment_Size",manager.getBackStackEntryCount()+" ...");
        if (manager.getBackStackEntryCount()>=1)
        {
            return false;
        }
        return true;
    }


    public static void hidekeyboard(FragmentActivity activity)
    {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView !=null)
        {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static String getRealPathFromURI(Uri uri,Activity activity) {

        Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
