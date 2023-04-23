package com.example.simplechess;

import static com.example.simplechess.Constants.orangePaint;
import static com.example.simplechess.Constants.yellowPaint;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.simplechess.Figures.Position;


// Конструктор, принимающий количество ячеек по горизонтали и вертикали и размер экрана
public class HelpCells{

    Position position;
    Cell cell;

    public HelpCells(Position position, Cell cell) {
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
