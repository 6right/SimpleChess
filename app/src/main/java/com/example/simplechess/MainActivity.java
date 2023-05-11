package com.example.simplechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.simplechess.dataBase.FirebaseWriter;
import com.example.simplechess.login.Login;
import com.example.simplechess.player.Users;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private Game game;
    private final FirebaseWriter database = new FirebaseWriter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button backButton = new Button(this);
        Button playWhiteButton = new Button(this);
        Button playBlackButton = new Button(this);
        
        backButton.setText("Назад");
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            finish();
            startActivity(intent);

        });
        playWhiteButton.setText("Играть за белых");
        playWhiteButton.setOnClickListener(v -> {
            game = new Game(this);
            database.writeUserToDatabase(new Users(), true);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(backButton);
            linearLayout.addView(game);

            setContentView(linearLayout);
        });
        playBlackButton.setText("Играть за черных");
        playBlackButton.setOnClickListener(v -> {
            game = new Game(this);
            database.writeUserToDatabase(new Users(), false);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(backButton);
            linearLayout.addView(game);

            setContentView(linearLayout);
        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(playWhiteButton);
        linearLayout.addView(playBlackButton);

        setContentView(linearLayout);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DESTROY", "DESTROY");
    }
}
