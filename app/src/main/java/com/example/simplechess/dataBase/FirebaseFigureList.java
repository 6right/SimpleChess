package com.example.simplechess.dataBase;

import android.content.Context;
import android.util.Log;

import com.example.simplechess.field.Cell;
import com.example.simplechess.figures.Bishop;
import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.King;
import com.example.simplechess.figures.Knight;
import com.example.simplechess.figures.Pawn;
import com.example.simplechess.figures.Position;
import com.example.simplechess.figures.Queen;
import com.example.simplechess.figures.Rook;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class FirebaseFigureList {
    private Cell cell;
    private boolean isWhite;
    private int id = 0;
    Context context;
    FirebaseGameManager firebaseGameManager = new FirebaseGameManager();

    private ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
    private ArrayList<FirebaseFigure> databaseMap = new ArrayList<>();

    public FirebaseFigureList(Context context, boolean isWhite, Cell cell) {
        this.cell = cell;
        this.context = context;
        this.isWhite = isWhite;

        firebaseGameManager.clearDatabase();
        fillTheField();
        fillTheDatabase();
        fillTheDatabaseFigures();
        firebaseGameManager.readData(isWhite);
    }

    private void fillTheDatabase() {
        for (Figure figure : figureMap.values()) {
            firebaseGameManager.writeData(figure, figure.getPosition(), isWhite);
        }
    }

    public ConcurrentHashMap<Position, Figure> getFigureMap() {
        databaseMap = firebaseGameManager.getDatabasePositions();
        for (FirebaseFigure firebaseFigure : databaseMap) {
            for (Figure figure : figureMap.values()) {
                if (firebaseFigure.equals(figure)) {
                    figure.move(firebaseFigure.getPosition());
                    figureMap.remove(figure.getPosition());
                    figureMap.put(firebaseFigure.getPosition(), figure);
                }
            }
        }
        return figureMap;
    }

    public void fillTheDatabaseFigures(){
        for (Figure figure : figureMap.values()) {
            firebaseGameManager.writeData(figure, figure.getPosition(), isWhite);
        }
    }

    public void fillTheField() {
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
}
