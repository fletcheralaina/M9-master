package com.cs2340.team6.totallyratticalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Map;

public class SetDateActivity extends AppCompatActivity {

    Spinner stDaySpinner;
    Spinner stMonthSpinner;
    Spinner stYearSpinner;
    Spinner eDaySpinner;
    Spinner eMonthSpinner;
    Spinner eYearSpinner;

    static int[] dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);

        stDaySpinner = (Spinner) findViewById(R.id.stDaySpinner);
        stMonthSpinner = (Spinner) findViewById(R.id.stMonthSpinner);
        stYearSpinner = (Spinner) findViewById(R.id.stYearSpinner);
        eDaySpinner = (Spinner) findViewById(R.id.eDaySpinner);
        eMonthSpinner = (Spinner) findViewById(R.id.eMonthSpinner);
        eYearSpinner = (Spinner) findViewById(R.id.eYearSpinner);


        ArrayList<Integer> days = new ArrayList<Integer>();
        for (int i = 1; i <= 31; i++) {
            days.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stDaySpinner.setAdapter(adapter);
        eDaySpinner.setAdapter(adapter);

        ArrayList<Integer> months = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++) {
            months.add(i);
        }

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stMonthSpinner.setAdapter(adapter2);
        eMonthSpinner.setAdapter(adapter2);

        ArrayList<Integer> years = new ArrayList<Integer>();
        for (int i = 1950; i <= 2050; i++) {
            years.add(i);
        }

        ArrayAdapter<Integer> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, years);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stYearSpinner.setAdapter(adapter3);
        eYearSpinner.setAdapter(adapter3);
    }

    /**
     * Add dates into array, called when set dates button is pressed
     *
     * @param view a View
     */
    public void onSetDatePressed(View view) {
        dates = new int[6];
        dates[0] = (Integer)stDaySpinner.getSelectedItem();
        dates[1] = (Integer)stMonthSpinner.getSelectedItem();
        dates[2] = (Integer)stYearSpinner.getSelectedItem();
        dates[3] = (Integer)eDaySpinner.getSelectedItem();
        dates[4] = (Integer)eMonthSpinner.getSelectedItem();
        dates[5] = (Integer)eYearSpinner.getSelectedItem();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * getter for dates
     *
     * @return dates
     */
    public static int[] getDates() {
        return dates;
    }

}
