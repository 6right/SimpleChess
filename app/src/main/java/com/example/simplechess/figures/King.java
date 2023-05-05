//package com.example.simplechess.figures;
//
//import android.content.Context;
//import android.graphics.BitmapFactory;
//
//import com.example.simplechess.R;
//
//// Класс фигуры короля
//public class King extends Figure {
//
//    private boolean hasMoved = false;
//    public King(Context context, int id, Position position, boolean isWhite, int height, int width){
//        super(id, position,isWhite, height, width);
//
//        bitmap = BitmapFactory.decodeResource(
//                context.getResources(),
//                isWhite ? R.drawable.chess_klt45 : R.drawable.chess_kdt45
//        );
//    }
//
//    @Override
//    public boolean canMove(Position selectedFigure){
//            int dx = Math.abs(position.getCol() - selectedFigure.getCol());
//            int dy = Math.abs(position.getRow() - selectedFigure.getRow());
//
//            if (dx <= 1 && dy <= 1) {
//                hasMoved = true;
//                return true; // король может двигаться на одну клетку в любом направлении
//            }
//
//            // Проверяем возможность рокировки для короля
////            if (!hasMoved) {
////                int y = selectedFigure.getY();
////                if ((position.getX() == 2 && position.getY() == y) // Короткая рокировка
////                        && game.getFigureAtPosition(new Position(3, y)) == null
////                        && game.getFigureAtPosition(new Position(2, y)) == null
////                        && game.getFigureAtPosition(new Position(1, y)) instanceof Rook
////                        && !game.getFigureAtPosition(new Position(1, y)).isMoved()) {
////                    return true;
////                } else if ((position.getX() == 6 && position.getY() == y) // Длинная рокировка
////                        && game.getFigureAtPosition(new Position(5, y)) == null
////                        && game.getFigureAtPosition(new Position(6, y)) == null
////                        && game.getFigureAtPosition(new Position(7, y)) instanceof Rook
////                        && !game.getFigureAtPosition(new Position(7, y)).isMoved()) {
////                    return true;
////                }
////            }
//            return false; // король не может двигаться на данную позицию
//    }
//}
