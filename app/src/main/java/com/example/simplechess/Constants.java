package com.example.simplechess;

import android.graphics.Color;
import android.graphics.Paint;

public class Constants {

    // Colors

    protected static Paint brownPaint = new Paint();
    protected static Paint orangePaint = new Paint();
    protected static Paint blackPaint = new Paint();
    protected static Paint whitePaint = new Paint();

    static {
        blackPaint.setColor(Color.BLACK);
        whitePaint.setColor(Color.WHITE);
        brownPaint.setColor(Color.parseColor("#572200"));
        orangePaint.setColor(Color.parseColor("#DB9900"));
    }
}
