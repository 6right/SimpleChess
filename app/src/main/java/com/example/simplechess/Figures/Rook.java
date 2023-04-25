package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.R;

// Класс фигуры ладьи
public class Rook extends Figure {
    public Rook(Context context, Position position, boolean isWhite, int height, int width){
        super(position,isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_rlt45 : R.drawable.chess_rdt45
        );
    }

    @Override
    public boolean canMove(Position selectedFigure) {
            int dx = Math.abs(position.getCol() - selectedFigure.getCol());
            int dy = Math.abs(position.getRow() - selectedFigure.getRow());
            if ((dx == 0 && dy > 0) || (dx > 0 && dy == 0)) {
                return true; // ладья может двигаться на данную позицию
        }
        return false; // ладья не может двигаться на данную позицию
    }
}
