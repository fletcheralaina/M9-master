package com.cs2340.team6.totallyratticalapp;

/**
 * This class holds the ListView for the sightings, as well
 * as well as the selected rat from the listview
 * @author Dylan Bulfin
 * @version 1.0
 * @since whenever
 */

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScrollingActivity extends ListActivity {
    private static RatSighting selectedRat = null;

    /**
     * This class holds the currently selected rat from the ListView,
     * which allows the app to display the information
     * for the correct rat, this method is the getter for that
     *
     * @return selected rat
     */

    public static RatSighting getSelectedRat() {return selectedRat;}

    /**
     * Sets the selected rat
     *
     * @param r rat that should be selected
     */
    public static void setSelectedRat(RatSighting r) {
        selectedRat = r;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        ListView list = (ListView)findViewById(android.R.id.list);
        ArrayAdapter<RatSighting> adapter = new ArrayAdapter<RatSighting>(this, android.R.layout.simple_list_item_1, CSVReader.getReports());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                selectedRat = (RatSighting) adapter.getItemAtPosition(position);
                Intent intent = new Intent(v.getContext(), InformationScreen.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AppActivity.class);
        startActivity(intent);
    }
}
