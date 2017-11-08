package com.cs2340.team6.totallyratticalapp;

/**
 * This screen is displayed after a rat sighting
 * is clicked on in the ListView. It displays the required information
 * for this milestone
 *
 * @author Dylan Bulfin
 * @version 1.0
 * @since whenever
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InformationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_screen);

        TextView text = (TextView) findViewById(R.id.InfoText);
        text.setText(ScrollingActivity.getSelectedRat().getInfo());
    }
}
