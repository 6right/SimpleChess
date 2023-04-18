package com.example.simplechess;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

public class Bishop extends Figure{

    public Bishop (Context context, Position position, boolean isWhite){
        super(context, position, isWhite);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_blt45 : R.drawable.chess_bdt45
        );
    }


    public void draw(Canvas canvas) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.chess_bdt45);
        canvas.drawBitmap(bitmap, 500 , 500, null);
    }
}
