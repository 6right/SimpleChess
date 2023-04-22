package com.example.simplechess.Figures;

import com.example.simplechess.Game;
import com.example.simplechess.Player;

public class Actions {

    public static String getFigureType(Figure figure) {
        return figure.getClass().getSimpleName();
    }

//    public static boolean ruleToMove(Figure selectedFigure) {
//        if (selectedFigure instanceof Pawn) {
//            return canMove(selectedFigure);
//        } else if (selectedFigure instanceof Rook) {
//            return rookMove(selectedFigure);
//        } else if (selectedFigure instanceof Knight) {
//            return knightMove(selectedFigure);
//        } else if (selectedFigure instanceof Bishop) {
//            return bishopMove(selectedFigure);
//        } else if (selectedFigure instanceof Queen) {
//            return queenMove(selectedFigure);
//        } else if (selectedFigure instanceof King) {
//            return kingMove(selectedFigure);
//        } else {
//            return false;
//        }
//    }
}
