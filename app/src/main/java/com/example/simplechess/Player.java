package com.example.simplechess;

// Импорт констанкты

import static com.example.simplechess.Constants.yellowPaint;

import android.content.Context;
import android.graphics.Canvas;

import com.example.simplechess.dataBase.FirebaseGameManager;
import com.example.simplechess.dataBase.FirebaseWriter;
import com.example.simplechess.figures.*;
import com.example.simplechess.field.Cell;
import com.example.simplechess.player.FigureCollection;

import java.util.ArrayList;

public class Player {
    protected Position selectedPosition = null;
    private ArrayList<Position> canMoveList = new ArrayList<>();
    private FigureCollection figureCollection = new FigureCollection();
    private Cell cell;
    private Context context;
    private FirebaseWriter firebaseWriter = new FirebaseWriter();
    private boolean isWhite;


    public Player(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
        this.context = context;
        this.isWhite = isWhite;

//        firebaseWriter.startGame();
        fillTheField();
        new FirebaseGameManager(figureCollection);
    }

    protected void draw(Canvas canvas, Field field) {
        // Проходимся по мапе, берем как элемент позиции так и фигуру
        figureCollection.getFigureMap().forEach((position, figure) -> {
            figure.draw(
                    canvas,
                    figure.getXCoordinate(field.getLeftTop().getX(), position.getCol()),
                    figure.getYCoordinate(field.getLeftTop().getY(), position.getRow())
            );
        });


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
        int clickedPositionСol = (x - field.getLeftTop().getX()) / field.getCell().getWidth();
        int clickedPositionRow = (y - field.getLeftTop().getY()) / field.getCell().getHeight();
        Position clickedPosition = new Position(clickedPositionRow, clickedPositionСol);

        if (selectedPosition == null) {
            if (figureCollection.hasFigure(clickedPosition)) {
                selectFigure(clickedPosition, game);
            }
        } else {
            if (canMoveList.contains(clickedPosition)) {
                moveFigure(clickedPosition);
            } else {
                returnFigure();
            }
        }
    }

    public void selectFigure(Position position, Game game) {
        selectedPosition = position;
        firebaseWriter.writeDataFrom(position);
        canMoveList.addAll(selectedFigure().getAvailableMoves(game, selectedPosition));
    }

    public void moveFigure(Position position) {
        firebaseWriter.writeDataTo(position);
        selectedPosition = null;
        canMoveList.clear();
    }

    public void returnFigure() {
        firebaseWriter.writeDataTo(selectedPosition);
        canMoveList.clear();
        selectedPosition = null;
    }

    public void fillTheField() {
        int row;
        row = isWhite ? 1 : 6;
        for (int i = 0; i < 8; i++) {
            figureCollection.addFigure(new Position(row, i), new Pawn(context, isWhite, cell.getHeight(), cell.getWidth()));
        }
        row = isWhite ? 0 : 7;
        for (int i = 0; i < 2; i++) {
            figureCollection.addFigure(new Position(row, i * 7), new Rook(context, isWhite, cell.getHeight(), cell.getWidth()));
            figureCollection.addFigure(new Position(row, i * 5 + 1), new Knight(context, isWhite, cell.getHeight(), cell.getWidth()));
            figureCollection.addFigure(new Position(row, i * 3 + 2), new Bishop(context, isWhite, cell.getHeight(), cell.getWidth()));
        }
        figureCollection.addFigure(new Position(row, 3), new Queen(context, isWhite, cell.getHeight(), cell.getWidth()));
        figureCollection.addFigure(new Position(row, 4), new King(context, isWhite, cell.getHeight(), cell.getWidth()));
    }

    public boolean hasFigure(Position position) {
        return figureCollection.hasFigure(position);
    }

    public Figure getFigure(Position position) {
        return figureCollection.getFigure(position);
    }

    public Figure selectedFigure() {
        return figureCollection.getFigure(selectedPosition);
    }
}
