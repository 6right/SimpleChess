package com.example.simplechess.Figures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.simplechess.cells.Cell;

public abstract class Figure {
    // Взял из Cell, думаю как-то вынести в отдельный класс Object
    private int yPositionCenter;
    private int xPositionCenter;
    private int height;
    private int width;

    // Позиция фигуры на доске
    protected Position position;
    // Для понимания какую фигуру нужно отрисовать
    protected boolean isWhite;
    // Для отрисовки, тут хранится картинка
    Bitmap bitmap;

    // Вкидываем сюда данные, которые нужны будут для отрисовки
    public Figure(Position position, boolean isWhite, Cell cell) {
        this.position = position;
        this.isWhite = isWhite;
        yPositionCenter = cell.getYPositionCenter();
        xPositionCenter = cell.getXPositionCenter();
        height = cell.getHeight();
        width = cell.getWidth();
    }

    // Надо разобраться, сделано путем тыка
    public void draw(Canvas canvas) {
        int cellX = position.getX() * width + xPositionCenter;
        int cellY = position.getY() * height + yPositionCenter;
        Rect dstRect = new Rect(cellX, cellY, cellX + width, cellY + height);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public abstract boolean canMove(Position position);
}
