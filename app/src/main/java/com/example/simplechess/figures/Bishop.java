package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.Player;
import com.example.simplechess.R;

import java.util.ArrayList;

// Класс фигуры слона
public class Bishop extends Figure {

    public Bishop(Context context, boolean isWhite, int height, int width) {
        super(isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_blt45 : R.drawable.chess_bdt45
        );
    }

    public ArrayList<Position> getAvailableMoves(Game game, Position position) {
        ArrayList<Position> availableMoves = new ArrayList<>();
        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);
        // Границы поля
        int maxRow = game.getField().getCellCounts().getRowQuantity() - 1;
        int maxCol = game.getField().getCellCounts().getColQuantity() - 1;

        // Проходимся по всем возможным направлениям относительно position
        // Отталкиваем от row и col и прибавляем к ним i и j
        // Проверяем, что позиция не выходит за границы поля
        // Если позиция не выходит за границы поля, то проверяем, что на этой позиции нет фигуры
        // Если на этой позиции нет фигуры, то добавляем ее в availableMoves
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <=1; j += 2) {
                Position checkPosition = new Position(position.getRow() + i, position.getCol() + j);

                while (game.getField().isInside(checkPosition)) {
                    if (thisPlayer.hasFigure(checkPosition)) {
                        break;
                    }
                    availableMoves.add(checkPosition);
                    if (enemyPlayer.hasFigure(checkPosition)) {
                        break;
                    }

                    checkPosition = checkPosition.add(i, j);
                }
            }
        }

        return availableMoves;
    }
}
