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

        // Проверка по горизонтали
        for (int i = -1; i <= 1; i += 2) {
            Position checkPosition = new Position(position.getRow(), position.getCol() + i);

            while (game.getField().isInside(checkPosition)) {
                if (game.getPlayer(isWhite).hasFigure(checkPosition)) {
                    break;
                }
                availableMoves.add(checkPosition);
                if (game.getPlayer(!isWhite).hasFigure(checkPosition)) {
                    break;
                }

                checkPosition = checkPosition.add(0, i);
            }
        }

        // Проверка по вертикали
        for (int i = -1; i <= 1; i += 2) {
            Position checkPosition = new Position(position.getRow() + i, position.getCol());

            while (game.getField().isInside(checkPosition)) {
                if (game.getPlayer(isWhite).hasFigure(checkPosition)) {
                    break;
                }
                availableMoves.add(checkPosition);
                if (game.getPlayer(!isWhite).hasFigure(checkPosition)) {
                    break;
                }

                checkPosition = checkPosition.add(i, 0);
            }
        }

        return availableMoves;
    }
}
