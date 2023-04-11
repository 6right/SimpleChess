package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Field {

    private int xCel;
    private int yCel;

    Context context;
    public Field(Context context, Size size) {
        this.context = context;
        xCel = size.width;
        yCel = size.height;
    }
    protected void draw(Canvas canvas) {
//        Отрисовка поля

        int size = Math.min(canvas.getWidth(), canvas.getHeight());
        int yCenter = (canvas.getHeight() - size) / 2;
        int xCenter = (canvas.getWidth() - size) / 2;
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
