package com.cs2340.team6.totallyratticalapp;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

/**
 * This activity is loaded when the user presses the login button on the welcome screen or
 * after a user registers
 *
 * @author Group 6
 * @version 1.0
 * @since whenever
 *
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etPassword;
    private EditText etUsername;
    private static ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }

    /**
     * This method is called when the cancel button is pressed on this screen.
     * It clears out the EditText objects and switches to the WelcomeActivity
     *
     * @param v A View
     */
    public void onCancelPressed(View v){
        etPassword.setText("");
        etUsername.setText("");
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * This method is called when the register button is pressed on this screen.
     * It clears out the EditText objects, and starts the RegisterActivity class
     *
     * @param v A View
     */
    public void onRegisterPressed(View v) {
        etPassword.setText("");
        etUsername.setText("");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * This method is called when the login button is pressed on this screen.
     * It checks if the login details are valid, starts AppActivity if so, shows
     * warning if not.
     *
     * @param v A View
     */
    public void onLoginPressed(View v) {
        if (LoginActivity.userExists(etUsername.getText().toString(), etPassword.getText().toString())) {
            AppActivity.setCurrentUser(findUser(etUsername.getText().toString()));
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("I ain't know you. \r\nWrong Username or Password.");
            AlertDialog dialogue = builder.create();
            dialogue.show();
        }
    }

    @Override
    public void onBackPressed() {

    }

    /**
     * Adds a new user to the User ArrayList which functions as a user database
     * for this application.
     *
     * @param user The User to be added
     */
    public static void addUser (User user) {
        users.add(user);
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
    private User findUser (String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
