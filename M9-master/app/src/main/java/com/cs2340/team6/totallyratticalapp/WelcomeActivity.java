package com.cs2340.team6.totallyratticalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.InputStream;

/**
 * This activity is loaded when the app starts up
 *
 * @author Group 6
 * @version 1.0
 * @since whenever
 *
 */

public class WelcomeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        InputStream file = getResources().openRawResource(R.raw.rat_sightings);
        CSVReader.parseFile(file);
    }

    /**
     * Starts LoginActivity
     *
     * @param v a View
     */
    public void onWelcomeLoginPressed (View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Starts RegisterActivity
     *
     * @param v a View
     */
    public void onWelcomeRegisterPressed (View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
