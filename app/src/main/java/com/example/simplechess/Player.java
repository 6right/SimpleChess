package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.simplechess.Figures.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Player {
    protected Figure selectedFigure = null;
    protected boolean isWhite;

    protected ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();

    public Player(Context context, boolean isWhite, Cell cell) {
        // Для расстановки фигур по y
        this.isWhite = isWhite;
        int y;

        y = isWhite ? 1 : 6;
        for (int i = 0; i < 8; i++) {
            figureMap.put(
                    new Position(i, y),
                    new Pawn(context, new Position(i, y), isWhite, cell)
            );
        }

        y = isWhite ? 0 : 7;
        for (int i = 0; i < 2; i++) {
            figureMap.put(
                    new Position(i * 7, y),
                    new Rook(context, new Position(i * 7, y), isWhite, cell)
            );

            figureMap.put(
                    new Position(i * 5 + 1, y),
                    new Knight(context, new Position(i * 5 + 1, y), isWhite, cell)
            );

            figureMap.put(
                    new Position(i * 3 + 2, y),
                    new Bishop(context, new Position(i * 3 + 2, y), isWhite, cell)
            );
        }

        figureMap.put(
                new Position(3, y),
                new Queen(context, new Position(3, y), isWhite, cell)
        );

        figureMap.put(
                new Position(4, y),
                new King(context, new Position(4, y), isWhite, cell)
        );
    }

    protected void draw(Canvas canvas) {
        for (Figure figure : figureMap.values()) {
            figure.draw(canvas);
        }
    }

    public void removeFigure(Position position) {
        figureMap.remove(position);
    }

    public void handleClick(Context context, Game game, int x, int y) {
        Field field = game.getField();
        int positionX = (x - field.getCell().getXPositionCenter()) / field.getCell().getWidth();
        int positionY = (y - field.getCell().getYPositionCenter()) / field.getCell().getHeight();
        Position newPosition = new Position(positionX, positionY);

        //Выбираем фигуру, проверяем что есть в списке.
        if (selectedFigure == null) {
            // Проходим по всем фигурам и проверяем, есть ли фигура на этой клетке
            for (Position position : figureMap.keySet()) {
                if (position.equals(newPosition)) {
                    selectedFigure = figureMap.get(position);
                    removeFigure(position);
                }
            }

        } else {
            // Ставим фигуру в новую клетку
            if (positionFree(newPosition, game.getPlayer(!isWhite)) && selectedFigure.canMove(newPosition)) {
                selectedFigure.setPosition(newPosition);
                figureMap.put(newPosition, selectedFigure);
                selectedFigure = null;
            } else {
                // Если клетка занята, то возвращаем фигуру на место
                figureMap.put(selectedFigure.getPosition(), selectedFigure);
                selectedFigure = null;
            }
        }
    }

    // Проверка на наличие другой фигуры на клетке (схожей по цвету)
    public boolean positionFree(Position position, Player otherPlayer) {
        for (Position pos : figureMap.keySet()) {
            if (position.equals(pos)) {
                return false;
            }
        }
        for (Position pos : otherPlayer.figureMap.keySet()) {
            if (position.equals(pos)) {
                return false;
            }
        }

        return true;
    }
}
