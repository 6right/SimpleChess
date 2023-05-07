package com.example.simplechess.dataBase;


import androidx.annotation.NonNull;

import com.example.simplechess.Game;
import com.example.simplechess.figures.Position;
import com.example.simplechess.player.FigureCollection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseGameManager {
    DatabaseReference mDatabaseRef;
    Position previousToPosition;
    Game game;

    public FirebaseGameManager(Game game) {
        this.game = game;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        // Этот объект нужен, чтобы мы меняли координаты фигуры, только после того, как поменяется данные to В базе.
        // Предотвращает перестановку фигуры на To, при первом клике на нее (был баг)
        previousToPosition = new Position(5, 5);
        mDatabaseRef.child("gameID").child("ID").child("moves").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FigureCollection whiteCollection = game.getPlayer(true).getFigureCollection();
                FigureCollection blackCollection = game.getPlayer(false).getFigureCollection();
                Position from = snapshot.child("from").getValue(Position.class);
                Position to = snapshot.child("to").getValue(Position.class);
                if (!to.equals(previousToPosition)) {
                    // Если на позиции to есть фигура, то мы ее удаляем, иначе перемещаем фигуру
                    if (whiteCollection.hasFigure(to)) {
                        whiteCollection.removeFigure(to);
                    } else {
                        setPosition(whiteCollection, from, to);
                    }
                    if (blackCollection.hasFigure(to)) {
                        blackCollection.removeFigure(to);
                    } else {
                        setPosition(blackCollection, from, to);
                    }
                    previousToPosition = to;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setPosition(FigureCollection figureCollection, Position from, Position to) {
        game.setMove();
        figureCollection.moveFigure(from, to);
    }
}

