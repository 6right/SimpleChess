package com.example.simplechess.cells;

import static com.example.simplechess.Constants.*;

import android.graphics.Canvas;

public class Cell {
    // Охота перенсти в отдельный класс Object, а то используется в нескольких классах
    private int yPositionCenter;
    private int xPositionCenter;
    private int height;
    private int width;

    // Конструктор, который принимает размеры экрана и размеры клетки
    public Cell(int width, int height, int screenWidth, int screenHeight) {
        // Set position of field
        int size = Math.min(screenWidth, screenHeight);
        this.yPositionCenter = (screenHeight - size) / 2;
        this.xPositionCenter = (screenWidth - size) / 2;

        // Set size of cell
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXPositionCenter() {
        return xPositionCenter;
    }

    public int getYPositionCenter() {
        return yPositionCenter;
    }

    // Отрисовка клетки
    public void draw(Canvas canvas, int row, int col) {
        int x = col * width + xPositionCenter;
        int y = row * height + yPositionCenter;
        if ((row + col) % 2 == 0) {
            canvas.drawRect(x, y, x + width, y + height, orangePaint);
        } else {
            canvas.drawRect(x, y, x + width, y + height, whitePaint);
        }
    }
}
