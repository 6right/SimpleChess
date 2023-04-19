package com.example.simplechess;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Figure {
    protected Position position;
    protected boolean isWhite;
    protected boolean isDead;
    Bitmap bitmap;

    public Figure(Position position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
    }

    public void draw(Canvas canvas) {
        if (isDead) return;
        int CELL_SIZE = Math.min(canvas.getWidth(), canvas.getHeight()) / 8;
        canvas.drawBitmap(bitmap, position.getX() * CELL_SIZE, position.getY() * CELL_SIZE, null);
    }

    public boolean canMove(Position position) {
        return false;
    }
}
