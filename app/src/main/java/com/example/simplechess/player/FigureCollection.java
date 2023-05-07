package com.example.simplechess.player;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class FigureCollection {
    // Объект состоящий из мапы с позициями и фигурами
    private ConcurrentHashMap<Position, Figure> figureMap = new ConcurrentHashMap<>();

    // Метод для получения массива фигур ArrayList
    public ArrayList<Figure> getFigureList() {
        return new ArrayList<Figure>(figureMap.values());
    }

    public ArrayList<Position> getPositionList() {
        return new ArrayList<Position>(figureMap.keySet());
    }

    public ConcurrentHashMap<Position, Figure> getFigureMap() {
        return figureMap;
    }

    public Figure getFigure(Position position) {
        return figureMap.get(position);
    }

    public void addFigure(Position position, Figure figure) {
        figureMap.put(position, figure);
    }

    public void removeFigure(Position position) {
        figureMap.remove(position);
    }

    public boolean moveFigure(Position from, Position to) {
        Figure figure = figureMap.get(from);
        if (figure == null) {
            return false;
        }

        figureMap.remove(from);
        figureMap.put(to, figure);
        return true;
    }

    public boolean hasFigure(Position position) {
        return figureMap.containsKey(position);
    }
}
