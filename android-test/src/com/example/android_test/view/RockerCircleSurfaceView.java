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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-22 上午2:12
 */
public class RockerCircleSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Thread th;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;
    // 固定摇杆背景圆形的X,Y坐标及半径
    private int RockerCircleX = 100;
    private int RockerCircleY = 100;
    private int RockerCircleR = 50;
    // 摇杆的X,Y坐标以及摇杆的半径
    private float SmallRockerCircleX = 100;
    private float SmallRockerCircleY = 100;
    private float SmallRockerCircleR = 20;

    public RockerCircleSurfaceView(Context context) {
        super(context);
        Log.v("Ara", "RockerCircleSurfaceView");
        this.setKeepScreenOn(true);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.v("Ara", "RockerCircleSurfaceView.surfaceCreated");
        th = new Thread(this);
        flag = true;
        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2,
            int i3) {
        Log.v("Ara", "RockerCircleSurfaceView.surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        flag = false;
        Log.v("Ara", "RockerCircleSurfaceView.surfaceDestroyed");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getAction() == MotionEvent.ACTION_MOVE) {
            // 当触屏区域不在活动范围内
            if (Math.sqrt(Math.pow((RockerCircleX - (int) event.getX()), 2)
                    + Math.pow((RockerCircleY - (int) event.getY()),
                    2)) >= RockerCircleR) {
                // 得到摇杆与触屏点所形成的角度
                double tempRad = getRad(RockerCircleX, RockerCircleY,
                        event.getX(), event.getY());
                // 保证内部小圆运动的长度限制
                getXY(RockerCircleX, RockerCircleY, RockerCircleR, tempRad);
            } else { // 如果小球中心点小于活动区域则随着用户触屏点移动即可
                SmallRockerCircleX = (int) event.getX();
                SmallRockerCircleY = (int) event.getY();
            }
        }
        return true;
    }

    /**
     * 定位摇杆位置
     *
     * @param centerX  圆周运动的旋转点X坐标
     * @param centerY  圆周运动的旋转点Y坐标
     * @param R        圆周运动半径
     * @param rad      旋转弧度
     */
    private void getXY(int centerX, int centerY, int R, double rad) {
        // 获取圆周运动的X坐标
        SmallRockerCircleX = (float) (R * Math.cos(rad)) + centerX;
        // 获取圆周运动的Y坐标
        SmallRockerCircleY = (float) (R * Math.sin(rad)) + centerY;
    }

    /**
     * 得到两点之间的弧度
     *
     * @param px1 点1 X坐标
     * @param py1 点1 Y坐标
     * @param px2 点2 X坐标
     * @param py2 点2 Y坐标
     * @return
     */
    public double getRad(float px1, float py1, float px2, float py2) {
        // 得到两点X的距离
        float x = px2 - px1;
        // 得到两点Y的距离
        float y = py1 - py2;
        // 算出斜边长
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        // 得到这个角度的余弦值
        float cosAngle = x / xie;
        // 通过反余弦定力获取其角度的弧度
        float rad = (float) Math.acos(cosAngle);
        // 注意: 当触屏的位置Y坐标<摇杆的Y坐标我们要取反值 -0 ~ -180
        if (py2 < py1) {
            rad = -rad;
        }
        return rad;
    }

    @Override
    public void run() {
        while (flag) {
            draw();
            try {
                Thread.sleep(50);
            } catch (Exception ex) {
                Log.e("Ara", "RockerCircleSurfaceView.run() sleep error");
            }
        }
    }


    public void draw() {
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.WHITE);
            // 设置透明度
            paint.setColor(0x70000000);
            // 绘制摇杆背景
            canvas.drawCircle(RockerCircleX, RockerCircleY, RockerCircleR,
                    paint);
            paint.setColor(0x70ff0000);
            // 绘制摇杆
            canvas.drawCircle(SmallRockerCircleX, SmallRockerCircleY,
                    SmallRockerCircleR, paint);
        } catch (Exception e) {
            Log.e("Ara", "RockerCircleSurfaceView.draw() failed");
        } finally {
            try {
                if (canvas != null) {
                    sfh.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e2) {
                Log.e("Ara", "RockerCircleSurfaceView.unlockCanvas failed");
            }
        }
    }
}
