package org.infosejm.infosejm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import static org.infosejm.infosejm.Tablica.members;
import java.util.Arrays;
import java.util.Comparator;

public class Ranking extends AppCompatActivity {
    //TextView[] ranking = new TextView[10];
    TextView rankingPartii, rankingPoslow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        /*
        ranking[0] = (TextView) findViewById(R.id.rankPos1);
        ranking[1] = (TextView) findViewById(R.id.rankPos2);
        ranking[2] = (TextView) findViewById(R.id.rankPos3);
        ranking[3] = (TextView) findViewById(R.id.rankPos4);
        ranking[4] = (TextView) findViewById(R.id.rankPos5);
        ranking[5] = (TextView) findViewById(R.id.rankPos6);
        ranking[6] = (TextView) findViewById(R.id.rankPos7);
        ranking[7] = (TextView) findViewById(R.id.rankPos8);
        ranking[8] = (TextView) findViewById(R.id.rankPos9);
        ranking[9] = (TextView) findViewById(R.id.rankPos10);
        */
        rankingPoslow = (TextView) findViewById(R.id.rankingPoslow);
        rankingPartii = (TextView) findViewById(R.id.rankPartii);
        rankingList();

    }

    public void rankingList() {
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
        /*
        int j = 0;
        for (int i = 459; i > 449; i--) {
            double wyd = Double.parseDouble(MainActivity.members[i][3]);
            double wydRounded = Math.round(wyd * 100.0) / 100.0;
            long l = (long)wydRounded;
            String wydPos = String.format("%,8d", l);
                ranking[j].setText((j+1) + ". " + MainActivity.members[i][4] + " (" + MainActivity.members[i][2] + ")\n" + "Wydatki: " + wydPos + " zł");
            j++;
        }
        */
        StringBuilder textPosel = new StringBuilder();
        int position = 1;
        for (int i = 459; i > 449; i--) {
            double d = Double.parseDouble(members[i][3]);
            double wydatki = Math.round(d * 100.0) / 100.0;
            long l = (long)wydatki;
            String poselWydatki = String.format("%,8d", l);
            textPosel.append(position).append(". ").append(members[i][4]).append(" \nPartia: ").append(members[i][2]).append(" \nWydatki: ").append(poselWydatki).append(" zł\n");
            position++;
        }
        rankingPoslow.setText(textPosel);


        double pis = 0;
        double po = 0;
        double psl = 0;
        double sld = 0;
        double niezrzeszeni = 0;
        double bc = 0;
        double zp = 0;
        double rp = 0;

        String partie[][] = new String[][]{{"PiS",""},{"PO",""},{"PSL",""},{"SLD",""},{"Biało-Czerwoni",""},{"ZP",""},{"Niezrzeszeni",""},{"RP",""},};

        for(int i = 0; i<members.length; i++){
            if(members[i][2].equals("PiS")){
                pis += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("PO")){
                po += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("PSL")){
                psl += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("SLD")){
                sld += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("Biało-Czerwoni")){
                bc += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("ZP")){
                zp += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("Niezrzeszeni")){
                niezrzeszeni += Double.parseDouble(members[i][3]);
            }else if(members[i][2].equals("RP")){
                rp += Double.parseDouble(members[i][3]);
            }
        }

        partie[0][1] = Double.toString(pis);
        partie[1][1] = Double.toString(po);
        partie[2][1] = Double.toString(psl);
        partie[3][1] = Double.toString(sld);
        partie[4][1] = Double.toString(niezrzeszeni);
        partie[5][1] = Double.toString(bc);
        partie[6][1] = Double.toString(zp);
        partie[7][1] = Double.toString(rp);

        Arrays.sort(partie, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {

                double one = Double.parseDouble(o1[1]);
                double two = Double.parseDouble(o2[1]);
                if (one > two) {
                    return 1;
                } else if (one < two) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });


        StringBuilder text = new StringBuilder();
        int positionPartia = 1;
        for (int i = 7; i >= 0; i--) {
            double d = Double.parseDouble(partie[i][1]);
            double wydatki = Math.round(d * 100.0) / 100.0;
            long l = (long)wydatki;
            String w = String.format("%,8d", l);
            text.append(positionPartia).append(". ").append(partie[i][0]).append(" \nWydatki: ").append(w).append(" zł\n");
            positionPartia++;
        }
        //  onClick do goscia
        rankingPartii.setText(text);

    }
}
