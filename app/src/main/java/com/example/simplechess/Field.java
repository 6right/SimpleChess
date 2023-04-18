package com.example.simplechess;

import android.content.Context;
import android.graphics.Canvas;

public class Field {

    private CellCounts cellCounts;
    Cell cell;

    public Field(CellCounts cellCounts, ScreenSize screenSize) {
        // Set size of field
        this.cellCounts = cellCounts;

        // Set size of cell
        int sizeCell = Math.min(screenSize.getWidth(), screenSize.getHeight());
        int cellWidth = sizeCell / cellCounts.getXQuantity();
        int cellHeight = sizeCell / cellCounts.getYQuantity();
        cell = new Cell(cellWidth, cellHeight, screenSize.getWidth(), screenSize.getHeight());
    }

    protected void draw(Canvas canvas) {
        // Отрисовка поля
        for (int row = 0; row < cellCounts.getXQuantity(); row++) {
            for (int col = 0; col < cellCounts.getYQuantity(); col++) {
                cell.draw(canvas, row, col);
            }
        }
    }
}
