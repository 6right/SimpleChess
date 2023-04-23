package com.example.simplechess;

import static com.example.simplechess.Constants.*;

import android.graphics.Canvas;

import com.example.simplechess.field.*;

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
        int cellWidth = sizeCell / cellCounts.getXQuantity();
        int cellHeight = sizeCell / cellCounts.getYQuantity();
        cell = new Cell(cellWidth, cellHeight);
    }

    // Использую, чтобы получить информацию о ячейке для отрисовки фигур
    public Cell getCell() {
        return cell;
    }
    public Point getLeftTop() { return leftTop; }

    public Point getRightBottom() { return rightBottom; }

    // Отрисовка поля
    public void draw(Canvas canvas) {
        // Отрисовка поля
        for (int row = 0; row < cellCounts.getXQuantity(); row++) {
            for (int col = 0; col < cellCounts.getYQuantity(); col++) {
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
