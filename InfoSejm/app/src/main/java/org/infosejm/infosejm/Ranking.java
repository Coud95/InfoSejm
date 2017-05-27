package org.infosejm.infosejm;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;

public class Ranking extends AppCompatActivity {
    TextView ranking;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ranking = (TextView) findViewById(R.id.ranking);
        rankingList();
    }

    public void rankingList() {
        StringBuilder text = new StringBuilder();
        int position = 1;
        for (int i = 459; i > 449; i--) {
            double d = Double.parseDouble(MainActivity.members[i][3]);
            double wydatki = Math.round(d * 100.0) / 100.0;
            text.append(position).append(". ").append(MainActivity.members[i][4]).append(" Partia: ").append(MainActivity.members[i][2]).append(" Wydatki: ").append(wydatki).append("\n");
            position++;
        }
        ranking.setText(text);
    }
}
