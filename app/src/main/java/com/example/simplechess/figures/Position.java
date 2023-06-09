package com.example.simplechess.figures;

// Класс, хранящий координаты ячейки, не зависящий от размеров доски
// Используется для хранения координат фигур
public class Position {
    private int col;
    private int row;

    public Position() {
    }

    public Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Position add(Position other) {
        return new Position(this.row + other.row, this.col + other.col);
    }

    public Position add(int row, int col) {
        return new Position(this.row + row, this.col + col);
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
        return this.col == other.col && this.row == other.row;
    }

    @Override
    public int hashCode() {
        return col * 10 + row;
    }
}
