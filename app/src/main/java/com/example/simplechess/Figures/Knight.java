package com.example.simplechess.Figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.cells.Cell;
import com.example.simplechess.R;

// Класс фигуры коня
public class Knight extends Figure {
    public Knight(Context context, Position position, boolean isWhite, Cell cell) {
        super(position,isWhite, cell);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_nlt45 : R.drawable.chess_ndt45
        );
    }

    @Override
    public boolean canMove(Position selectedFigure){
            int dx = Math.abs(position.getX() - selectedFigure.getX());
            int dy = Math.abs(position.getY() - selectedFigure.getY());

            if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
                return true; // конь может двигаться в форме буквы L
        }
            return false; // конь не может двигаться на данную позицию
    }
}
