package com.example.simplechess;

public class Rook extends Figure {
    public Rook(Position position) {
        this.position = position;
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
