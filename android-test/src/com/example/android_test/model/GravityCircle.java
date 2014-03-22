/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.example.android_test.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.example.android_test.view.GravityCircleSurfaceView;

import java.util.Random;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-22 下午1:10
 */
public class GravityCircle {

    // 圆形的随机颜色集合
    private static final int[] RAN_COLORS = {Color.WHITE, Color.BLUE,
            Color.CYAN, Color.DKGRAY, Color.RED, Color.GREEN, Color.GRAY,
            Color.YELLOW, Color.MAGENTA};

    private int arc_x, arc_y, arc_r; // 圆形的X,Y坐标和半径
    private float distance_x = 1.2f, distance_y = 5.2f; // 小球的x,y的距离
    private float vertical_speed; // 速度
    private float horizontal_speed; // 水平速度
    private final float ACC = 0.135f; // 加速度
    private final float RECESSION = 0.2F; // 每次谈起的衰退系数
    private boolean isDown = true; // 是否处于下落状态
    private Random ran; // 随机数
    private int color;

    /**
     * 重力圆形的构造函数
     * @param x 圆形X坐标
     * @param y 圆形Y坐标
     * @param r 圆形半径
     */
    public GravityCircle(int x, int y, int r) {
        ran = new Random();
        this.arc_x = x;
        this.arc_y = y;
        this.arc_r = r;
        this.color = RAN_COLORS[ran.nextInt(RAN_COLORS.length)];
    }

    /**
     * 绘制圆
     *
     * @param canvas 画布
     * @param paint 画笔
     */
    public void drawMyArc(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawArc(new RectF(arc_x + distance_x, arc_y + distance_y,
                arc_x + 2 * arc_r + distance_x, arc_y + 2 * arc_r + distance_y),
                0, 360, true, paint);
    }

    /**
     * 圆逻辑
     */
    public void logic() {
        if (isDown) { // 圆形下落逻辑
            distance_y += vertical_speed; // 圆形在Y轴上的距离
            int count = (int) vertical_speed ++;
            for (int i = 0; i < count; i++) {
                vertical_speed += ACC;
            }
        } else { //圆形反弹逻辑
            distance_y -= vertical_speed;
            int count = (int) vertical_speed --;
            for (int i = 0; i < count; i++) {
                vertical_speed -= ACC;
            }
        }
        if (isCollision()) {
            isDown = !isDown; // 当发生碰撞说明圆形的方向要改变一下
            vertical_speed -= vertical_speed * RECESSION;
        }
    }

    private boolean isCollision() {
        return arc_y + 2 * arc_r + distance_y >= GravityCircleSurfaceView.SCREEN_H;
    }

}
