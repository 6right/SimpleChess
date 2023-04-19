package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

public class King extends Figure {
    public King(Context context, Position position, boolean isWhite) {
        super(position, isWhite);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_klt45 : R.drawable.chess_kdt45
        );
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
