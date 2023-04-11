package com.example.simplechess;

import android.graphics.Bitmap;

public class Figure {
    public Position position;
    public boolean isDead;
    Bitmap bitmap;

    public boolean canMove(Position position) {
        return false;
    }
}
