package com.example.simplechess.Figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Field;
import com.example.simplechess.R;
import com.example.simplechess.Player;
import com.example.simplechess.Game;

import java.util.ArrayList;

// Класс фигуры пешки
public class Pawn extends Figure {
    private boolean hasMoved = false;
    public Pawn(Context context, Position position, boolean isWhite, int height, int width){
        super(position,isWhite, height, width);

        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_plt45 : R.drawable.chess_pdt45
        );
    }

    @Override
    public void move(Position position){
        super.move(position);
        if (!hasMoved) {
            hasMoved = true;
        }
    }

    @Override
    public boolean canMove(Position selectedFigure) {
            int dx = Math.abs(position.getRow() - selectedFigure.getRow());
            int dy = position.getCol() - selectedFigure.getCol();

        if (dx == 0 && dy == (isWhite ? -2 : 2) && !hasMoved) {
            hasMoved = true;
            return true; // пешка может двигаться на две клетки вперед только на первом ходу
        } else if ((dy == 1 && !isWhite) || (dy == -1 && isWhite)) {
            hasMoved = true;
                return dx == 0; // пешка может двигаться на одну клетку вперед
        }
        return false; // пешка не может двигаться на данную позицию
    }

    @Override
    public ArrayList<Position> getAvailableMoves(Game game){
        ArrayList<Position> availableMoves = new ArrayList<>();
        // Рассчитываем смещение по оси Y
        int dy = (isWhite ? 1 : -1);

        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);
        // Если впереди нет фигур, то пешка может двигаться одну клетку
        // Используем figureMap из класса Player
        if (thisPlayer.getFigureMap().get(new Position(position.getRow(), position.getCol() + dy)) == null
                && enemyPlayer.getFigureMap().get(new Position(position.getRow(), position.getCol() + dy)) == null)
        {
            availableMoves.add(new Position(position.getRow(), position.getCol() + dy));
        }

        if (!hasMoved
                && thisPlayer.getFigureMap().get(new Position(position.getRow(), position.getCol() + 2 * dy)) == null
                && enemyPlayer.getFigureMap().get(new Position(position.getRow(), position.getCol() + 2 * dy)) == null)
        {
            availableMoves.add(new Position(position.getRow(), position.getCol() + 2 * dy));
        }

        // Если по диагонали есть фигура противника, то пешка может съесть её
        if (enemyPlayer.getFigureMap().get(new Position(position.getRow() + 1, position.getCol() + dy)) != null)
        {
            availableMoves.add(new Position(position.getRow() + 1, position.getCol() + dy));
        }

        if (enemyPlayer.getFigureMap().get(new Position(position.getRow() - 1, position.getCol() + dy)) != null)
        {
            availableMoves.add(new Position(position.getRow() - 1, position.getCol() + dy));
        }

        return availableMoves;
    }
}
