package com.example.simplechess.cells;

import static com.example.simplechess.Constants.yellowPaint;

import android.graphics.Canvas;

import com.example.simplechess.Figures.Position;
import com.example.simplechess.cells.Cell;


// Конструктор, принимающий количество ячеек по горизонтали и вертикали и размер экрана
public class HelpCell {

    Position position;
    Cell cell;

    public HelpCell(Position position, Cell cell) {
        this.cell = cell;
        this.position = position;
    }
    // Отрисовка поля
    public void draw(Canvas canvas) {
        int x = position.getX() * cell.getWidth() + cell.getXPositionCenter();
        int y = position.getY() * cell.getHeight() + cell.getYPositionCenter();
        canvas.drawRect(x, y, x + cell.getWidth(), y + cell.getHeight(), yellowPaint);
    }

}
