package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Queen extends Figure {
    public Queen(Context context, Position position, boolean isWhite, Cell cell) {
        super(position, isWhite, cell);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_qlt45 : R.drawable.chess_qdt45
        );
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
