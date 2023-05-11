package com.example.simplechess.player;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Users {
    private String Uid;

    public Users() {
        // При создании объекта Users, создается объект Users с UID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setUID(user.getUid());
    }

    public Users(String Uid) {
        this.Uid = Uid;
    }

    public String getUid() {
        return Uid;
    }

    public void setUID(String Uid) {
        this.Uid = Uid;
    }
}
