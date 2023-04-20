package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyboardShortcutInfo;

import com.example.simplechess.Figures.Bishop;
import com.example.simplechess.Figures.Figure;
import com.example.simplechess.Figures.King;
import com.example.simplechess.Figures.Knight;
import com.example.simplechess.Figures.Pawn;
import com.example.simplechess.Figures.Position;
import com.example.simplechess.Figures.Queen;
import com.example.simplechess.Figures.Rook;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Player {
    protected Figure figure;
    protected Figure selectedFigure = null;

    private Pawn pawn;
    private Rook rook;
    private Knight knight;
    private Bishop bishop;
    private Queen queen;
    private King king;

    protected ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
    public Player(Context context, boolean isWhite, Cell cell) {
        for (int i = 0; i < 8; i++) {
            Position position = isWhite ? new Position(i, 1) : new Position(i, 6);
            Pawn pawn = new Pawn(context, position, isWhite, cell);
            figureMap.put(position, pawn);
        }

        for (int i = 0; i < 2; i++) {
            Position rookPosition = isWhite ? new Position(i * 7, 0) : new Position(i * 7, 7);
            rook = new Rook(context, rookPosition, isWhite, cell);
            figureMap.put(rookPosition, rook);


            Position knightPosition = isWhite ? new Position(i * 5 + 1, 0) : new Position(i * 5 + 1, 7);
            knight = new Knight(context, knightPosition, isWhite, cell);
            figureMap.put(knightPosition, knight);

            Position bishopPosition = isWhite ? new Position(i * 3 + 2, 0) : new Position(i * 3 + 2, 7);
            bishop = new Bishop(context, bishopPosition, isWhite, cell);
            figureMap.put(bishopPosition, bishop);
        }

        Position queenPosition = isWhite ? new Position(3, 0) : new Position(3, 7);
        queen = new Queen(context, queenPosition, isWhite, cell);
        figureMap.put(queenPosition, queen);

        Position kingPosition = isWhite ? new Position(4, 0) : new Position(4, 7);
        king = new King(context, kingPosition, isWhite, cell);
        figureMap.put(kingPosition, king);
    }
    protected void draw(Canvas canvas) {
        for (Figure figure : figureMap.values()) {
            figure.draw(canvas);
        }
    }
    public void removeFigure(Position position) {
        figureMap.remove(position);
    }

    public void addFigure(Figure figure) {
        figureMap.put(figure.getPosition(), figure);
    }

    public void handleClick(Context context, Field field, int x, int y) {
        boolean removedFigure = false;
        for (Position position : figureMap.keySet()) {
            figure = figureMap.get(position);
            if (figure.contains(x, y) && selectedFigure == null) {
                selectedFigure = figure;
                removeFigure(position);
                removedFigure = true;
            }
        }
        // Проверка на то что у нас выбрана фигура, и поле пустое.
        if (!removedFigure && selectedFigure != null) {
        // Если условия выполнены, ставим выбранную фигуру в новую позицию на поле.
            int positionX = (x - field.getCell().getXPositionCenter()) / field.getCell().getWidth();
            int positionY = (y - field.getCell().getYPositionCenter()) / field.getCell().getHeight();
            selectedFigure.setPosition(positionX, positionY);
            figureMap.put(new Position(positionX, positionY), selectedFigure);
            selectedFigure = null;
        }
    }
}
