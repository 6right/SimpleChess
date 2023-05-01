package com.example.simplechess.dataBase;

import com.example.simplechess.figures.Position;

public class FirebaseFigure {

    private boolean isWhite;
    private int id;
    private Position position;

    public FirebaseFigure(int id, Position position, boolean isWhite) {
        this.id = id;
        this.position = position;
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }
}
