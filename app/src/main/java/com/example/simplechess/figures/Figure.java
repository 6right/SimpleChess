package com.example.simplechess.figures;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.simplechess.DrawingEntity;
import com.example.simplechess.Game;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Figure extends DrawingEntity {
    // Позиция фигуры на доске
    protected Position position;
    // Для понимания какую фигуру нужно отрисовать
    protected boolean isWhite;
    // Для отрисовки, тут хранится картинка
    protected int id;
    Bitmap bitmap;

    // Вкидываем сюда данные, которые нужны будут для отрисовки
    public Figure(int id, Position position, boolean isWhite, int height, int width) {
        super(height, width);
        this.id = id;
        this.position = position;
        this.isWhite = isWhite;
    }

    // Надо разобраться, сделано путем тыка
    public void draw(Canvas canvas, int xTop, int yLeft) {
        draw(canvas, bitmap, xTop, yLeft);
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getIdString() {
        return Integer.toString(id);
    }

    public int getXCoordinate(int leftTopX) {
        return getXCoordinate(leftTopX, position.getCol());
    }

    public int getYCoordinate(int leftTopY) {
        return getYCoordinate(leftTopY, position.getRow());
    }

    public abstract boolean canMove(Position position);

    public ArrayList<Position> getAvailableMoves(Game game){
        // Возвращаем список с одной позицией, на которой находится фигура
        ArrayList<Position> availableMoves = new ArrayList<>();
        availableMoves.add(position);
        return availableMoves;
    }

    public void move(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Figure)) {
            return false;
        }
        Figure figure = (Figure) obj;
        return this.id == figure.getId() && this.isWhite == figure.isWhite();
    }
}
