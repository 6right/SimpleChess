package com.example.simplechess.Figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.field.Cell;
import com.example.simplechess.R;


// Класс фигуры ферзя
public class Queen extends Figure {
    public Queen(Context context, Position position, boolean isWhite, int height, int width){
        super(position,isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_qlt45 : R.drawable.chess_qdt45
        );
    }
    @Override
    public boolean canMove(Position selectedFigure) {
            int dx = Math.abs(position.getRow() - selectedFigure.getRow());
            int dy = Math.abs(position.getCol() - selectedFigure.getCol());
            if (dx == 0 || dy == 0 || dx == dy) { // если фигура Ферзь на той же вертикали, горизонтали или диагонали, что и выбранная позиция
                return true;
        }
        return false; // фигура Ферзь не может двигаться на данную позицию
    }
}
