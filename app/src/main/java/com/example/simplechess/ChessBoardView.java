package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ChessBoardView extends View {
    private Paint blackPaint = new Paint();
    private Paint whitePaint = new Paint();

    public ChessBoardView(Context context) {
        super(context);
        blackPaint.setColor(Color.GREEN);
        whitePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / 8;
        int cellHeight = height / 8;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;
                if ((row + col) % 2 == 0) {
                    canvas.drawRect(x, y, x + cellWidth, y + cellHeight, blackPaint);
                } else {
                    canvas.drawRect(x, y, x + cellWidth, y + cellHeight, whitePaint);
                }
            }
        }
    }
}
