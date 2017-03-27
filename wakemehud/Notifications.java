package com.example.anthagonas.wakemehud;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by anthagonas on 27/03/17.
 */

public class Notifications extends MainActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) { // Activite secondaire contenant un text TEST TODO : Afficher les notifications
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        TextView text = (TextView) findViewById(R.id.MainText); // TODO : partie a remplacer par le flux de notifications
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.hudable);
        layout.setScaleY(1);
    }
}
