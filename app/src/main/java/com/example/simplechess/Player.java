package com.example.simplechess;

// Импорт констанкты
import static com.example.simplechess.Constants.*;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.simplechess.dataBase.FirebaseFigureList;
import com.example.simplechess.dataBase.FirebaseGameManager;
import com.example.simplechess.figures.*;
import com.example.simplechess.field.Cell;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class Player {
    protected Figure selectedFigure = null;
    protected boolean isWhite;
    protected boolean move = false;
    private FirebaseGameManager firebaseGameManager = new FirebaseGameManager();
    private ConcurrentHashMap<Position, Figure> figureMap;
    private ArrayList <Position> canMoveList = new ArrayList<>();
    private Cell cell;

    public Player(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
        this.figureMap = new FirebaseFigureList(context, isWhite, cell).getFigureMap();
        }

    public Figure getFigure(Position position){
        return figureMap.get(position);
    }

    public boolean hasFigure(Position position){
        return figureMap.containsKey(position);
    }

    protected void draw(Canvas canvas, Field field) {
        // Вызываем метод readData
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
            if (canMoveList.contains(clickedPosition)) {
                moveFigure(clickedPosition);
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
    }

    public void moveFigure(Position position) {
        selectedFigure.move(position);
        figureMap.put(position, selectedFigure);
        Log.d("TAG", "col: " + selectedFigure.getPosition().getCol());
        Log.d("TAG", "row: " + selectedFigure.getPosition().getRow());
        Log.d("TAG", "id: " + selectedFigure.getId());
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


    public void writeToDatabase(){
        for (Figure figure : figureMap.values()) {
            firebaseGameManager.writeData(figure, figure.getPosition(), isWhite);
        }
    }
    // Метод для чтения данных из базы данных
    // Берем данные из базы данных и записываем в figureMap
//    public void readData(){
//        for (Integer id : firebaseGameManager.readData().keySet()) {
//            for (Figure figure : figureMap.values()) {
//                if (figure.getId() == id && figure.isWhite()) {
//                    figureMap.put(firebaseGameManager.readData().get(id), figure);
//                    figure.move(firebaseGameManager.readData().get(id));
//                    figureMap.remove(figure.getPosition());
//                    return;
//                }
//            }
//        }
////        figureMap = firebaseGameManager.readData(isWhite, id);
//    }
}
