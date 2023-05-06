package com.example.simplechess.figures;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.simplechess.DrawingEntity;
import com.example.simplechess.Game;

import java.util.ArrayList;

public abstract class Figure extends DrawingEntity {
    protected boolean isWhite;
    Bitmap bitmap;

    // Вкидываем сюда данные, которые нужны будут для отрисовки
    public Figure(boolean isWhite, int height, int width) {
        super(height, width);
        this.isWhite = isWhite;
    }

    // Надо разобраться, сделано путем тыка
    public void draw(Canvas canvas, int xTop, int yLeft) {
        draw(canvas, bitmap, xTop, yLeft);
    }

    public boolean isWhite() {
        return isWhite;
    }
    public int getXCoordinate(int leftTopX, Position position) {
        return getXCoordinate(leftTopX, position.getCol());
    }

    public int getYCoordinate(int leftTopY, Position position) {
        return getYCoordinate(leftTopY, position.getRow());
    }

    public ArrayList<Position> getAvailableMoves(Game game, Position position) {
        // Возвращаем список с одной позицией, на которой находится фигура
        ArrayList<Position> availableMoves = new ArrayList<>();
        availableMoves.add(position);
        return availableMoves;
    }

    public void move() { }
}
