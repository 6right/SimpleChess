package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;

public class Field {

    private int xCelQuantity = 8;
    private int yCelQuantity = 8;
    Cell cell;

    public Field(Canvas canvas, Size size) {
        // Set size of field
        this.xCelQuantity = size.width;
        this.yCelQuantity = size.height;

        // Set size of cell
        int sizeCell = Math.min(canvas.getWidth(), canvas.getHeight());
        int cellWidth = sizeCell / this.xCelQuantity;
        int cellHeight = sizeCell / this.yCelQuantity;
        this.cell = new Cell(canvas, cellHeight, cellWidth);
    }

    protected void draw(Canvas canvas) {
//        Отрисовка поля

        int size = Math.min(canvas.getWidth(), canvas.getHeight());
        int yCenter = (canvas.getHeight() - size) / 2;
        int xCenter = (canvas.getWidth() - size) / 2;
        int cellWidth = size / this.xCelQuantity;
        int cellHeight = size / this.yCelQuantity;

        for (int row = 0; row < xCelQuantity; row++) {
            for (int col = 0; col < yCelQuantity; col++) {
                this.cell.draw(canvas, row, col);
            }
        }
    }
}
