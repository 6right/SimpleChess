package com.example.simplechess;

public class Knight extends Figure {
    public Knight(Position position) {
        this.position = position;
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
