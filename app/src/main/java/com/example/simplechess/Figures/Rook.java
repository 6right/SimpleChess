package com.example.simplechess.Figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Cell;
import com.example.simplechess.R;

// Класс фигуры ладьи
public class Rook extends Figure {
    public Rook(Context context, Position position, boolean isWhite, Cell cell) {
        super(position,isWhite, cell);
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
