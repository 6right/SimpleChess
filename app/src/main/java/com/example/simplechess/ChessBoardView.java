package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ChessBoardView extends View {
    private Paint brownPaint = new Paint();

    private Paint orangePaint = new Paint();
    private Paint blackPaint = new Paint();
    private Paint whitePaint = new Paint();

    public ChessBoardView(Context context) {
        super(context);
        blackPaint.setColor(Color.BLACK);
        whitePaint.setColor(Color.WHITE);
        brownPaint.setColor(Color.parseColor("#572200"));
        orangePaint.setColor(Color.parseColor("#DB9900"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//       Отрисовка всего поля без клеток
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int left = (width - size) / 2;
        int top = (height - size) / 2;
        int right = left + size;
        int bottom = top + size;

        canvas.drawRect(left, top, right, bottom, brownPaint);


//        Отрисовка клеток

        int cellWidth = (size - 100) / 8;
        int cellHeight = (size - 100) / 8;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;
                if ((row + col) % 2 == 0) {
                    canvas.drawRect(x, y, x + cellWidth, y + cellHeight, orangePaint);
                } else {
                    canvas.drawRect(x, y, x + cellWidth, y + cellHeight, whitePaint);
                }
            }
        }
    }
}
