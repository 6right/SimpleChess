package com.example.simplechess;

public class Queen extends Figure {
    public Queen(Position position) {
        this.position = position;
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
