package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.simplechess.Figures.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Player {
    protected Figure figure;
    protected Figure selectedFigure = null;

    protected ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();

    public Player(Context context, boolean isWhite, Cell cell) {
        // Для расстановки фигур по y
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

    public void handleClick(Context context, Field field, int x, int y) {
        // Проходим по всем фигурам и проверяем попадает ли координата в область фигуры.
        if(selectedFigure == null) {
            for (Position position : figureMap.keySet()) {
                figure = figureMap.get(position);
                // Если попадает, то выбираем фигуру и удаляем ее из поля.
                if (figure.contains(x, y) && selectedFigure == null) {
                    selectedFigure = figure;
                    removeFigure(position);
                }
            }
        }
        else {
            int positionX = (x - field.getCell().getXPositionCenter()) / field.getCell().getWidth();
            int positionY = (y - field.getCell().getYPositionCenter()) / field.getCell().getHeight();
            Position newPosition = new Position(positionX, positionY);

            // Проверка на свободно ли место для фигуры или нет.
            if (positionFree(newPosition)) {
                selectedFigure.setPosition(newPosition);
                figureMap.put(newPosition, selectedFigure);
                selectedFigure = null;
            }
        }
    }

    public boolean positionFree(Position position) {
        return !figureMap.containsKey(position);
    }
}

