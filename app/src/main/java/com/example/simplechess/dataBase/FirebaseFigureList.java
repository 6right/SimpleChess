//package com.example.simplechess.dataBase;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.example.simplechess.field.Cell;
//import com.example.simplechess.figures.Bishop;
//import com.example.simplechess.figures.Figure;
//import com.example.simplechess.figures.King;
//import com.example.simplechess.figures.Knight;
//import com.example.simplechess.figures.Pawn;
//import com.example.simplechess.figures.Position;
//import com.example.simplechess.figures.Queen;
//import com.example.simplechess.figures.Rook;
//
//import java.util.ArrayList;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class FirebaseFigureList {
//    private Cell cell;
//    private boolean isWhite;
//    private int id = 0;
//    Context context;
//    FirebaseGameManager firebaseGameManager = new FirebaseGameManager();
//
//    private ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();
//    private ArrayList<FirebaseFigure> databaseMap = new ArrayList<>();
//
//    public FirebaseFigureList(Context context, boolean isWhite, Cell cell) {
//        this.cell = cell;
//        this.context = context;
//        this.isWhite = isWhite;
//
//        firebaseGameManager.clearDatabase();
//        fillTheField();
//        fillTheDatabase();
//        fillTheDatabaseFigures();
//        firebaseGameManager.readData(isWhite);
//    }
//
//    private void fillTheDatabase() {
//        for (Figure figure : figureMap.values()) {
//            firebaseGameManager.writeData(figure, figure.getPosition(), isWhite);
//        }
//    }
//
//    public ConcurrentHashMap<Position, Figure> getFigureMap() {
//        databaseMap = firebaseGameManager.getDatabasePositions();
//        for (FirebaseFigure firebaseFigure : databaseMap) {
//            for (Figure figure : figureMap.values()) {
//                if (firebaseFigure.equals(figure)) {
//                    figure.move(firebaseFigure.getPosition());
//                    figureMap.remove(figure.getPosition());
//                    figureMap.put(firebaseFigure.getPosition(), figure);
//                }
//            }
//        }
//        return figureMap;
//    }
//
//    public void fillTheDatabaseFigures(){
//        for (Figure figure : figureMap.values()) {
//            firebaseGameManager.writeData(figure, figure.getPosition(), isWhite);
//        }
//    }
//}
