package org.infosejm.infosejm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.Comparator;

public class Ranking extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Log.i("wiadomosc", MainActivity.members[459][4]);
        Log.i("wiadomosc", MainActivity.members[459][0]);
        Log.i("wiadomosc", MainActivity.members[459][1]);
        Log.i("wiadomosc", MainActivity.members[459][2]);
        Log.i("wiadomosc", MainActivity.members[459][3]);

    }
}
