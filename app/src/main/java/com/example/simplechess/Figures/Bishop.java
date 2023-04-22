package com.example.simplechess.Figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Cell;
import com.example.simplechess.R;

// Класс фигуры слона
public class Bishop extends Figure {

    public Bishop (Context context, Position position, boolean isWhite, Cell cell){
        super(position,isWhite, cell);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_blt45 : R.drawable.chess_bdt45
        );
    }

    @Override
    public boolean canMove(Position selectedFigure) {
        int dx = Math.abs(position.getX() - selectedFigure.getX());
        int dy = Math.abs(position.getY() - selectedFigure.getY());
        if (dx == dy) { // слон может двигаться только по диагонали, то есть dx должен быть равен dy
            return true;
        }
        return false;
    }
}
