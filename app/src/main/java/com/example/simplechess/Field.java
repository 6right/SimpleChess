package com.example.simplechess;

import android.graphics.Canvas;

// Класс, хранящий информацию о поле
public class Field {

    // Количество ячеек по горизонтали и вертикали
    private CellCounts cellCounts;
    // Информация о ячейке
    Cell cell;

    // Конструктор, принимающий количество ячеек по горизонтали и вертикали и размер экрана
    public Field(CellCounts cellCounts, ScreenSize screenSize) {
        // Set size of field
        this.cellCounts = cellCounts;

        // Set size of cell
        int sizeCell = Math.min(screenSize.getWidth(), screenSize.getHeight());
        int cellWidth = sizeCell / cellCounts.getXQuantity();
        int cellHeight = sizeCell / cellCounts.getYQuantity();
        cell = new Cell(cellWidth, cellHeight, screenSize.getWidth(), screenSize.getHeight());
    }

    // Использую, чтобы получить информацию о ячейке для отрисовки фигур
    public Cell getCell() {
        return cell;
    }

    // Отрисовка поля
    public void draw(Canvas canvas) {
        // Отрисовка поля
        for (int row = 0; row < cellCounts.getXQuantity(); row++) {
            for (int col = 0; col < cellCounts.getYQuantity(); col++) {
                cell.draw(canvas, row, col);
            }
        }
    }
}
