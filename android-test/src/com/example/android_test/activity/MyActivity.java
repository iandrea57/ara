package com.example.android_test.activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.android_test.R;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
