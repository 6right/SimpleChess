package com.example.simplechess.field;

// Класс, хранящий количество ячеек по горизонтали и вертикали
public class CellCounts {
    private int rowQuantity;
    private int colQuantity;

    public CellCounts(int rowQuantity, int colQuantity) {
        this.rowQuantity = rowQuantity;
        this.colQuantity = colQuantity;
    }

    public int getRowQuantity() {
        return rowQuantity;
    }

    public int getColQuantity() {
        return colQuantity;
    }
}
