package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.Player;
import com.example.simplechess.field.Cell;
import com.example.simplechess.R;

import java.util.ArrayList;

// Класс фигуры коня
public class Knight extends Figure {
    public Knight(Context context, Position position, boolean isWhite, int height, int width){
        super(position,isWhite, height, width);

        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_nlt45 : R.drawable.chess_ndt45
        );
    }

    @Override
    public boolean canMove(Position selectedFigure){
            int dx = Math.abs(position.getRow() - selectedFigure.getRow());
            int dy = Math.abs(position.getCol() - selectedFigure.getCol());

            if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
                return true; // конь может двигаться в форме буквы L
        }
            return false; // конь не может двигаться на данную позицию
    }

    @Override
    public ArrayList<Position> getAvailableMoves(Game game){
        ArrayList<Position> availableMoves = new ArrayList<>();

        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);

        // Проходимся по циклу позиции, которые может занять конь
        // Конь может ходить только буквой L
        // Также проверяем, чтобы он не ходил за границы поля
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (Math.abs(i) + Math.abs(j) == 3) {
                    Position newPosition = position.add(i, j);
                    // Если позиция находится в пределах доски
                    if (game.getField().isInside(newPosition)) {
                        // Если на позиции нет фигуры или фигура противника
                        // Проверка на присутствие фигуры противника не нужна, так как
                        // присутствие или отсутствие фигуры врага не влияет на возможность хода
                        if (thisPlayer.getFigure(newPosition) == null) {
                            availableMoves.add(newPosition);
                        }
                    }
                }
            }
        }

        return availableMoves;
    }
}
