package org.infosejm.infosejm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void compare(View view) {
        Intent intent = new Intent(this, CompareActivity.class);
        startActivity(intent);
    }

    public void ranking(View view) {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }

    public void about(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(Menu.this).create();
        alertDialog.setTitle("O aplikacji");
        alertDialog.setMessage("Aplikacja InfoSejm umożliwia użytkownikowi dowiedzieć się więcej na temat posłów Sejmu RP. Funkcjonalności to wyświetlenie szczegółowych informacji o danym pośle, porównanie dwóch posłów, a także ranking wydatków partii oraz posłów. Aplikacja korzysta z API Sejmometr.");
        alertDialog.show();
    }
}
