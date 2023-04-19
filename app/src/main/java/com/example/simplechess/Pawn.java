package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

// Класс фигуры пешки
public class Pawn extends Figure {
    public Pawn(Context context, Position position, boolean isWhite, Cell cell) {
        super(position, isWhite, cell);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_plt45 : R.drawable.chess_pdt45
        );
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
