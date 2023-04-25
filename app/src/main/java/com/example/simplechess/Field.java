package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.graphics.Canvas;

import com.example.simplechess.field.*;
import com.example.simplechess.figures.Position;

// Класс, хранящий информацию о поле
public class Field {

    // Количество ячеек по горизонтали и вертикали
    private CellCounts cellCounts;
    // Информация о ячейке
    private Cell cell;
    // Левый верхний угол поля
    private Point leftTop;
    // Правый нижний угол поля
    private Point rightBottom;

    // Конструктор, принимающий количество ячеек по горизонтали и вертикали и размер экрана
    public Field(CellCounts cellCounts, ScreenSize screenSize) {
        // Set size of field
        this.cellCounts = cellCounts;

        // Set position of field
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();
        int size = Math.min(screenWidth, screenHeight);
        leftTop = new Point((screenWidth - size) / 2, (screenHeight - size) / 2);
        rightBottom = new Point((screenWidth + size) / 2, (screenHeight + size) / 2);

        // Set size of cell
        int sizeCell = Math.min(screenSize.getWidth(), screenSize.getHeight());
        int cellWidth = sizeCell / cellCounts.getRowQuantity();
        int cellHeight = sizeCell / cellCounts.getColQuantity();
        cell = new Cell(cellWidth, cellHeight);
    }

    // Использую, чтобы получить информацию о ячейке для отрисовки фигур
    public Cell getCell() {
        return cell;
    }

    public CellCounts getCellCounts() { return cellCounts; }

    public int getRowQuantity() { return cellCounts.getRowQuantity(); }
    public int getColQuantity() { return cellCounts.getColQuantity(); }

    // Проверка, что позиция находится внутри поля
    public boolean isInside(Position position) {
        return position.getCol() >= 0 && position.getCol() < cellCounts.getRowQuantity() &&
                position.getRow() >= 0 && position.getRow() < cellCounts.getColQuantity();
    }

    public Point getLeftTop() { return leftTop; }

    public Point getRightBottom() { return rightBottom; }

    // Отрисовка поля
    public void draw(Canvas canvas) {
        // Отрисовка поля
        for (int row = 0; row < cellCounts.getRowQuantity(); row++) {
            for (int col = 0; col < cellCounts.getColQuantity(); col++) {
                cell.draw(
                        canvas,
                        leftTop.getX() + col * cell.getWidth(),
                        leftTop.getY() + row * cell.getHeight(),
                        (row + col) % 2 == 0 ? orangePaint : whitePaint
                );
            }
        }
    }
}
