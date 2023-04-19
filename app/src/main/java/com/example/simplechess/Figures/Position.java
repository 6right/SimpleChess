package com.example.simplechess.Figures;

// Класс, хранящий координаты ячейки, не зависящий от размеров доски
// Используется для хранения координат фигур
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
