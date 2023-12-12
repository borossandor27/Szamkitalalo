package com.example.szamkitalalo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random random;
    int randomSzam, tippekSzama;
    ImageView elet1, elet2, elet3;
    EditText tipp;
    TextView eredmeny;
    Button tippel;
MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tippel.setOnClickListener(v -> {
            if (tipp.getText().toString().isEmpty()) {
                tipp.setError("Nem adtál meg számot!");
                return;
            }
            int tippSzam = Integer.parseInt(tipp.getText().toString());
            if (tippSzam < 1 || tippSzam > 10) {
                tipp.setError("1 és 10 közötti számot adj meg!");
                return;
            }
            tippekSzama++;
            if (tippSzam == randomSzam) {
                eredmeny.setText("Gratulálok, eltaláltad " + tippekSzama + " tippből!");
                mediaPlayer = MediaPlayer.create(this, R.raw.applause);
                mediaPlayer.start();//-- zene lejátszása
                tippel.setEnabled(false);
            } else {
                if (tippekSzama == 1) {
                    tippelesEredmenye(tippSzam);
                    elet3.setImageResource(R.drawable.heart_empty);
                } else if (tippekSzama == 2) {
                    tippelesEredmenye(tippSzam);
                    elet2.setImageResource(R.drawable.heart_empty);
                } else {
                    elet1.setImageResource(R.drawable.heart_empty);
                    eredmeny.setText("Sajnos nem találtad el " + tippekSzama + " tippből!\nA kitalálandó szám: " + randomSzam + " volt.");
                    tippel.setEnabled(false);
                }
            }
        });
    }

    private void tippelesEredmenye(int tippSzam) {
        if (tippSzam < randomSzam) {
            eredmeny.setText("A kitalálandó szám nagyobb!");
        } else {
            eredmeny.setText("A kitalálandó szám kisebb!");
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.failure);
        mediaPlayer.start();
    }


    private void init() {
        random = new Random();
        randomSzam = random.nextInt(10) + 1;
        tippekSzama = 0;
        tipp = findViewById(R.id.tipp);
        tippel = findViewById(R.id.ellenorzes);
        elet1 = findViewById(R.id.elet1);
        elet2 = findViewById(R.id.elet2);
        elet3 = findViewById(R.id.elet3);
        eredmeny = findViewById(R.id.eredmeny);
        eredmeny.setText("Még nem tippeltél!");
    }
}