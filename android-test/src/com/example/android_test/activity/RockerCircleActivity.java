/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.example.android_test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.android_test.view.RockerCircleSurfaceView;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-22 上午3:01
 */
public class RockerCircleActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new RockerCircleSurfaceView(this));
    }
}
