package com.example.simplechess.player;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
