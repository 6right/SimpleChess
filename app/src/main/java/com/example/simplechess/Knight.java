package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

// Класс фигуры коня
public class Knight extends Figure {
    public Knight(Context context, Position position, boolean isWhite, Cell cell) {
        super(position, isWhite, cell);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_nlt45 : R.drawable.chess_ndt45
        );
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
