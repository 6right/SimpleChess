package com.example.simplechess;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class DrawingEntity {
    protected int height;
    protected int width;

    public DrawingEntity(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getXCoordinate(int leftTopX, int row) {
        return leftTopX + row * width;
    }

    public int getYCoordinate(int leftTopY, int col) {
        return leftTopY + col * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Отриосвка картинки
    public void draw(Canvas canvas, Bitmap bitmap, int xTop, int yLeft) {
        Rect dstRect = new Rect(xTop, yLeft, xTop + width, yLeft + height);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    // Отрисовка прямоугольника
    public void draw(Canvas canvas, int xLeft, int yTop, Paint paint) {
        canvas.drawRect(xLeft, yTop, xLeft + width, yTop + height, paint);
    }
}
