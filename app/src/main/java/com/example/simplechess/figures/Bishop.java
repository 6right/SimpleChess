package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.R;

// Класс фигуры слона
public class Bishop extends Figure {

    public Bishop (Context context, Position position, boolean isWhite, int height, int width){
        super(position,isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_blt45 : R.drawable.chess_bdt45
        );
    }

    @Override
    public boolean canMove(Position selectedFigure) {
        int dx = Math.abs(position.getCol() - selectedFigure.getCol());
        int dy = Math.abs(position.getRow() - selectedFigure.getRow());
        if (dx == dy) { // слон может двигаться только по диагонали, то есть dx должен быть равен dy
            return true;
        }
        return false;
    }
}
