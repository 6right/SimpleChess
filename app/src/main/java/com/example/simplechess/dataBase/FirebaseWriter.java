package com.example.simplechess.dataBase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.simplechess.figures.Position;
import com.example.simplechess.player.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseWriter {
    DatabaseReference mDatabaseRef;
    Users user;
    String Uid;

    public FirebaseWriter() {
    }

    public FirebaseWriter(Users user) {
        this.user = user;
        Uid = user.getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userID = dataSnapshot.child("gameID").child("ID").child("user_1").getValue(String.class);
                if (userID != null) {
                    mDatabaseRef.child("gameID").child("ID").child("user_1").setValue(Uid);
                } else {
                    mDatabaseRef.child("gameID").child("ID").child("user_2").setValue(Uid);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void writeDataFrom(Position from) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("gameID").child("ID").child("moves").child("from").setValue(from);
    }

    public void writeDataTo(Position clickedPosition) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("gameID").child("ID").child("moves").child("to").setValue(clickedPosition);
    }

    public void startGame() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.child("gameID").child("ID").child("user_1").setValue("UID_1");
        mDatabaseRef.child("gameID").child("ID").child("user_2").setValue("UID_2");
        mDatabaseRef.child("gameID").child("ID").child("moves").child("from").setValue(new Position(9, 9));
        mDatabaseRef.child("gameID").child("ID").child("moves").child("to").setValue(new Position(9, 9));
        mDatabaseRef.child("gameID").child("ID").child("whoseMove").setValue("true");
    }
}
