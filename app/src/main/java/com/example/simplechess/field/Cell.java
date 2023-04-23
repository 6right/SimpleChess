package com.example.simplechess.field;

import com.example.simplechess.DrawingEntity;

public class Cell extends DrawingEntity {
    // Конструктор, который принимает размеры экрана и размеры клетки
    public Cell(int height, int width) {
        super(height, width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
