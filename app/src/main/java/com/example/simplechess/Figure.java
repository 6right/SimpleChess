package com.example.simplechess;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Figure {
    private int yPositionCenter;
    private int xPositionCenter;
    private int height;
    private int width;

    protected Position position;
    protected boolean isWhite;
    protected boolean isDead;
    Bitmap bitmap;

    public Figure(Position position, boolean isWhite, Cell cell) {
        this.position = position;
        this.isWhite = isWhite;
        this.isDead = false;
        this.yPositionCenter = cell.getYPositionCenter();
        this.xPositionCenter = cell.getXPositionCenter();
        this.height = cell.getHeight();
        this.width = cell.getWidth();
    }

    public void draw(Canvas canvas) {
        int cellX = position.getX() * width + xPositionCenter;
        int cellY = position.getY() * height + yPositionCenter;
        Rect dstRect = new Rect(cellX, cellY, cellX + width, cellY + height);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }


    public boolean canMove(Position position) {
        return false;
    }
}
