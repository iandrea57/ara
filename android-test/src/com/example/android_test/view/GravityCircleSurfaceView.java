/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.example.android_test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.android_test.model.GravityCircle;

import java.util.Random;
import java.util.Vector;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-22 下午8:55
 */
public class GravityCircleSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    public static int SCREEN_H, SCREEN_W;
    private Thread th;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;
    private Vector<GravityCircle> vc; // 这里定义装我们自定义圆形的容器
    private Random ran; // 随机数

    public GravityCircleSurfaceView(Context context) {
        super(context);
        this.setKeepScreenOn(true);
        vc = new Vector<GravityCircle>();
        ran = new Random();
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.v("Ara", "GravityCircleSurfaceView.surfaceCreated");
        flag = true;
        th = new Thread(this);
        SCREEN_H = this.getHeight();
        SCREEN_W = this.getWidth();
        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2,
            int i3) {
        Log.v("Ara", "GravityCircleSurfaceView.surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        flag = false;
        Log.v("Ara", "GravityCircleSurfaceView.surfaceDestroyed");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            vc.addElement(new GravityCircle(ran.nextInt(this.getWidth()),
                    ran.nextInt(100), ran.nextInt(50) + 20));
            Log.v("Ara", "GravityCircleSurfaceView.onKeyDown()");
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            vc.addElement(new GravityCircle((int)event.getX(),
                    (int)event.getY(), ran.nextInt(50) + 20));
            Log.v("Ara", "GravityCircleSurfaceView.onTouchEvent()");
        }
        return true;
    }

    @Override
    public void run() {
        while (flag) {
            logic();
            draw();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                Log.e("Ara", "GravityCircleSurfaceView.run() sleep error");
            }
        }
    }

    /**
     * 作图
     */
    public void draw() {
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.BLACK);
            if (vc != null) {
                for (int i = 0; i < vc.size(); i++) {
                    vc.elementAt(i).drawMyArc(canvas, paint);
                }
            }
        } catch (Exception e) {
            Log.e("Ara", "GravityCircleSurfaceView.draw() error");
        } finally {
            try {
                if (canvas != null) {
                    sfh.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                Log.e("Ara", "GravityCircleSurfaceView unlockCanvas error");
            }
        }
    }

    /**
     * 主逻辑
     */
    private void logic() {
        if (vc != null) { // 当容器不为空, 便利容器中所有圆形逻辑
            for (int i = 0; i < vc.size(); i++) {
                vc.elementAt(i).logic();
            }
        }
    }
}
