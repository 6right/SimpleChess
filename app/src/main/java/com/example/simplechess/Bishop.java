package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Bishop extends Figure{

    public Bishop (Context context, Position position, boolean isWhite, Cell cell){
        super(position, isWhite, cell);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_blt45 : R.drawable.chess_bdt45
        );
    }
}
