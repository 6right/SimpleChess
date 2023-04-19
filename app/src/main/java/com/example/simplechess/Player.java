package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.simplechess.Figures.Figure;
import com.example.simplechess.Figures.Pawn;
import com.example.simplechess.Figures.Position;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Player {
    protected ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
    public Player(Context context, boolean isWhite, Cell cell) {
        for (int i = 0; i < 8; i++) {
            Position position = isWhite ? new Position(i, 1) : new Position(i, 6);
            Pawn pawn = new Pawn(context, position, isWhite, cell);
            figureMap.put(position, pawn);
        }
    }
    protected void draw(Canvas canvas) {
        for (Figure figure : figureMap.values()) {
            figure.draw(canvas);
        }
    }
    public void removeFigure(Position position) {
        figureMap.remove(position);
    }
    public void handleClick(int x, int y) {
        for (Position position : figureMap.keySet()) {
            Figure figure = figureMap.get(position);
            if (figure.contains(x, y)) {
                removeFigure(position);
            }
        }
    }
}
