package com.example.simplechess;

// Импорт констанкты

import static com.example.simplechess.Constants.yellowPaint;

import android.content.Context;
import android.graphics.Canvas;

import com.example.simplechess.dataBase.FirebaseGameManager;
import com.example.simplechess.dataBase.FirebaseWriter;
import com.example.simplechess.figures.*;
import com.example.simplechess.field.Cell;

import java.util.ArrayList;

public class Player {
    protected Figure selectedFigure = null;
    protected Position selectedPosition = null;
    protected boolean move = false;
    private ArrayList<Position> canMoveList = new ArrayList<>();
    private ArrayList<Figure> figureList = new ArrayList<>();
    private Cell cell;
    private Context context;
    private FirebaseWriter firebaseWriter = new FirebaseWriter();
    private boolean isWhite;


    public Player(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
        this.context = context;
        this.isWhite = isWhite;

        // Не придумал пока как нам при инициализации игры, убирать данные из дб
        // Создал метод removeData, но он пока вызывает ошибку при 1м ходе
        // Если при инициализации указывать позицию внутри поля, то при 1м ходе, фигурка будет вставать на эту позицию
        firebaseWriter.writeDataFromTo(new Position(9, 9), new Position(9, 9));
        fillTheField();
        new FirebaseGameManager(figureList);
    }

    public Figure getFigure(Position position) {
        Figure figure = null;
        for (Figure figureInList : figureList) {
            if (figureInList.getPosition().equals(position)) {
                figure = figureInList;
                break;
            }
        }
        return figure;
    }

    protected void draw(Canvas canvas, Field field) {
        for (Figure figure : figureList) {
            figure.draw(
                    canvas,
                    figure.getXCoordinate(field.getLeftTop().getX()),
                    figure.getYCoordinate(field.getLeftTop().getY())
            );
        }
        for (Position position : canMoveList) {
            cell.draw(
                    canvas,
                    cell.getXCoordinate(field.getLeftTop().getX(), position.getCol()),
                    cell.getYCoordinate(field.getLeftTop().getY(), position.getRow()),
                    yellowPaint
            );
        }

    }

    public void handleClick(Game game, int x, int y) {
        Field field = game.getField();
        int clickedPositionRow = (x - field.getLeftTop().getX()) / field.getCell().getWidth();
        int clickedPositionCol = (y - field.getLeftTop().getY()) / field.getCell().getHeight();
        Position clickedPosition = new Position(clickedPositionCol, clickedPositionRow);

        if (selectedFigure == null) {
            if (hasFigure(clickedPosition)) {
                selectFigure(clickedPosition, game);
                firebaseWriter.writeDataFrom(clickedPosition);
            }
        } else {
            if (canMoveList.contains(clickedPosition)) {
                moveFigure(clickedPosition);
                firebaseWriter.writeDataTo(clickedPosition);
            } else {
                //                returnFigure();
            }
        }
    }

    public boolean hasFigure(Position position) {
        for (Figure figure : figureList) {
            if (figure.getPosition().equals(position)) {
                return true;
            }
        }
        return true;
    }

    public void selectFigure(Position position, Game game) {
        selectedFigure = getFigure(position);
        canMoveList.addAll(selectedFigure.getAvailableMoves(game));

    }

    public void moveFigure(Position position) {
        firebaseWriter.writeDataFromTo(selectedFigure.getPosition(), position);
        selectedFigure = null;
        canMoveList.clear();
        selectedFigure = null;
    }

//    public void returnFigure() {
////        figureMap.put(selectedFigure.getPosition(), selectedFigure);
//        canMoveList.clear();
//        selectedFigure = null;
//    }

        public void fillTheField() {
//            figureList.add(new Pawn(context, new Position(3, 3), isWhite, cell.getHeight(), cell.getWidth()));
        int row;
        row = isWhite ? 1 : 6;
        for (int i = 0; i < 8; i++) {
            figureList.add(new Pawn(context, new Position(row, i), isWhite, cell.getHeight(), cell.getWidth()));
        }
        row = isWhite ? 0 : 7;
        for (int i = 0; i < 2; i++) {
            figureList.add(new Rook(context, new Position(row, i * 7), isWhite, cell.getHeight(), cell.getWidth()));
            figureList.add(new Knight(context, new Position(row, i * 5 + 1), isWhite, cell.getHeight(), cell.getWidth()));
            figureList.add(new Bishop(context, new Position(row, i * 3 + 2), isWhite, cell.getHeight(), cell.getWidth()));
        }
            figureList.add(new Queen(context, new Position(row, 3), isWhite, cell.getHeight(), cell.getWidth()));
            figureList.add(new King(context, new Position(row, 4), isWhite, cell.getHeight(), cell.getWidth()));
    }
}
