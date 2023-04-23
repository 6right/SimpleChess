package com.example.simplechess;

import android.graphics.Color;
import android.graphics.Paint;

public class Constants {

    //  Colors

    // Используется для отрисовки ячеек, на которые можно сходить
    public static Paint brownPaint = new Paint();
    public static Paint orangePaint = new Paint();
    public static Paint blackPaint = new Paint();
    public static Paint whitePaint = new Paint();
    public static Paint yellowPaint = new Paint();

    static {
        blackPaint.setColor(Color.BLACK);
        whitePaint.setColor(Color.WHITE);
        brownPaint.setColor(Color.parseColor("#572200"));
        orangePaint.setColor(Color.parseColor("#DB9900"));
        yellowPaint.setColor(Color.parseColor("#FFFF00"));
    }
}
