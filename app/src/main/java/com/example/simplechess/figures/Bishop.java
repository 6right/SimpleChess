package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.Player;
import com.example.simplechess.R;

import java.util.ArrayList;

// Класс фигуры слона
public class Bishop extends Figure {

    public Bishop(Context context, Position position, boolean isWhite, int height, int width) {
        super(position, isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_blt45 : R.drawable.chess_bdt45
        );
    }

    public ArrayList<Position> getAvailableMoves(Game game) {
        ArrayList<Position> availableMoves = new ArrayList<>();
        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);
        // Проходимся по циклу позиции, которые может занять слон
        // Слон может ходить только по диагонали
        // Также проверяем, чтобы он не ходил за границы поля
        for (int i = -7; i <= 7; i++) {
            for (int j = -7; j <= 7; j++) {
                if (Math.abs(i) == Math.abs(j)) {
                    Position newPosition = position.add(i, j);
                    // Если позиция находится в пределах доски
                    if (game.getField().isInside(newPosition)) {
                        // Если на позиции нет фигуры или фигура противника
                        // Проверка на присутствие фигуры противника не нужна, так как
                        // присутствие или отсутствие фигуры врага не влияет на возможность хода
                        if (!thisPlayer.hasFigure(newPosition)) {
                            availableMoves.add(newPosition);
                        }
                    }
                }
            }
        }
        return availableMoves;
    }
}
