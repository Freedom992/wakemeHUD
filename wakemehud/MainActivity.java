package com.example.anthagonas.wakemehud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextClock;

public class MainActivity extends Activity {
    protected int scaleY_value = 1; // tentative pour garder le -1 sur toutes les activités
    @Override
    public void onCreate(Bundle savedInstanceState) { // Cree notre activite main avec l'heure
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextClock digital = (TextClock) findViewById(R.id.digital_clock);
    }

    //Permet de passer d'un activité a une autre TODO : Pour toutes les activités (Que notifications pour le moment)
    public void changeActivity(View v)
    {
        Intent TaskIntent = new Intent(this,Notifications.class);
        startActivity(TaskIntent);
    }

    //Contient les methodes utilisables par les bouttons
    public class ButtonListener implements android.view.View.OnClickListener
    {
        public void onClick(View v)
        {
            changeActivity(v);
            mirror(v);
        }
    }

    //Permet a un boutton de retourner les elements "Hudable"
    public void mirror(View v)
    {
        View parent = (View) v.getParent();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.hudable);
        layout.setScaleY(-layout.getScaleY());
        scaleY_value -= scaleY_value;
    }
}
