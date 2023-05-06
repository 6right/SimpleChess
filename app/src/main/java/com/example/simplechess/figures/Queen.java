package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.Player;
import com.example.simplechess.R;

import java.util.ArrayList;

// Класс фигуры ферзя
public class Queen extends Figure {
    public Queen(Context context, Position position, boolean isWhite, int height, int width) {
        super(position, isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_qlt45 : R.drawable.chess_qdt45
        );
    }

    // TODO Переделать
    @Override
    public ArrayList<Position> getAvailableMoves(Game game) {
        ArrayList<Position> availableMoves = new ArrayList<>();
        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);
        // Проходимся по циклу позиции, которые может занять ферзь
        // Ферзь может ходить по вертикали, горизонтали и диагонали

        // Проверить, что по горизонтали нет фигур
        // Если есть фигура, то выйти из цикла
        // Если нет фигуры, то добавить позицию в список доступных позиций
        // Проходимся по циклу от фигуры до границы поля
        // Фигура на (0, 3)
        // Пешка на (2, 3)

        // (1, 3)


        for (int i = position.getRow() + 1; i < 8; i++) {
            Position checkPosition = new Position(i, position.getCol());
            boolean hasFigure = thisPlayer.hasFigure(checkPosition);

            if (hasFigure) {
                break;
            }
            availableMoves.add(checkPosition);
            if (enemyPlayer.hasFigure(checkPosition)) {
                break;
            }
        }

        return availableMoves;
    }
}
