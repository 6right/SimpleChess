package com.example.simplechess;

// Класс, хранящий количество ячеек по горизонтали и вертикали
public class CellCounts {
    private int xQuantity;
    private int yQuantity;

    public CellCounts(int xQuantity, int yQuantity) {
        this.xQuantity = xQuantity;
        this.yQuantity = yQuantity;
    }

    public int getXQuantity() {
        return xQuantity;
    }

    public int getYQuantity() {
        return yQuantity;
    }
}
