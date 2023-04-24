package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.Player;
import com.example.simplechess.R;

import java.util.ArrayList;

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

    // TODO Переделать
    @Override
    public ArrayList<Position> getAvailableMoves(Game game){
        ArrayList<Position> availableMoves = new ArrayList<>();
        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);
        // Проходимся по циклу позиции, которые может занять ферзь
        // Ферзь может ходить по вертикали, горизонтали и диагонали
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i, j);
                // Если фигура может ходить на данную позицию
                if (canMove(position)) {
                    // Если на данной позиции нет фигуры
                    if (thisPlayer.getFigure(position) == null) {
                        availableMoves.add(position);
                    }
                    // Если на данной позиции есть фигура
                    else {
                        // Если фигура противника
                        if (enemyPlayer.getFigure(position) != null) {
                            availableMoves.add(position);
                        }
                        break;
                    }
                }
            }
        }

        return availableMoves;
    }
}
