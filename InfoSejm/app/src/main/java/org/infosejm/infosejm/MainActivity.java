package org.infosejm.infosejm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

import javax.net.ssl.HttpsURLConnection;

import static org.infosejm.infosejm.Tablica.members;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    EditText imie, nazwisko;
    //Generated with simple js script, not written manually. API has issues with finding elements using names.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Arrays.sort(members, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {

                double one = Double.parseDouble(o1[3]);
                double two = Double.parseDouble(o2[3]);
                if (one > two) {
                    return 1;
                } else if (one < two) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        textView = (TextView) findViewById(R.id.textView);
        imie = (EditText) findViewById(R.id.editText);
        nazwisko = (EditText) findViewById(R.id.editText2);
        textView = (TextView)findViewById(R.id.textView);
    }
    public void json(View view) {
        String imieInput = imie.getText().toString().toLowerCase();
        String imieOutput = imieInput.replace("ł", "l").replace("ą", "a").replace("ś", "s")
                .replace("ż", "z").replace("ć", "c").replace("ę", "e")
                .replace("ó", "o").replace("ź", "z").replace("ń", "n");
        String nazwiskoInput = nazwisko.getText().toString().toLowerCase();
        String nazwiskoOutput = nazwiskoInput.replace("ł", "l").replace("ą", "a").replace("ś", "s")
                .replace("ż", "z").replace("ć", "c").replace("ę", "e")
                .replace("ó", "o").replace("ź", "z").replace("ń", "n");
        String id = "";
        String posel = (nazwiskoOutput + "-" + imieOutput);

        for(int i = 0; i<members.length; i++){
            if(posel.equals(members[i][1])){
                id = members[i][0];
            }
        }
        new JSONTask().execute("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=krs");
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
                String dataUrodzenia = finalObject.getString("poslowie.data_urodzenia");
                String zamieszkanie = finalObject.getString("poslowie.miejsce_zamieszkania");
                String zawod = finalObject.getString("poslowie.zawod");
                // Actions
                String liczbaProjektowUchwal = finalObject.getString("poslowie.liczba_projektow_uchwal");
                String liczbaWypowiedzi = finalObject.getString("poslowie.liczba_wypowiedzi");
                String liczbaProjektowUstaw = finalObject.getString("poslowie.liczba_projektow_ustaw");
                String liczbaGlosowan = finalObject.getString("poslowie.liczba_glosowan");
                String liczbaGlosowowanOpuszczonych = finalObject.getString("poslowie.liczba_glosowan_opuszczonych");
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
                String krs;
                JSONObject finalObject2 = parentObject.getJSONObject("layers");
                JSONObject finalObject3 = finalObject2.getJSONObject("krs");
                JSONArray array = finalObject3.getJSONArray("ngo");
                if(array.length() == 0){
                    krs = "brak";
                }else {
                    JSONObject finalObject4 = array.getJSONObject(0);
                    JSONObject finalObject5 = finalObject4.getJSONObject("organizacja");
                    krs = finalObject5.getString("nazwa");
                }


                double sumaWydatkow = Double.parseDouble(biuroInne) +  Double.parseDouble(biuroEkspertyzy) +
                        Double.parseDouble(biuroMaterialy) + Double.parseDouble(biuroTaksowki) + Double.parseDouble( biuroZlecenia) +
                        Double.parseDouble(biuroWynagrodzenia) +
                        Double.parseDouble(biuroUposazenia) + Double.parseDouble(biuroSpotkania) + Double.parseDouble(biuroWyjazdy) +
                        Double.parseDouble(biuroPrzejazdy) +  Double.parseDouble(biuroRefundacja) +  Double.parseDouble(biuroTelekomunikacja ) +
                        + Double.parseDouble(biuroBiuro) + Double.parseDouble(biuroPodrozePracownikow ) + Double.parseDouble(biuroSrodkiTrwale);
                double zaokragloneWydatki = Math.round(sumaWydatkow * 100.0) / 100.0;
                long l = (long)zaokragloneWydatki;
                String zaokragloneWydatkiFormat = String.format("%,8d", l);


                return "Dane personalne" + "\n\nImię i nazwisko: " + nazwa + "\nData urodzenia: " + dataUrodzenia +
                        "\nMiejsce zamieszkania: " + zamieszkanie + "\nZawód: " + zawod +
                        "\nPartia: " + klub + "\nKRS: " +  krs + "\n\nDziałania w Sejmie RP" +
                        "\n\nLiczba projektów uchwał: " + liczbaProjektowUchwal + "\nLiczba wypowiedzi: " + liczbaWypowiedzi+
                        "\nLiczba projektów ustaw: " + liczbaProjektowUstaw + "\nLiczba głosowań: " + liczbaGlosowan +
                        "\nLiczba głosowań opuszczonych: " + liczbaGlosowowanOpuszczonych + "\n\nWydatki" +
                        "\n\nWydatki na ekspertyzy: " + biuroEkspertyzy + " zł" + "\nWydatki na materiały: " + biuroMaterialy + " zł" +
                        "\nWydatki na taksówki: " + biuroTaksowki + " zł" + "\nWydatki na zlecenia: " + biuroZlecenia + " zł" +
                        "\nWydatki na wynagrodzenia: " + biuroWynagrodzenia + " zł" + "\nWydatki na uposażenia: " + biuroUposazenia + " zł" +
                        "\nWydatki na spotkania: " + biuroSpotkania + " zł" + "\nWydatki na wyjazdy: " + biuroWyjazdy +
                        "\nWydatki na przejazdy: " + biuroPrzejazdy + " zł" + "\nWydatki na refudacja: " + biuroRefundacja + " zł" +
                        "\nWydatki na telekomunikacje: " + biuroTelekomunikacja + " zł" + "\nWydatki na biuro: " + biuroBiuro + " zł" +
                        "\nWydatki na podróże pracowników: " + biuroPodrozePracownikow + " zł" + "\nWydatki na środki trwałe: " + biuroSrodkiTrwale + " zł" +
                        "\nWydatki na biuro(inne): " + biuroInne + " zł" + "\n\nSuma wydatków: " + zaokragloneWydatkiFormat + " zł";
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
            textView.setText(result);

        }
    }

}
