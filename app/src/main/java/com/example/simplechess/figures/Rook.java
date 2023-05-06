package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.R;

import java.util.ArrayList;

// Класс фигуры ладьи

public class Rook extends Figure {
    private boolean hasMoved = false;

    public Rook(Context context, boolean isWhite, int height, int width) {
        super(isWhite, height, width);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_rlt45 : R.drawable.chess_rdt45
        );
    }

    @Override
    public void move() {
        hasMoved = true;
    }

    @Override
    public ArrayList<Position> getAvailableMoves(Game game, Position position) {
        ArrayList<Position> availableMoves = new ArrayList<>();

        // Цикл для движения ладьи вверх
        for (int dy = 1; dy < 8; dy++) {
            Position newPosition = position.add(0, dy);
            if (!game.getField().isInside(newPosition)) {
                break;  // Выйти из цикла, если вышли за границы поля
            }
            Figure figure = game.getPlayer(isWhite).getFigure(newPosition);
            if (!game.getPlayer(isWhite).hasFigure(newPosition)) {
                if (figure.isWhite() != isWhite) {
                    availableMoves.add(newPosition);
                }
                break;  // Выйти из цикла, если встретили фигуру
            }
            availableMoves.add(newPosition);
        }

        // Цикл для движения ладьи вниз
        for (int dy = -1; dy > -8; dy--) {
            Position newPosition = position.add(0, dy);
            if (!game.getField().isInside(newPosition)) {
                break;
            }
            Figure figure = game.getPlayer(isWhite).getFigure(newPosition);
            if (figure != null) {
                if (figure.isWhite() != isWhite) {
                    availableMoves.add(newPosition);
                }
                break;
            }
            availableMoves.add(newPosition);
        }

        // Цикл для движения ладьи вправо
        for (int dx = 1; dx < 8; dx++) {
            Position newPosition = position.add(dx, 0);
            if (!game.getField().isInside(newPosition)) {
                break;
            }
            Figure figure = game.getPlayer(isWhite).getFigure(newPosition);
            if (figure != null) {
                if (figure.isWhite() != isWhite) {
                    availableMoves.add(newPosition);
                }
                break;
            }
            availableMoves.add(newPosition);
        }

        // Цикл для движения ладьи влево
        for (int dx = -1; dx > -8; dx--) {
            Position newPosition = position.add(dx, 0);
            if (!game.getField().isInside(newPosition)) {
                break;
            }
            Figure figure = game.getPlayer(isWhite).getFigure(newPosition);
            if (figure != null) {
                if (figure.isWhite() != isWhite) {
                    availableMoves.add(newPosition);
                }
                break;
            }
            availableMoves.add(newPosition);
        }

        return availableMoves;
    }
}
