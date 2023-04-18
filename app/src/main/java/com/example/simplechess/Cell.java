package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

public class Cell {
    private int yPositionCenter;
    private int xPositionCenter;
    private int height;
    private int width;

    public Cell(int width, int height, int screenWidth, int screenHeight) {
        // Set position of field
        int size = Math.min(screenWidth, screenHeight);
        this.yPositionCenter = (screenHeight - size) / 2;
        this.xPositionCenter = (screenWidth - size) / 2;

        // Set size of cell
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