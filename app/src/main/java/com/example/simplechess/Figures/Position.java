package com.example.simplechess.Figures;

// Класс, хранящий координаты ячейки, не зависящий от размеров доски
// Используется для хранения координат фигур
public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Я как понял, это нужно для того, чтобы можно было сравнивать позиции
    // Оно хранит позицию в виде одного числа, которое можно сравнивать
    @Override
    public int hashCode() {
        return row * 10 + col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }
}
