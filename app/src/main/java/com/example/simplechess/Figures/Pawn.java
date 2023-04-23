package com.example.simplechess.Figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.cells.Cell;
import com.example.simplechess.R;

// Класс фигуры пешки
public class Pawn extends Figure {
    private boolean hasMoved = false;
    public Pawn(Context context, Position position, boolean isWhite, Cell cell) {
        super(position, isWhite, cell);


        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_plt45 : R.drawable.chess_pdt45
        );
    }

    @Override
    public boolean canMove(Position selectedFigure) {
            int dx = Math.abs(position.getX() - selectedFigure.getX());
            int dy = position.getY() - selectedFigure.getY();

        if (dx == 0 && dy == (isWhite ? -2 : 2) && !hasMoved) {
            hasMoved = true;
            return true; // пешка может двигаться на две клетки вперед только на первом ходу
        } else if ((dy == 1 && !isWhite) || (dy == -1 && isWhite)) {
            hasMoved = true;
                return dx == 0; // пешка может двигаться на одну клетку вперед
        }
        return false; // пешка не может двигаться на данную позицию
    }
}
