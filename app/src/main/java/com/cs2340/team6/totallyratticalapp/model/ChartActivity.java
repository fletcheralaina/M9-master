package com.cs2340.team6.totallyratticalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cs2340.team6.totallyratticalapp.SetDateActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends AppCompatActivity {

    //private DataSetExample data = new DataSetExample();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chart);
//
//        LineChart lineChart = (LineChart) findViewById(R.id.chart);
//
//        //List<Entry> entries = convertDataSetToEntry(markers.getDataList());
//        ArrayList<RatSighting> entries = CSVReader.getReports();
//
//        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
//
//        Log.d("APP", "Made dataset with : " + entries.size());
//
//        LineData data = new LineData(dataset);
//        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
//
//        dataset.setDrawFilled(true);
//
//        lineChart.setData(data);
//        lineChart.animateY(5000);
//
//        lineChart.getDescription().setText("Average Calls per Month");
//
//    }
//
//    private List<Entry> convertDataSetToEntry(List<Data> data) {
//        List<Entry> entries = new ArrayList<>();
//
//        for (Data d : data) {
//            entries.add(new Entry(d.x, d.y));
//        }
//
//        return entries;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        LineChart chart = (LineChart) findViewById(R.id.chart); //code from demo code

        ArrayList<RatSighting> markers = CSVReader.getReports(); //from MapsActivity, initializes an array from the csv

        List<Entry> entries = new ArrayList<Entry>(); //creates a new array based on what will be on the graph

        for (RatSighting r : markers) {
            entries.add(new Entry(SetDateActivity.getDates(), getCount(r)));
            //so what we're going to do is use the setDateActivity to get the date range, then get the total number of rat sightings on that date
            //sightings will not be broken down by day but by month and year
            //working off of set_date
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset (from dem code)
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);  //from demo code
        dataSet.setDrawFilled(true); //from demo code

        LineData lineData = new LineData(dataSet); //from demo code
        chart.setData(lineData); // from demo code
        chart.invalidate(); // refresh (from demo code)

    }

    //creates an int from based on the number of rat sightings that fall within the range
    private int getCount(RatSighting r){
        int count = 0;
        for (RatSighting r : markers) {
            if (onDate(r)){
                count ++;
            }
        }
        return count;
    }
    /**
     * checks if date of given rat object fits with given parameters
     *
     * @param r rat sighting
     * @return sightingsThatDay
     */
    private boolean onDate(RatSighting r) {
        int[] dates = SetDateActivity.getDates();
        if (dates == null) {return true;}
        String date = r.getDate();
        String[]dateArray = date.split("/");
        if (dateArray.length < 3) return false;
        int month = Integer.valueOf(dateArray[0]);
        int day = Integer.valueOf(dateArray[1]);
        int year = Integer.valueOf(dateArray[2]);
        if (year < dates[2] || year > dates[5]) return false;
        if (year == dates[2]) {
            if (month < dates[1] || (month == dates[1] && day < dates[0])) return false;
            return true;
        }
        if (year == dates[5]) {
            if (month > dates[4] || (month == dates[4] && day > dates[3])) return false;
            return true;
        }
        return true;
    }

}
