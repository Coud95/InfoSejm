package org.infosejm.infosejm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CompareActivity extends AppCompatActivity {
    EditText imie1, nazwisko1, imie2, nazwisko2;
    TextView person1, person2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        imie1 = (EditText) findViewById(R.id.imie1);
        imie2 = (EditText) findViewById(R.id.imie2);
        nazwisko1 = (EditText) findViewById(R.id.nazwisko1);
        nazwisko2 = (EditText) findViewById(R.id.nazwisko2);
        person1 = (TextView) findViewById(R.id.person1);
        person2 = (TextView) findViewById(R.id.person2);
    }


    public void json(View view) {
        String imieInput = imie1.getText().toString().toLowerCase();
        String imieOutput = imieInput.replace("ł", "l").replace("ą", "a").replace("ś", "s")
                .replace("ż", "z").replace("ć", "c").replace("ę", "e")
                .replace("ó", "o").replace("ź", "z").replace("ń", "n");
        String nazwiskoInput = nazwisko1.getText().toString().toLowerCase();
        String nazwiskoOutput = nazwiskoInput.replace("ł", "l").replace("ą", "a").replace("ś", "s")
                .replace("ż", "z").replace("ć", "c").replace("ę", "e")
                .replace("ó", "o").replace("ź", "z").replace("ń", "n");
        String imieInput2 = imie2.getText().toString().toLowerCase();
        String imieOutput2 = imieInput2.replace("ł", "l").replace("ą", "a").replace("ś", "s")
                .replace("ż", "z").replace("ć", "c").replace("ę", "e")
                .replace("ó", "o").replace("ź", "z").replace("ń", "n");
        String nazwiskoInput2 = nazwisko2.getText().toString().toLowerCase();
        String nazwiskoOutput2 = nazwiskoInput2.replace("ł", "l").replace("ą", "a").replace("ś", "s")
                .replace("ż", "z").replace("ć", "c").replace("ę", "e")
                .replace("ó", "o").replace("ź", "z").replace("ń", "n");
        String id = "";
        String id2 = "";
        String posel = (nazwiskoOutput + "-" + imieOutput);
        String posel2 = (nazwiskoOutput2 + "-" + imieOutput2);

        for (int i = 0; i < MainActivity.members.length; i++) {
            if (posel.equals(MainActivity.members[i][1])) {
                id = MainActivity.members[i][0];
            }
            if (posel2.equals(MainActivity.members[i][1])) {
                id2 = MainActivity.members[i][0];
            }
        }
        new JSONTask().execute("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json");
        new JSONTask2().execute("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id2 + ".json");
    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("");
                }
                String finalJSon = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJSon);
                JSONObject finalObject = parentObject.getJSONObject("data");
                //Personal data
                String nazwa = finalObject.getString("ludzie.nazwa");
                String klub = finalObject.getString("sejm_kluby.nazwa");
                // Money spent
                String biuroInne = finalObject.getString("poslowie.wartosc_biuro_inne");
                String biuroEkspertyzy = finalObject.getString("poslowie.wartosc_biuro_ekspertyzy");
                String biuroMaterialy = finalObject.getString("poslowie.wartosc_biuro_materialy_biurowe");
                String biuroTaksowki = finalObject.getString("poslowie.wartosc_biuro_taksowki");
                String biuroZlecenia = finalObject.getString("poslowie.wartosc_biuro_zlecenia");
                String biuroWynagrodzenia = finalObject.getString("poslowie.wartosc_biuro_wynagrodzenia_pracownikow");
                String biuroUposazenia = finalObject.getString("poslowie.wartosc_uposazenia_pln");
                String biuroSpotkania = finalObject.getString("poslowie.wartosc_biuro_spotkania");
                String biuroWyjazdy = finalObject.getString("poslowie.wartosc_wyjazdow");
                String biuroPrzejazdy = finalObject.getString("poslowie.wartosc_biuro_przejazdy");
                String biuroRefundacja = finalObject.getString("poslowie.wartosc_refundacja_kwater_pln");
                String biuroTelekomunikacja = finalObject.getString("poslowie.wartosc_biuro_telekomunikacja");
                String biuroBiuro = finalObject.getString("poslowie.wartosc_biuro_biuro");
                String biuroPodrozePracownikow = finalObject.getString("poslowie.wartosc_biuro_podroze_pracownikow");
                String biuroSrodkiTrwale = finalObject.getString("poslowie.wartosc_biuro_srodki_trwale");

                double sumaWydatkow = Double.parseDouble(biuroInne) + Double.parseDouble(biuroEkspertyzy) +
                        Double.parseDouble(biuroMaterialy) + Double.parseDouble(biuroTaksowki) + Double.parseDouble(biuroZlecenia) +
                        Double.parseDouble(biuroWynagrodzenia) +
                        Double.parseDouble(biuroUposazenia) + Double.parseDouble(biuroSpotkania) + Double.parseDouble(biuroWyjazdy) +
                        Double.parseDouble(biuroPrzejazdy) + Double.parseDouble(biuroRefundacja) + Double.parseDouble(biuroTelekomunikacja) +
                        +Double.parseDouble(biuroBiuro) + Double.parseDouble(biuroPodrozePracownikow) + Double.parseDouble(biuroSrodkiTrwale);
                double zaokragloneWydatki = Math.round(sumaWydatkow * 100.0) / 100.0;
                long l = (long)zaokragloneWydatki;
                String zaokragloneWydatkiFormat = String.format("%,8d", l);

                return nazwa + "\nPartia: " + klub + "\n\nSuma wydatków: " + zaokragloneWydatkiFormat + " zł";
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            person1.setText(result);
        }
    }

    public class JSONTask2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("");
                }
                String finalJSon = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJSon);
                JSONObject finalObject = parentObject.getJSONObject("data");
                //Personal data
                String nazwa = finalObject.getString("ludzie.nazwa");
                String klub = finalObject.getString("sejm_kluby.nazwa");
                // Money spent
                String biuroInne = finalObject.getString("poslowie.wartosc_biuro_inne");
                String biuroEkspertyzy = finalObject.getString("poslowie.wartosc_biuro_ekspertyzy");
                String biuroMaterialy = finalObject.getString("poslowie.wartosc_biuro_materialy_biurowe");
                String biuroTaksowki = finalObject.getString("poslowie.wartosc_biuro_taksowki");
                String biuroZlecenia = finalObject.getString("poslowie.wartosc_biuro_zlecenia");
                String biuroWynagrodzenia = finalObject.getString("poslowie.wartosc_biuro_wynagrodzenia_pracownikow");
                String biuroUposazenia = finalObject.getString("poslowie.wartosc_uposazenia_pln");
                String biuroSpotkania = finalObject.getString("poslowie.wartosc_biuro_spotkania");
                String biuroWyjazdy = finalObject.getString("poslowie.wartosc_wyjazdow");
                String biuroPrzejazdy = finalObject.getString("poslowie.wartosc_biuro_przejazdy");
                String biuroRefundacja = finalObject.getString("poslowie.wartosc_refundacja_kwater_pln");
                String biuroTelekomunikacja = finalObject.getString("poslowie.wartosc_biuro_telekomunikacja");
                String biuroBiuro = finalObject.getString("poslowie.wartosc_biuro_biuro");
                String biuroPodrozePracownikow = finalObject.getString("poslowie.wartosc_biuro_podroze_pracownikow");
                String biuroSrodkiTrwale = finalObject.getString("poslowie.wartosc_biuro_srodki_trwale");

                double sumaWydatkow = Double.parseDouble(biuroInne) + Double.parseDouble(biuroEkspertyzy) +
                        Double.parseDouble(biuroMaterialy) + Double.parseDouble(biuroTaksowki) + Double.parseDouble(biuroZlecenia) +
                        Double.parseDouble(biuroWynagrodzenia) +
                        Double.parseDouble(biuroUposazenia) + Double.parseDouble(biuroSpotkania) + Double.parseDouble(biuroWyjazdy) +
                        Double.parseDouble(biuroPrzejazdy) + Double.parseDouble(biuroRefundacja) + Double.parseDouble(biuroTelekomunikacja) +
                        +Double.parseDouble(biuroBiuro) + Double.parseDouble(biuroPodrozePracownikow) + Double.parseDouble(biuroSrodkiTrwale);
                double zaokragloneWydatki = Math.round(sumaWydatkow * 100.0) / 100.0;
                long l = (long)zaokragloneWydatki;
                String zaokragloneWydatkiFormat = String.format("%,8d", l);

                return nazwa + "\nPartia: " + klub + "\n\nSuma wydatków: " + zaokragloneWydatkiFormat + " zł";
                
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            person2.setText(result);
        }
    }
}


