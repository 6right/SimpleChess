package com.example.simplechess;

// Импорт констанкты
import static android.content.ContentValues.TAG;
import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.simplechess.dataBase.FirebaseGameManager;
import com.example.simplechess.figures.*;
import com.example.simplechess.field.Cell;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class Player {
    protected Figure selectedFigure = null;
    protected boolean isWhite;
    protected boolean move = false;
    Cell cell;
    private FirebaseGameManager firebaseGameManager = new FirebaseGameManager();
    private ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
    private ArrayList <Position> canMoveList = new ArrayList<>();

    Context context;

    private int id = 0;

    public Player(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
        // Для расстановки фигур по y
        this.context = context;
        this.isWhite = isWhite;
        int row;
        row = isWhite ? 1 : 6;
        for (int i = 0; i < 8; i++) {
            figureMap.put(
                    new Position(row, i),
                    new Pawn(context, id, new Position(row, i), isWhite, cell.getHeight(), cell.getWidth())
            );
            id++;
        }

        row = isWhite ? 0 : 7;
        for (int i = 0; i < 2; i++) {
            figureMap.put(
                    new Position(row, i * 7),
                    new Rook(context, id, new Position(row, i * 7), isWhite, cell.getHeight(), cell.getWidth())
            );
            id++;

            figureMap.put(
                    new Position(row, i * 5 + 1),
                    new Knight(context, id, new Position(row, i * 5 + 1), isWhite, cell.getHeight(), cell.getWidth())
            );
            id++;

            figureMap.put(
                    new Position(row, i * 3 + 2),
                    new Bishop(context, id, new Position(row, i * 3 + 2), isWhite, cell.getHeight(), cell.getWidth())
            );
            id++;
        }

        figureMap.put(
                new Position(row, 3),
                new Queen(context, id, new Position(row, 3), isWhite, cell.getHeight(), cell.getWidth())
        );
        id++;

        figureMap.put(
                new Position(row, 4),
                new King(context, id, new Position(row, 4), isWhite, cell.getHeight(), cell.getWidth())
        );
        id++;
    }

    public Figure getFigure(Position position){
        return figureMap.get(position);
    }

    public boolean hasFigure(Position position){
        return figureMap.containsKey(position);
    }

    protected void draw(Canvas canvas, Field field) {
        // Вызываем метод readData
        if(move) {
            readData();
        }

        for (Figure figure : figureMap.values()) {
            figure.draw(
                    canvas,
                    figure.getXCoordinate(field.getLeftTop().getX()),
                    figure.getYCoordinate(field.getLeftTop().getY())
            );
        }
        for (Position position : canMoveList){
            cell.draw(
                    canvas,
                    cell.getXCoordinate(field.getLeftTop().getX(), position.getCol()),
                    cell.getYCoordinate(field.getLeftTop().getY(), position.getRow()),
                    yellowPaint
            );
        }
    }

    // Если переместил фигуру, то возвращаем true
    public boolean handleClick(Game game, int x, int y) {
        Field field = game.getField();
        int clickedPositionRow = (x - field.getLeftTop().getX()) / field.getCell().getWidth();
        int clickedPositionCol = (y - field.getLeftTop().getY()) / field.getCell().getHeight();
        Position clickedPosition = new Position(clickedPositionCol, clickedPositionRow);
        if (selectedFigure == null) {
            // Если на клетке есть фигура, то выбираем её
            if (hasFigure(clickedPosition)) {
                selectFigure(clickedPosition, game);
            }
            return false;
        } else {
            if (positionFree(clickedPosition, game) && canMoveList.contains(clickedPosition)) {
                moveFigure(clickedPosition);
                readData();
                return true;
            } else {
                returnFigure();
                return false;
            }
        }
    }

    public void selectFigure(Position position, Game game) {
        selectedFigure = getFigure(position);
        canMoveList.addAll(selectedFigure.getAvailableMoves(game));
        figureMap.remove(position);
        readData();
    }

    public void moveFigure(Position position) {
        selectedFigure.move(position);
        figureMap.put(position, selectedFigure);
        writeToDatabase();
        canMoveList.clear();
        selectedFigure = null;
        move = true;
    }

    public void returnFigure() {
        figureMap.put(selectedFigure.getPosition(), selectedFigure);
        canMoveList.clear();
        selectedFigure = null;
    }

    // Проверка на наличие другой фигуры на клетке (схожей по цвету)
    public boolean positionFree(Position position, Game game) {
        if (hasFigure(position)
                || game.getPlayer(!isWhite).hasFigure(position)) {
            return false;
        }
        return true;
    }

    public void writeToDatabase(){
        for (Figure figure : figureMap.values()) {
            firebaseGameManager.writeData(figure, figure.getPosition(), isWhite);
        }
    }
    // Метод для чтения данных из базы данных
    // Берем данные из базы данных и записываем в figureMap
    public void readData(){
        for (Integer id : firebaseGameManager.readData().keySet()) {
            for (Figure figure : figureMap.values()) {
                if (figure.getId() == id) {
                    figureMap.put(firebaseGameManager.readData().get(id), figure);
                    figure.move(firebaseGameManager.readData().get(id));
                    figureMap.remove(figure.getPosition());
                }
            }
        }
//        figureMap = firebaseGameManager.readData(isWhite, id);
    }
}
