/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.processing.demo;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-24 上午10:44
 */
public class PTest extends PApplet {
    PImage selected;
    float hueLv;
    int spacing = 12;
    int sideLen;

    public void setup() {
        selected = loadImage(
                PTest.class.getResource("/").getPath() + "apple.jpg");
        selected.resize(400, 0);

        selected.filter(THRESHOLD, 0.9f);
//        selected.filter(INVERT);
        selected.filter(BLUR, (spacing * 2 / 3));

        size(selected.width, selected.height);
        background(255);
        noLoop();
    }

    public void draw() {
        for (int i = spacing / 2; i < selected.width; i += spacing) {
            if (map(i, 0, selected.width - 1, 0, 255) - 156 < 0) {
                hueLv = map(i, 0, selected.width - 1, 0, 255) + 100;
            } else {
                hueLv = map(i, 0, selected.width - 1, 0, 255) - 156;
            }
            println(hueLv);
            colorMode(HSB);
            fill(hueLv, 150, 220);
            for (int j = spacing / 2; j < selected.height; j += spacing) {
                sideLen = (int) (map(brightness(selected.get(i, j)), 0, 255, 0,
                        spacing * 7 / 8));
                Rrect(i, j, sideLen);
            }
        }
    }

    void Rrect(int x, int y, int side) {
        noStroke();
        beginShape();
        vertex(x - side * 3 / 8, y - side / 2);
        vertex(x + side * 3 / 8, y - side / 2);
        arc(x + side * 3 / 8, y - side * 3 / 8, side / 4, side / 4, -HALF_PI,
                0);
        vertex(x + side / 2, y - side * 3 / 8);
        vertex(x + side / 2, y + side * 3 / 8);
        arc(x + side * 3 / 8, y + side * 3 / 8, side / 4, side / 4, 0, HALF_PI);
        vertex(x + side * 3 / 8, y + side / 2);
        vertex(x - side * 3 / 8, y + side / 2);
        arc(x - side * 3 / 8, y + side * 3 / 8, side / 4, side / 4, HALF_PI,
                PI);
        vertex(x - side / 2, y + side * 3 / 8);
        vertex(x - side / 2, y - side * 3 / 8);
        arc(x - side * 3 / 8, y - side * 3 / 8, side / 4, side / 4, PI,
                PI + HALF_PI);
        endShape();
    }
}
