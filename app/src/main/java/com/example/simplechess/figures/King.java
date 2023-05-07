package com.example.simplechess.figures;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.simplechess.Game;
import com.example.simplechess.R;

import java.util.ArrayList;

// Класс фигуры короля
public class King extends Figure {

    private boolean hasMoved = false;

    public King(Context context, boolean isWhite, int height, int width) {
        super(isWhite, height, width);

        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                isWhite ? R.drawable.chess_klt45 : R.drawable.chess_kdt45
        );
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    @Override
    public void move() {
        hasMoved = true;
    }

    public ArrayList<Position> getAvailableMoves(Game game, Position position) {
        ArrayList<Position> availableMoves = new ArrayList<>();
        // Расчитываем возможные ходы короля
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Если король находится на границе поля, то он не может двигаться
                // Границу узнаем через класс Field и метод isInside
                if (!game.getField().isInside(position.add(dy, dx))) {
                    continue;
                }
                // Получаем фигуру на позиции
                Figure figure = game.getPlayer(isWhite).getFigure(position.add(dy, dx));
                // Если фигура на позиции есть
                if (figure != null) {
                    // Если фигура того же цвета, что и король, то король не может двигаться
                    if (figure.isWhite() == isWhite) {
                        continue;
                    }
                }
                // Если фигуры на позиции нет, то король может двигаться
                availableMoves.add(position.add(dy, dx));
            }
        }

        // Рокировка
        // Проверяем, что на пути к Ладье нет фигур
        // Проверяем, что король и ладья не двигались
        // Проверяем, что король не находится под шахом


        return availableMoves;
    }
}
