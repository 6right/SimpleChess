package com.example.simplechess.Figures;

import com.example.simplechess.Game;
import com.example.simplechess.Player;

public class Actions {
    private boolean isWhite;


    public static String getFigureType(Figure figure) {
        return figure.getClass().getSimpleName();
    }

    public static boolean ruleToMove(Position position, Figure selectedFigure, Game game) {
        if (selectedFigure instanceof Pawn) { // если выбрана пешка

            int dx = Math.abs(position.getX() - selectedFigure.getPosition().getX());
            int dy = position.getY() - selectedFigure.getPosition().getY();

            if ((dy == 1 && selectedFigure.isWhite) || (dy == -1 && !selectedFigure.isWhite)) {
                if (dx == 0) {
                    return true; // пешка может двигаться на одну клетку вперед
                }
            }
            return false; // пешка не может двигаться на данную позицию
        }

        if (selectedFigure instanceof Knight) { // если выбран конь
            int dx = Math.abs(position.getX() - selectedFigure.getPosition().getX());
            int dy = Math.abs(position.getY() - selectedFigure.getPosition().getY());

            if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
                return true; // конь может двигаться в форме буквы L
            }
            return false; // конь не может двигаться на данную позицию
        }

        if (selectedFigure instanceof Rook) { // если выбрана ладья
            int dx = Math.abs(position.getX() - selectedFigure.getPosition().getX());
            int dy = Math.abs(position.getY() - selectedFigure.getPosition().getY());
            if ((dx == 0 && dy > 0) || (dx > 0 && dy == 0)) {
                return true; // ладья может двигаться на данную позицию
            }
            return false; // ладья не может двигаться на данную позицию
        }

        if (selectedFigure instanceof Queen) { // если выбрана фигура Ферзь
            int dx = Math.abs(position.getX() - selectedFigure.getPosition().getX());
            int dy = Math.abs(position.getY() - selectedFigure.getPosition().getY());
            if (dx == 0 || dy == 0 || dx == dy) { // если фигура Ферзь на той же вертикали, горизонтали или диагонали, что и выбранная позиция
                return true;
            }
            return false; // фигура Ферзь не может двигаться на данную позицию
        }

        if (selectedFigure instanceof Bishop) { // если выбран слон
            int dx = Math.abs(position.getX() - selectedFigure.getPosition().getX());
            int dy = Math.abs(position.getY() - selectedFigure.getPosition().getY());
            if (dx == dy) { // слон может двигаться только по диагонали, то есть dx должен быть равен dy
                return true;
            }
            return false; // слон не может двигаться на данную позицию
        }

//        if (selectedFigure instanceof King) { // если выбран король
//
//            int dx = Math.abs(position.getX() - selectedFigure.getPosition().getX());
//            int dy = Math.abs(position.getY() - selectedFigure.getPosition().getY());
//
//            if (dx <= 1 && dy <= 1) {
//                return true; // король может двигаться на одну клетку в любом направлении
//            }
//
//            // Проверяем возможность рокировки для короля
//            if (!selectedFigure.isMoved()) {
//                int y = selectedFigure.getPosition().getY();
//                if ((position.getX() == 2 && position.getY() == y) // Короткая рокировка
//                        && game.getFigureAtPosition(new Position(3, y)) == null
//                        && game.getFigureAtPosition(new Position(2, y)) == null
//                        && game.getFigureAtPosition(new Position(1, y)) instanceof Rook
//                        && !game.getFigureAtPosition(new Position(1, y)).isMoved()) {
//                    return true;
//                } else if ((position.getX() == 6 && position.getY() == y) // Длинная рокировка
//                        && game.getFigureAtPosition(new Position(5, y)) == null
//                        && game.getFigureAtPosition(new Position(6, y)) == null
//                        && game.getFigureAtPosition(new Position(7, y)) instanceof Rook
//                        && !game.getFigureAtPosition(new Position(7, y)).isMoved()) {
//                    return true;
//                }
//            }
//            return false; // король не может двигаться на данную позицию
//        }
        return true; // для других фигур возвращаем true, т.к. они могут двигаться на несколько клеток
    }
}
