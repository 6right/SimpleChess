package com.example.simplechess.dataBase;


import androidx.annotation.NonNull;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.King;
import com.example.simplechess.figures.Pawn;
import com.example.simplechess.figures.Position;
import com.example.simplechess.figures.Rook;
import com.example.simplechess.player.FigureCollection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseGameManager {

    private FigureCollection figureCollection;
    DatabaseReference mDatabaseRef;
    Position previousToPosition;

    public FirebaseGameManager(FigureCollection figureCollection) {
        this.figureCollection = figureCollection;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        // Этот объект нужен, чтобы мы меняли координаты фигуры, только после того, как поменяется данные to В базе.
        // Предотвращает перестановку фигуры на To, при первом клике на нее (был баг)
        previousToPosition = new Position(5, 5);
        mDatabaseRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Position from = snapshot.child("from").getValue(Position.class);
                Position to = snapshot.child("to").getValue(Position.class);
                if (!to.equals(previousToPosition)) {
                    setPosition(from, to);
                    previousToPosition = to;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setPosition(Position from, Position to) {
        figureCollection.moveFigure(from, to);
    }
}

