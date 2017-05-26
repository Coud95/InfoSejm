package org.infosejm.infosejm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
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
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    EditText imie, nazwisko;
    //Generated with simple js script, not written manually. API has issues with finding elements using names.
    String members[][] = new String[][]{{"2","adamczak-malgorzata"},{"3","adamczyk-andrzej"},{"5","aleksandrzak-leszek"},{"6","andzel-waldemar"},{"4","ajchler-romuald"},{"7","arciszewska-mielewczyk-dorota"},{"1","abramowicz-adam"},{"8","ardanowski-jan-krzysztof"},{"9","arent-iwona"},{"10","arkit-tadeusz"},{"13","ast-marek"},{"12","arndt-pawel"},{"11","arlukowicz-bartosz"},{"15","aziewicz-tadeusz"},{"14","augustyn-urszula"},{"16","babalski-zbigniew"},{"20","bankowska-anna"},{"22","bauc-piotr-pawel"},{"19","banaszak-maciej"},{"21","bartus-barbara"},{"17","babinetz-piotr"},{"18","balt-marek"},{"24","bernacki-wlodzimierz"},{"26","biedron-robert"},{"25","betkowski-andrzej"},{"23","bak-dariusz"},{"27","biernacki-marek"},{"28","biernat-andrzej"},{"29","blanik-leszek"},{"32","bodio-bartlomiej"},{"33","bogucki-jacek"},{"31","bobowska-joanna"},{"30","blaszczak-mariusz"},{"34","borawski-edmund"},{"35","borkowski-jerzy"},{"38","borowiak-lukasz"},{"36","borkowski-krzysztof"},{"39","bramora-artur"},{"37","borowczak-jerzy"},{"40","brejza-krzysztof"},{"41","brudzinski-joachim"},{"45","budka-borys"},{"42","brzezinka-jacek"},{"43","bublewicz-beata"},{"44","bubula-barbara"},{"49","bury-jan"},{"46","budnik-jerzy"},{"50","butryn-renata"},{"51","cedzynski-jan"},{"48","bula-andrzej"},{"47","bukiewicz-bozenna"},{"53","chmielowiec-zbigniew"},{"52","charlampowicz-jaroslaw"},{"55","chomycz-malgorzata"},{"54","chmielowski-piotr"},{"57","cieslinski-piotr"},{"56","cichon-janusz"},{"59","czaplicka-barbara"},{"62","czechyra-czeslaw"},{"63","czerniak-jacek"},{"58","cycon-marian"},{"61","czartoryski-arkadiusz"},{"60","czarnecki-witold"},{"64","czernow-zofia"},{"68","dabrowska-alicja"},{"67","czykwin-eugeniusz"},{"65","czerwinski-andrzej"},{"66","czesak-edward"},{"69","dabrowski-andrzej"},{"71","debski-artur"},{"72","dobrzynski-leszek"},{"70","dera-andrzej"},{"73","dolata-zbigniew"},{"74","domaracki-marek"},{"75","dorn-ludwik"},{"78","dunin-artur"},{"76","drozd-ewa"},{"81","dziedziczak-jan"},{"77","duda-andrzej"},{"80","dziadzio-dariusz-cezar"},{"82","dzieciol-janusz"},{"85","elsner-wincenty"},{"86","fabisiak-joanna"},{"79","durka-zenon"},{"83","dzikowski-waldy"},{"84","dziuba-tadeusz"},{"90","fotyga-anna"},{"92","galla-ryszard"},{"91","gadowski-krzysztof"},{"88","fedorowicz-jerzy"},{"89","fiedler-arkady"},{"87","falfus-jacek"},{"94","gapinska-elzbieta"},{"95","garbowski-tomasz"},{"93","galazewski-andrzej"},{"98","gasior-marek-magdalena"},{"96","gawlowski-stanislaw"},{"97","gadek-lidia"},{"102","girzynski-zbigniew"},{"101","gierada-artur"},{"103","gizynski-szymon"},{"99","gelert-elzbieta"},{"100","gibala-lukasz"},{"104","gluza-czeslaw"},{"106","godson-john-abraham"},{"105","glogowski-tomasz"},{"107","golba-mieczyslaw"},{"108","golojuch-kazimierz"},{"109","gos-marek"},{"110","gosiewska-malgorzata"},{"111","gowin-jaroslaw"},{"112","gorczynski-artur"},{"113","gorczynski-jaroslaw"},{"114","gorski-artur"},{"115","gorski-tomasz"},{"116","grabarczyk-cezary"},{"118","grad-mariusz"},{"117","grad-aleksander"},{"119","gras-pawel"},{"120","grodzka-anna"},{"121","grupinski-rafal"},{"122","grzeszczak-eugeniusz-tomasz"},{"123","gut-mostowy-andrzej"},{"124","guzowska-iwona"},{"125","halicki-andrzej"},{"127","hanajczyk-agnieszka"},{"126","hall-katarzyna"},{"129","hofman-adam"},{"132","hrynkiewicz-jozefa"},{"133","huskowski-stanislaw"},{"128","hoc-czeslaw"},{"131","hoppe-teresa"},{"130","hok-marek"},{"137","jagiello-jaroslaw-tomasz"},{"135","jach-michal"},{"136","jackiewicz-dawid"},{"134","iwinski-tadeusz"},{"138","jaki-patryk"},{"139","janczyk-wieslaw"},{"142","jaros-michal"},{"140","janyska-maria-malgorzata"},{"143","jasinski-wojciech"},{"145","jaworski-andrzej"},{"141","jarmuziewicz-tadeusz"},{"144","jastrzebski-leszek"},{"147","jonski-dariusz"},{"146","jedrysek-mariusz-orion"},{"150","kaczmarek-tomasz"},{"151","kaczor-roman"},{"149","kabacinski-michal"},{"153","kalemba-stanislaw"},{"152","kaczynski-jaroslaw"},{"148","jurgiel-krzysztof"},{"156","kaminski-mariusz"},{"157","kaminski-mariusz-antoni"},{"159","kania-andrzej"},{"154","kalisz-ryszard"},{"155","kaminska-bozena"},{"158","kaminski-tomasz"},{"164","kempa-beata"},{"160","karpinski-wlodzimierz"},{"161","kasprzak-mieczyslaw"},{"162","katulski-jaroslaw"},{"163","kazmierczak-jan"},{"165","kepinski-adam"},{"167","kierwinski-marcin"},{"166","kidawa-blonska-malgorzata"},{"170","kluzik-rostkowska-joanna"},{"168","klepacz-witold"},{"169","kloc-izabela"},{"171","klopotek-eugeniusz"},{"175","kmiecik-henryk"},{"173","klosowski-krzysztof"},{"172","klosin-krystyna"},{"174","klosowski-slawomir"},{"176","kochan-magdalena"},{"177","kolenda-labus-brygida"},{"179","kolakowski-lech"},{"178","kolacz-leszczynska-agnieszka"},{"180","kolakowski-robert"},{"183","kopacz-ewa"},{"181","kolodziej-ewa"},{"182","konwinski-zbigniew"},{"185","kopycinski-slawomir"},{"184","kopaczewska-domicela"},{"188","kotlinski-roman"},{"187","kosecki-roman-jacek"},{"189","kowalczyk-henryk"},{"186","korzeniowski-leszek"},{"194","kozlowska-rajewicz-agnieszka"},{"195","kozlowska-iwona"},{"190","kowalski-slawomir"},{"191","kownacki-bartosz"},{"192","kozaczynski-jacek"},{"193","kozdron-jerzy"},{"198","krajewska-ligia"},{"197","kraczkowski-maks"},{"199","krasulski-leonard"},{"200","kropiwnicki-robert"},{"196","kozlakiewicz-miroslaw"},{"201","kruk-elzbieta"},{"202","krupa-lukasz"},{"206","kudrycka-barbara"},{"207","kuzmiuk-zbigniew"},{"205","kuchcinski-marek"},{"203","krzakala-marek"},{"204","kucharski-cezary"},{"208","kwiatkowski-adam"},{"209","kwiatkowski-jacek"},{"211","lamczyk-stanislaw"},{"210","kwiatkowski-krzysztof"},{"213","latos-tomasz"},{"212","lassota-jozef"},{"215","leszczyna-izabela"},{"216","lewandowski-andrzej"},{"217","lipiec-krzysztof"},{"214","lenz-tomasz"},{"218","lipinski-adam"},{"219","litwinski-arkadiusz"},{"222","lawrynowicz-zofia"},{"225","luczak-mieczyslaw-marcin"},{"221","latas-marek"},{"220","lapinski-marek"},{"223","lopata-jan"},{"224","lopinski-maciej"},{"226","lybacka-krystyna"},{"227","machalek-marzena"},{"230","malik-ewa"},{"228","macierewicz-antoni"},{"229","makowski-tomasz"},{"231","maliszewski-miroslaw"},{"235","marczulajtis-walczak-jagna"},{"232","malecka-libera-beata"},{"234","marcinkiewicz-malgorzata"},{"236","maslowska-gabriela"},{"237","mastalerek-marcin"},{"233","malecki-maciej"},{"238","materna-jerzy"},{"240","matusik-lipiec-katarzyna"},{"239","matusiak-grzegorz"},{"241","matuszczak-zbigniew"},{"242","matuszewski-marek"},{"243","mazurek-beata"},{"244","mezydlo-antoni"},{"246","miller-leszek"},{"247","miller-rajmund"},{"245","michalkiewicz-krzysztof"},{"248","miodowicz-konstanty"},{"250","mroczek-czeslaw"},{"249","moskal-kazimierz"},{"252","mrzyglocka-izabela-katarzyna"},{"254","mularczyk-arkadiusz"},{"251","mroczek-maciej"},{"253","mucha-joanna"},{"257","najder-jacek"},{"255","munyama-killion"},{"258","napieralski-grzegorz"},{"256","naimski-piotr"},{"259","nems-anna"},{"260","neumann-slawomir"},{"263","niesiolowski-stefan"},{"262","niemczyk-malgorzata"},{"261","niedziela-dorota"},{"264","nowak-maria"},{"265","nowak-slawomir"},{"269","okla-drewnowicz-marzena"},{"268","nykiel-miroslawa"},{"266","nowak-tomasz-piotr"},{"267","nowicka-wanda"},{"270","okragly-janina"},{"271","olechowska-alicja"},{"273","olszewski-pawel"},{"276","orzechowski-maciej"},{"272","olejniczak-cezary"},{"277","ostrowski-artur"},{"274","opiola-marek"},{"275","orzechowski-andrzej"},{"279","oswiecimski-konstanty"},{"280","ozga-krystyna"},{"278","osuch-jacek"},{"283","pacholski-michal-tomasz"},{"281","ozog-stanislaw"},{"282","pacelt-zbigniew"},{"286","paluch-anna"},{"285","palikot-janusz"},{"284","pahl-witold"},{"287","papke-pawel"},{"291","penkalski-wojciech"},{"290","pawlowicz-krystyna"},{"289","pawlak-waldemar"},{"292","pepek-malgorzata"},{"288","pawlak-miroslaw"},{"293","piatak-andrzej"},{"296","piechota-slawomir-jan"},{"294","piecha-boleslaw-grzegorz"},{"295","piechocinski-janusz"},{"298","pietraszewska-danuta"},{"297","pierzchala-elzbieta-apolonia"},{"299","pietrzczyk-lucjan-marek"},{"301","pieta-stanislaw"},{"302","piontkowski-dariusz"},{"305","pitera-julia"},{"303","piotrowicz-stanislaw"},{"304","piotrowska-teresa"},{"300","pieta-jaroslaw"},{"310","polak-marek"},{"308","pluta-miroslaw"},{"306","plocke-kazimierz"},{"307","plura-marek"},{"309","polaczek-jerzy"},{"311","polak-piotr"},{"314","popiolek-zofia"},{"317","przadka-stanislawa"},{"315","poslednia-krystyna"},{"313","popiolek-krzysztof"},{"312","pomaska-agnieszka"},{"316","poznanski-marek"},{"318","pyzik-piotr"},{"320","raczkowski-damian"},{"319","racki-jozef"},{"322","rafalska-elzbieta"},{"321","radziszewska-elzbieta"},{"323","raniewicz-grzegorz"},{"324","ras-ireneusz"},{"326","rogacki-adam"},{"325","rebek-jerzy"},{"328","romanek-andrzej"},{"327","rojek-jozef"},{"330","rozenek-andrzej"},{"329","rosati-dariusz"},{"331","rozpondek-halina"},{"333","rutkowska-dorota"},{"332","rusiecki-jaroslaw"},{"334","rutnicki-jakub"},{"335","rybakowicz-adam"},{"336","rybicki-slawomir"},{"337","ryfinski-armand-kamil"},{"338","rynasiewicz-zbigniew"},{"339","rzasa-marek"},{"341","sadurska-malgorzata"},{"340","rzonca-bogdan"},{"342","sajak-pawel"},{"344","sasin-jacek"},{"343","saluga-wojciech"},{"345","sawicki-marek"},{"346","schetyna-grzegorz"},{"348","sekula-szmajdzinska-malgorzata"},{"347","schreiber-grzegorz"},{"349","seliga-dariusz"},{"351","siarka-edward"},{"350","sellin-jaroslaw"},{"354","sikorski-radoslaw"},{"356","slawiak-bozena"},{"352","sibinska-krystyna"},{"355","skowronska-krystyna"},{"353","siedlaczek-henryk"},{"357","slugocki-waldemar"},{"360","sobecka-anna-elzbieta"},{"362","sprawka-lech"},{"358","smolarz-henryk"},{"359","smolarz-tomasz"},{"361","sosnowski-zbigniew"},{"363","staron-lidia"},{"365","stolarski-marek"},{"367","suchowiejko-wieslaw"},{"366","strzalkowski-stefan"},{"368","suski-marek"},{"364","stefaniuk-franciszek-jerzy"},{"373","szczerba-michal"},{"372","szarama-wojciech"},{"369","suski-pawel"},{"371","szalamacha-pawel"},{"370","sycz-miron"},{"374","szczerski-krzysztof"},{"377","szeliga-piotr"},{"376","szejnfeld-adam"},{"375","szczypinska-jolanta"},{"378","szlachta-andrzej"},{"380","sztolcman-grzegorz"},{"379","szmit-jerzy"},{"381","sztorc-andrzej"},{"382","szulc-jakub"},{"385","szydlo-beata"},{"383","szumilas-krystyna"},{"386","szydlowska-bozena"},{"387","szymanski-tomasz"},{"388","szymiec-raczynska-halina"},{"384","szwed-stanislaw"},{"390","sledzinska-katarasinska-iwona"},{"389","szyszko-jan"},{"391","sniadek-janusz"},{"394","tchorzewski-krzysztof"},{"393","swiecicki-marcin"},{"392","swiat-jacek"},{"395","telus-robert"},{"396","terlecki-ryszard"},{"397","tobiszowski-grzegorz"},{"400","tomaszewski-jan"},{"399","tomanski-piotr"},{"398","tokarska-genowefa"},{"402","tomczak-jacek"},{"403","tomczyk-cezary"},{"401","tomaszewski-tadeusz"},{"406","tusk-donald"},{"404","tomczykiewicz-tomasz"},{"407","tyszkiewicz-robert"},{"405","trybus-cieslar-aleksandra"},{"408","ujazdowski-kazimierz-michal"},{"409","coghen-piotr-van-der"},{"412","wardzala-robert"},{"410","vincent-rostowski-jan"},{"413","warzecha-jan"},{"411","walkowski-piotr"},{"414","waszczykowski-witold"},{"415","wenderlich-jerzy"},{"416","wielichowska-monika"},{"419","witczak-mariusz"},{"418","wisniewska-jadwiga"},{"417","wipler-przemyslaw"},{"420","witek-elzbieta"},{"421","witko-marcin"},{"425","wojtkiewicz-michal"},{"423","wlodkowski-zbigniew"},{"424","wojnarowski-norbert"},{"422","witkowski-radoslaw"},{"426","wojtkowski-marek"},{"427","wolak-ewa"},{"430","wozniak-tadeusz"},{"431","wojcik-marek"},{"428","wontor-boguslaw"},{"429","wozniak-grzegorz-adam"},{"432","wrobel-marzena-dorota"},{"433","wydrzynski-maciej"},{"435","zaborowski-zbyszek"},{"436","zakrzewska-jadwiga"},{"434","wziatek-stanislaw"},{"437","zalewska-anna"},{"439","zawadzki-ryszard"},{"438","zaremba-renata"},{"440","zawislak-slawomir"},{"441","zbonikowski-lukasz"},{"442","zbrzyzny-ryszard"},{"443","zdrojewski-bogdan"},{"445","zielinski-jaroslaw"},{"446","zielinski-maciej"},{"444","zgorzelski-piotr"},{"447","ziemniak-wojciech"},{"451","zlotowski-kosma"},{"449","ziobro-jan"},{"448","zietek-jerzy"},{"450","ziobro-kazimierz"},{"452","zuba-maria"},{"456","zalek-jacek"},{"453","zubowski-wojciech"},{"455","zaczek-jaroslaw"},{"454","zych-jozef"},{"457","zelichowski-stanislaw"},{"458","zmijan-stanislaw"},{"460","zyzynski-jerzy"},{"459","zmuda-trzebiatowska-ewa"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        new JSONTask().execute("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json");
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

                double sumaWydatkow = Double.parseDouble(biuroInne) +  Double.parseDouble(biuroEkspertyzy) +
                        Double.parseDouble(biuroMaterialy) + Double.parseDouble(biuroTaksowki) + Double.parseDouble( biuroZlecenia) +
                        Double.parseDouble(biuroWynagrodzenia) +
                        Double.parseDouble(biuroUposazenia) + Double.parseDouble(biuroSpotkania) + Double.parseDouble(biuroWyjazdy) +
                        Double.parseDouble(biuroPrzejazdy) +  Double.parseDouble(biuroRefundacja) +  Double.parseDouble(biuroTelekomunikacja ) +
                        + Double.parseDouble(biuroBiuro) + Double.parseDouble(biuroPodrozePracownikow ) + Double.parseDouble(biuroSrodkiTrwale);
                double zaokragloneWydatki = Math.round(sumaWydatkow * 100.0) / 100.0;
                return "Dane personalne" + "\n\nImię i nazwisko: " + nazwa + "\nData urodzenia: " + dataUrodzenia +
                        "\nMiejsce zamieszkania: " + zamieszkanie + "\nZawód: " + zawod +
                        "\nPartia: " + klub + "\n\nDziałania w Sejmie RP" +
                        "\n\nLiczba projektów uchwał: " + liczbaProjektowUchwal + "\nLiczba wypowiedzi: " + liczbaWypowiedzi+
                        "\nLiczba projektów ustaw: " + liczbaProjektowUstaw + "\nLiczba głosowań: " + liczbaGlosowan +
                        "\nLiczba głosowań opuszczonych: " + liczbaGlosowowanOpuszczonych + "\n\nWydatki" +
                        "\n\nWydatki na ekspertyzy: " + biuroEkspertyzy + "\nWydatki na materiały: " + biuroMaterialy +
                        "\nWydatki na taksówki: " + biuroTaksowki + "\nWydatki na zlecenia: " + biuroZlecenia +
                        "\nWydatki na wynagrodzenia: " + biuroWynagrodzenia + "\nWydatki na uposażenia: " + biuroUposazenia +
                        "\nWydatki na spotkania: " + biuroSpotkania + "\nWydatki na wyjazdy: " + biuroWyjazdy +
                        "\nWydatki na przejazdy: " + biuroPrzejazdy + "\nWydatki na refudacja: " + biuroRefundacja +
                        "\nWydatki na telekomunikacje: " + biuroTelekomunikacja + "\nWydatki na biuro: " + biuroBiuro +
                        "\nWydatki na podróże pracowników: " + biuroPodrozePracownikow + "\nWydatki na środki trwałe: " + biuroSrodkiTrwale +
                        "\nWydatki na biuro(inne): " + biuroInne + "\n\nSuma wydatków: " + zaokragloneWydatki;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
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
