package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.simplechess.Figures.Figure;
import com.example.simplechess.Figures.Pawn;
import com.example.simplechess.Figures.Position;

import java.util.HashMap;

public class Player {
    protected HashMap<Position, Figure> figureMap = new HashMap<>();
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
    public void handleClick(int x, int y) {
        for (Position position : figureMap.keySet()) {
            Figure figure = figureMap.get(position);
            if (figure.contains(x, y)) {
                Log.d("Player", "Clicked on position x = " + position.getX() + ", y = " + position.getY());
                break;
            }
        }
    }
}
