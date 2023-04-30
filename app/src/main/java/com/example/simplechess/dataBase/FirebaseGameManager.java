package com.example.simplechess.dataBase;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.simplechess.figures.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseGameManager {
    private EditText editText;
    private TextView textView;
    private Button button;

    private Position value;
    DatabaseReference mDatabaseRef;

    public interface OnDataReceivedListener {
        void onDataReceived(Position position);
    }

    public void writeData(Position position) {

                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                mDatabaseRef.child("games").child("players").setValue(position);
                ;
            }

        // Добавляем слушатель событий ValueEventListener к mDatabaseRef
        public void registerEventListener(OnDataReceivedListener listener) {
            mDatabaseRef = FirebaseDatabase.getInstance().getReference();
            mDatabaseRef.child("games").child("players").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Position position = dataSnapshot.getValue(Position.class);
                    if (position != null) {
                        listener.onDataReceived(position);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
}
