package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class ChessField {
    public void draw(Canvas canvas) {
        // нарисовать квадрат с помощью метода drawRect
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 200, 200, paint);
    }
}
