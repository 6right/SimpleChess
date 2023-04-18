package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Figure {
    protected Position position;
    protected Context context;
    protected boolean isWhite;
    protected boolean isDead;
    Bitmap bitmap;

    public Figure(Context context, Position position, boolean isWhite) {
        this.context = context;
        this.position = position;
        this.isWhite = isWhite;
    }

    public void draw(Canvas canvas) {
        if (isDead) return;
        int CELL_SIZE = Math.min(canvas.getWidth(), canvas.getHeight()) / 8;
        canvas.drawBitmap(bitmap, position.x * CELL_SIZE, position.y * CELL_SIZE, null);
    }

    public boolean canMove(Position position) {
        return false;
    }
}
