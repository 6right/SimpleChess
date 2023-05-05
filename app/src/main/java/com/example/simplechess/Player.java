package com.example.simplechess;

// Импорт констанкты

import android.content.Context;
import android.graphics.Canvas;

import com.example.simplechess.dataBase.FirebaseGameManager;
import com.example.simplechess.dataBase.FirebaseWriter;
import com.example.simplechess.figures.*;
import com.example.simplechess.field.Cell;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class Player {
    protected Figure selectedFigure = null;
    protected Position selectedPosition = null;
    protected boolean move = false;
//    private FirebaseFigureList firebaseFigureList;
    private ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
    private ArrayList<Position> canMoveList = new ArrayList<>();
    private Cell cell;
    private Context context;
    private FirebaseWriter firebaseWriter = new FirebaseWriter();
    public Player(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
        this.context = context;
        figureMap.put(new Position(0,0), new Pawn(context,new Position(0,0), true, cell.getHeight(), cell.getWidth()));
//        firebaseWriter.writeDataFrom(new Position(0,0), new Position(0,0));
        new FirebaseGameManager(figureMap);


//        this.figureMap = new FirebaseFigureList(context, isWhite, cell).getFigureMap();
//        this.firebaseFigureList = new FirebaseFigureList(context, isWhite, cell);
    }

    public Figure getFigure(Position position) {
        return figureMap.get(position);
    }

    public boolean hasFigure(Position position) {
        return figureMap.containsKey(position);
    }

    protected void draw(Canvas canvas, Field field) {
        for (Figure figure : figureMap.values()) {
            figure.draw(
                    canvas,
                    figure.getXCoordinate(field.getLeftTop().getX()),
                    figure.getYCoordinate(field.getLeftTop().getY())
            );
        }
//        }
//        for (Position position : canMoveList) {
//            cell.draw(
//                    canvas,
//                    cell.getXCoordinate(field.getLeftTop().getX(), position.getCol()),
//                    cell.getYCoordinate(field.getLeftTop().getY(), position.getRow()),
//                    yellowPaint
//            );
//        }

    }

    // Если переместил фигуру, то возвращаем true
    public void handleClick(Game game, int x, int y) {
        Field field = game.getField();
        int clickedPositionRow = (x - field.getLeftTop().getX()) / field.getCell().getWidth();
        int clickedPositionCol = (y - field.getLeftTop().getY()) / field.getCell().getHeight();
        Position clickedPosition = new Position(clickedPositionCol, clickedPositionRow);


        if (selectedPosition == null) {
            selectedPosition = clickedPosition;
        } else {
            firebaseWriter.writeDataFrom(selectedPosition, clickedPosition);
        }
            // Если на клетке есть фигура, то выбираем её
//            if (hasFigure(clickedPosition)) {
//                selectFigure(clickedPosition, game);
//            }
//            return false;
//        } else {
//            if (canMoveList.contains(clickedPosition)) {
////                moveFigure(clickedPosition);
//                return true;
//            } else {
//                returnFigure();
//                return false;
//            }
//        }
    }

    public void selectFigure(Position position, Game game) {
        selectedFigure = getFigure(position);
        canMoveList.addAll(selectedFigure.getAvailableMoves(game));
        figureMap.remove(position);
    }

    public void moveFigure(Position position) {
//        selectedFigure.move(position);
//        setNewPosition();
        canMoveList.clear();
        selectedFigure = null;
        move = true;

    }

    public void returnFigure() {
//        figureMap.put(selectedFigure.getPosition(), selectedFigure);
        canMoveList.clear();
        selectedFigure = null;
    }

//    public void setNewPosition(){
//        for (Figure figure : figureMap.values()) {
//            for (Figure figure1 : firebaseFigureList.getFigureMap().values()) {
//                if (figure.equals(figure1)) {
//                    if(!figure.getPosition().equals(figure1.getPosition())) {
//                        figureMap.remove(figure.getPosition());
//                        figureMap.put(figure1.getPosition(), figure);
//                    }
//                }
//            }
//        }
//    }
}
