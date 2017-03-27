package com.example.anthagonas.wakemehud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextClock;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextClock digital = (TextClock) findViewById(R.id.digital_clock);
    }
    public void mirror(View v)
    {
        View parent = (View) v.getParent();
        parent.setScaleY(-parent.getScaleY());
        /* NE MARCHE QUE POUR LE TEXTCLOCK, TENTATIVE POUR TOUTES LES ACTIVITES AU DESSUS
        View parent = (View) v.getParent();
        if (parent != null)
        {
            TextClock txtClock = (TextClock) parent.findViewById(R.id.digital_clock);
            txtClock.setScaleY(-txtClock.getScaleY());
        }
        */
    }
    public void changeActivity(View v)
    {
        Intent TaskIntent = new Intent(this,Notifications.class);
        startActivity(TaskIntent);
    }

    public class ButtonListener implements android.view.View.OnClickListener
    {
        public void onClick(View v)
        {
            changeActivity(v);
            mirror(v);
        }
    }
}