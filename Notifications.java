package com.example.anthagonas.wakemehud;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by anthagonas on 27/03/17.
 */

public class Notifications extends MainActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        TextView text = (TextView) findViewById(R.id.MainText);

    }
    public class ButtonListener implements android.view.View.OnClickListener
    {
        public void onClick(View v)
        {
            mirror(v);
        }
    }
}
