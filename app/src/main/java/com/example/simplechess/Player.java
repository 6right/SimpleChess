package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;

import com.example.simplechess.Figures.*;
import com.example.simplechess.cells.Cell;
import com.example.simplechess.cells.Field;
import com.example.simplechess.cells.HelpCell;

import java.util.concurrent.ConcurrentHashMap;

public class Player {
    protected Figure selectedFigure = null;
    protected boolean isWhite;
    Canvas canvas;
    Cell cell;

    protected ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<Position, HelpCell> helpCell = new ConcurrentHashMap<>();

    public Player(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
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
        for (HelpCell hCell : helpCell.values()){
            hCell.draw(canvas);
        }
    }

    public void removeFigure(Position position) {
        figureMap.remove(position);
    }

    public void handleClick(Context context, Game game, int x, int y) {
        Field field = game.getField();
        int clickedPositionX = (x - field.getCell().getXPositionCenter()) / field.getCell().getWidth();
        int clickedPositionY = (y - field.getCell().getYPositionCenter()) / field.getCell().getHeight();
        Position clickedPosition = new Position(clickedPositionX, clickedPositionY);

        if (selectedFigure == null) {
            // Выбираем фигуру, проверяем что есть в списке.
            // Проходим по всем фигурам и проверяем, есть ли фигура на этой клетке
            for (Position position : figureMap.keySet()) {
                if (position.equals(clickedPosition)) {
                    selectFigure(position);
                }
            }
        } else {
            if (positionFree(clickedPosition, game) && selectedFigure.canMove(clickedPosition)) {
                // Ставим фигуру в новую клетку
                moveFigure(clickedPosition);
            } else {
                // Возвращаем фигуру на старое место
                returnFigure();
            }
        }
    }

    public void selectFigure(Position position) {
        helpCell.put(position, new HelpCell(position, cell));
        selectedFigure = figureMap.get(position);
        removeFigure(position);
    }

    public void moveFigure(Position position) {
        selectedFigure.setPosition(position);
        figureMap.put(position, selectedFigure);
        helpCell.clear();
        selectedFigure = null;
    }

    public void returnFigure() {
        figureMap.put(selectedFigure.getPosition(), selectedFigure);
        helpCell.clear();
        selectedFigure = null;
    }

    // Проверка на наличие другой фигуры на клетке (схожей по цвету)
    public boolean positionFree(Position position, Game game) {
        for (Position pos : figureMap.keySet()) {
            if (position.equals(pos)) {
                return false;
            }
        }
        for (Position pos : game.getPlayer(!isWhite).figureMap.keySet()) {
            if (position.equals(pos)) {
                return false;
            }
        }
        return true;
    }
}
