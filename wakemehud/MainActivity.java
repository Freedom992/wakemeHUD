package com.example.anthagonas.wakemehud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity <T extends Fragment> extends AppCompatActivity {

    protected ArrayList <T> fragmentList = new ArrayList <T>();
    protected int fragmentListPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperation de l'etat de la batterie
        registerReceiver(infos_batterie, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        // Ajout des differents fragments
        T bob = (T) new Heure();
        T notif = (T) new Notifications();
        T meteo = (T) new Meteo();
        T rss = (T) new Rss();
        T agenda = (T) new Agenda();
        T agregateur = (T) new Agregateur();

        fragmentList.add(bob);
        fragmentList.add(notif);
        fragmentList.add(meteo);
        fragmentList.add(rss);
        fragmentList.add(agenda);
        fragmentList.add(agregateur);


        //Configuration du comportement des boutons
        Button hud = (Button) findViewById(R.id.hud);
        hud.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                FrameLayout layout = (FrameLayout) findViewById(R.id.framelayout); // Recupere le framelayout
                layout.setScaleY(-layout.getScaleY()); // retourne le framelayout ( et donc le fragment affiché)
            }
        });


        Button suivant = (Button) findViewById(R.id.suivant) ;
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fragmentListPosition = (fragmentListPosition+1)%fragmentList.size(); // se déplace dans la liste des fragments
                FragmentTransaction fragmentManager= getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.framelayout, fragmentList.get(fragmentListPosition)); // Remplace le fragment actuel par le suivant dans la liste
                fragmentManager.commit();
            }
        });

        Button parametres = (Button) findViewById(R.id.parametres);
        parametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, Parametres.class); // Chargement de l'activite parametres
                startActivity(myIntent); // lancement de l'activite
            }
        });

        if (savedInstanceState != null)
        { return;} // la suite du code n'est a appliquer que si l'appli demarre

        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.add(R.id.framelayout, fragmentList.get(0)); // affichage du fragment par defaut (premier dans la liste)
        fragmentManager.commit();
    }

    //affichage de l'etat de la batterie :
    private BroadcastReceiver infos_batterie = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
            int level = i.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            TextView etat = (TextView) findViewById(R.id.batterie);
            etat.setText("Niveau de batterie: " + Integer.toString(level) + "%");
        }
    };

    // Quitter l'appli lors de l'appui sur la touche retour
    @Override
    public void onBackPressed()
    {
        finish();
    }

}
