package com.cs2340.team6.totallyratticalapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

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
    private static ArrayList<User> users = new ArrayList<User>();

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
    public static void addSighting(RatSighting rat, Context context) {
        reports.add(0, rat);

        String filename = "ratinfo.json";
        String string = rat.writeableString();
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method takes in the file's input
     * and splits it up into an array of strings (around commas)
     * creates RatSighting objects from that, and stores them into a list
     * Also puts into the list the user-added sightings
     *
     * @param file The CSV file's input stream
     */
    public static void parseFile(InputStream file, Context context) {
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

        try {
            InputStream inputStream = context.openFileInput("ratinfo.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String s;
                while ( (s = bufferedReader.readLine()) != null ) {
                    String[] ssplit = s.split(",");
                    if (ssplit.length >= 9) reports.add(0,new RatSighting(ssplit[0], ssplit[1], ssplit[2], ssplit[3], ssplit[4], ssplit[5], ssplit[6], ssplit[7], ssplit[8]));
                }

            }
        }
        catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    /**
     * Adds a new user to the User ArrayList which functions as a user database
     * for this application, also writes user to user info storage file
     *
     * @param user The User to be added
     */
    public static void addUser (User user, Context context) {

        users.add(user);
        String filename = "defnotuserinfo.exe";
        String string = user.getName()+","+user.getUsername()+","+user.getPassword()+","+((user.getRole().toString().equals("Admin")) ? "A" : "U")+"\n";
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads user info from CSV file and puts it into instance data
     *
     * @param context calling activity passes this in so it's easier to do stuff
     */
    public static void readUserInfo(Context context) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput("defnotuserinfo.exe");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String s;
                while ( (s = bufferedReader.readLine()) != null ) {
                    String[] ssplit = s.split(",");
                    if (ssplit.length >= 4) users.add(new User(ssplit[0],ssplit[1], ssplit[2], ((ssplit.equals("A")) ? User.Role.ADMIN: User.Role.USER)));
                }

            }
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
    }

    /**
     * Checks if a user exists with this username and password
     *
     * @param username the username to check
     * @param password the password to check
     * @return true if such a user exists, false else
     */
    public static boolean userExists (String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if username is already taken
     *
     * @param username the username to check
     * @return false if taken, true otherwise
     */
    public static boolean usernameAvailable(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the User ArrayList which functions as a database of users for this
     * application
     *
     * @return the aforementioned ArrayList
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Finds the user with the given username
     *
     * @param username the username to check
     * @return the unique User object with that name or null if it doesn't exist
     */
    public static User findUser (String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

}
