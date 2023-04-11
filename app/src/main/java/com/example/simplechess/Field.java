package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Field extends View {

    private int xCel;
    private int yCel;
    public Field(Context context, Size size) {
        super(context);
        xCel = size.width;
        yCel = size.height;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Отрисовка поля

        int size = Math.min(getWidth(), getHeight());
        int yCenter = (getHeight() - size) / 2;
        int xCenter = (getWidth() - size) / 2;
        int cellWidth = size / yCel;
        int cellHeight = size / xCel;
        for (int row = 0; row < xCel; row++) {
            for (int col = 0; col < yCel; col++) {
                int x = col * cellWidth + xCenter;
                int y = row * cellHeight + yCenter;
                if ((row + col) % 2 == 0) {
                    canvas.drawRect(x, y, x + cellWidth, y + cellHeight, orangePaint);
                } else {
                    canvas.drawRect(x, y, x + cellWidth, y + cellHeight, whitePaint);
                }
            }
        }
    }
}
