package com.cs2340.team6.totallyratticalapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

/**
 * This runs at the app startup, and it parses the CSV file
 * into a more usable format for later display
 *
 * @author Safa Quadri & Dylan Bulfin
 * @version 1.0
 * @since whenever
 */

public class CSVReader {

    private static ArrayList<RatSighting> reports;

    /**
     * Getter for list of Rat Sightings
     *
     * @return list of rat sightings
     */
    public static ArrayList<RatSighting> getReports() {
        return reports;
    }

    /**
     * Adds new sighting to front of List, so it shows up first
     *
     * @param rat The rat to be added
     */
    public static void addSighting(RatSighting rat) {
        reports.add(0, rat);
    }

    /**
     * This method takes in the file's input
     * and splits it up into an array of strings (around commas)
     * creates RatSighting objects from that, and stores them into a list
     *
     * @param file The CSV file's input stream
     */
    public static void parseFile(InputStream file) {
        reports = new ArrayList<RatSighting>();
        try {
            InputStreamReader fileReader = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null) {
                reports.add(new RatSighting(line.split(",")));
            }
        }
        catch(Exception e) {e.printStackTrace();}
    }

}
