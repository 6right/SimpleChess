package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

public class Bishop {

    public void draw(Canvas canvas) {

        // нарисовать квадрат с помощью метода drawRect
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(200, 200, 300, 230, paint);
    }
}
