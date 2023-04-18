package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Cell {
    private int yPositionCenter;
    private int xPositionCenter;
    private int height;
    private int width;

    public Cell(Canvas canvas, int height, int width) {
        int size = Math.min(canvas.getWidth(), canvas.getHeight());
        this.yPositionCenter = (canvas.getHeight() - size) / 2;
        this.xPositionCenter = (canvas.getWidth() - size) / 2;
        this.height = height;
        this.width = width;
    }

    public Cell(int yPositionCenter, int xPositionCenter, int height, int width) {
        this.yPositionCenter = yPositionCenter;
        this.xPositionCenter = xPositionCenter;
        this.height = height;
        this.width = width;
    }

    public void draw(Canvas canvas, int row, int col) {
        int x = col * width + xPositionCenter;
        int y = row * height + yPositionCenter;
        if ((row + col) % 2 == 0) {
            canvas.drawRect(x, y, x + width, y + height, orangePaint);
        } else {
            canvas.drawRect(x, y, x + width, y + height, whitePaint);
        }
    }
}
