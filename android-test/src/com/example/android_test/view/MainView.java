/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.example.android_test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-22 下午11:13
 */
public class MainView extends View {

    private Paint paint;

    public MainView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true); // 设置画笔抗锯齿
        this.setKeepScreenOn(true); // 设置背景长亮
        paint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE); // 设置刷屏颜色
        Rect rect = new Rect(30, 30, 50, 50); // 矩形左上角和右下角坐标构造矩形
        canvas.drawRect(rect, paint);
        RectF rectF = new RectF(70f, 30f, 90f, 90f); // RectF只是矩形的float形式, 只是跟Rect精度不一样
        canvas.drawArc(rectF, 0, 360, true, paint); // 矩形内切圆(椭圆)
        canvas.drawCircle(150, 30, 20, paint); // 根据x,y,半径构造圆
        paint.setColor(Color.BLUE);
        float[] points = new float[] {200f, 10f, 200f, 40f, 300f, 30f, 400f, 70f};
        canvas.drawLines(points, paint);
        paint.setColor(Color.GREEN);
        canvas.drawLines(points, 2, 5, paint); // 选取数组中的子集划线
        paint.setColor(Color.YELLOW);
        canvas.drawLines(points, 1, 4, paint);
        paint.setColor(Color.RED);
        canvas.drawText("iandrea", 230, 30, paint);
    }
}
