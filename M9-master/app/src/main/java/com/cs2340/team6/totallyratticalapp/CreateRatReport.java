package com.cs2340.team6.totallyratticalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateRatReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selected;

    private List<String> locationType;
    private List<String> borough;

    private Spinner locationSpinner;
    private Spinner boroughSpinner;
    private TextView text;

    private EditText zip;
    private EditText address;
    private EditText city;
    private EditText latitude;
    private EditText longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rat_report);

        locationType = Arrays.asList("1-2 Family Dwelling",
                "3+ Family Apartment Building", "3+ Family Mixed Use Building",
                "Commercial Building", "Vacant Lot", "Construction Site", "Hospital", "Catch Basin/Sewer");

        borough = Arrays.asList("Manhattan",
                "Staten Island", "Queens",
                "Brooklyn", "Bronx");
        zip = (EditText) findViewById(R.id.zip);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);

        locationSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        boroughSpinner = (Spinner) findViewById(R.id.boroughSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, locationType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, borough);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(adapter2);
    }

    /**
     * Called when create button is pressed, creates a new Rat Report with data from fields
     *
     * @param v a view
     */
    public void onCreatePressed(View v) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        CSVReader.addSighting(new RatSighting(date, locationSpinner.getSelectedItem().toString(), zip.getText().toString(), address.getText().toString(), city.getText().toString(), boroughSpinner.getSelectedItem().toString(), latitude.getText().toString(), longitude.getText().toString()));
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selected = "NA";
    }

    /**
     * This is called when the person cancels the rat report
     * they were making (takes them back to the user's main page)
     *
     * @param v View
     *
     */
    public void onCancelReportPressed(View v) {
        Intent intent = new Intent(this, AppActivity.class);
        startActivity(intent);
    }
}
