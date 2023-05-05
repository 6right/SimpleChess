package com.example.simplechess.dataBase;


import androidx.annotation.NonNull;

import com.example.simplechess.figures.Figure;
import com.example.simplechess.figures.Pawn;
import com.example.simplechess.figures.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ConcurrentHashMap;

public class FirebaseGameManager {

    ConcurrentHashMap<Integer, Figure> figureMap;
    DatabaseReference mDatabaseRef;
    public FirebaseGameManager(ConcurrentHashMap<Integer, Figure> figureMap){
        this.figureMap = figureMap;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Position value = snapshot.getValue(Position.class);
                setPosition(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Figure getFigure(Integer position) {
        return figureMap.get(position);
    }
    private void setPosition(Position value) {
        getFigure(0).move(value);
    }

//    public void fillTheField() {
//        int row;
//        row = isWhite ? 1 : 6;
//        for (int i = 0; i < 8; i++) {
//            figureMap.put(
//                    new Position(row, i),
//                    new Pawn(context, id, new Position(row, i), isWhite, cell.getHeight(), cell.getWidth())
//            );
//            id++;
//        }
//        row = isWhite ? 0 : 7;
//        for (int i = 0; i < 2; i++) {
//            figureMap.put(
//                    new Position(row, i * 7),
//                    new Rook(context, id, new Position(row, i * 7), isWhite, cell.getHeight(), cell.getWidth())
//            );
//            id++;
//
//            figureMap.put(
//                    new Position(row, i * 5 + 1),
//                    new Knight(context, id, new Position(row, i * 5 + 1), isWhite, cell.getHeight(), cell.getWidth())
//            );
//            id++;
//
//            figureMap.put(
//                    new Position(row, i * 3 + 2),
//                    new Bishop(context, id, new Position(row, i * 3 + 2), isWhite, cell.getHeight(), cell.getWidth())
//            );
//            id++;
//        }
//
//        figureMap.put(
//                new Position(row, 3),
//                new Queen(context, id, new Position(row, 3), isWhite, cell.getHeight(), cell.getWidth())
//        );
//        id++;
//
//        figureMap.put(
//                new Position(row, 4),
//                new King(context, id, new Position(row, 4), isWhite, cell.getHeight(), cell.getWidth())
//        );
//        id++;
//    }
}

