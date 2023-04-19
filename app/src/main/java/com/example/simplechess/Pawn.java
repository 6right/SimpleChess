package com.example.simplechess;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Pawn extends Figure {
    public Pawn(Context context, Position position, boolean isWhite) {
        super(position, isWhite);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_plt45 : R.drawable.chess_pdt45
        );
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
//    @Override
//    public void draw(Canvas canvas) {
//        if (isDead) return;
//        int CELL_SIZE = Math.min(canvas.getWidth(), canvas.getHeight()) / 8;
//        canvas.drawBitmap(bitmap, position.x * CELL_SIZE, position.y * CELL_SIZE, null);
//    }

}
