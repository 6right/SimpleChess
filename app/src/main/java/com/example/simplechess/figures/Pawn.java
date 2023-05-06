package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.R;
import com.example.simplechess.Player;
import com.example.simplechess.Game;

import java.util.ArrayList;

// Класс фигуры пешки
public class Pawn extends Figure {
    private boolean hasMoved = false;

    public Pawn(Context context, Position position, boolean isWhite, int height, int width) {
        super(position, isWhite, height, width);

        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_plt45 : R.drawable.chess_pdt45
        );
    }

    @Override
    public void move(Position position) {
        super.move(position);
    }
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public ArrayList<Position> getAvailableMoves(Game game) {
        ArrayList<Position> availableMoves = new ArrayList<>();
        // Рассчитываем смещение по оси Y
        int dy = (isWhite ? 1 : -1);

        // Если пешка находится на границе поля, то она не может двигаться
        // Границу узнаем через класс Field и метод isInside
        if (!game.getField().isInside(position.add(0, dy))) {
            return availableMoves;
        }

        Player thisPlayer = game.getPlayer(isWhite);
        Player enemyPlayer = game.getPlayer(!isWhite);

        // Если впереди нет фигур, то пешка может двигаться
        // на одну клетку вперед

        if (!thisPlayer.hasFigure(position.add(0, dy))
                && !enemyPlayer.hasFigure(position.add(0, dy)))
        {
            availableMoves.add(position.add(0, dy));
        }

        // Если по диагонали есть фигура противника, то пешка может съесть её
        if (enemyPlayer.hasFigure(position.add(1, dy)))
        {
            availableMoves.add(position.add(1, dy));
        }
        if (enemyPlayer.hasFigure(position.add(-1, dy)))
        {
            availableMoves.add(position.add(-1, dy));
        }


        // Если пешка уже двигалась, то она не может двигаться на две клетки
        if (hasMoved) {
            return availableMoves;
        }

        // Если через одну клетку впереди есть граница поля, то пешка не может двигаться
        if (!game.getField().isInside(position.add(0, 2 * dy))) {
            return availableMoves;
        }

        // Если впереди нет фигур, то пешка может двигаться на две клетки
        if (!thisPlayer.hasFigure(position.add(0, 2 * dy))
                && !enemyPlayer.hasFigure(position.add(0, 2 * dy)))
        {
            availableMoves.add(position.add(0, 2 * dy));
        }
        return availableMoves;
    }
}
