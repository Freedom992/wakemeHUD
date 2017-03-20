package com.anthagonas.fr.justatest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        if (parent != null)
        {
            TextClock txtClock = (TextClock) parent.findViewById(R.id.digital_clock);
            txtClock.setScaleY(-txtClock.getScaleY());
        }
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public class ButtonListener implements android.view.View.OnClickListener
    {
        public void onClick(View v)
        {
            mirror(v);
        }
    }
}