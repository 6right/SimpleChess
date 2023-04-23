package com.example.simplechess.cells;

// Класс, хранящий размеры экрана
public class ScreenSize {
    private int width;
    private int height;

    public ScreenSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
