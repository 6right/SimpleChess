package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Rook extends Figure {
    public Rook(Context context, Position position, boolean isWhite) {
        super(position, isWhite);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_rlt45 : R.drawable.chess_rdt45
        );
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
