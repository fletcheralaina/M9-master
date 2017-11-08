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
        if (CSVReader.userExists(etUsername.getText().toString(), etPassword.getText().toString())) {
            AppActivity.setCurrentUser(CSVReader.findUser(etUsername.getText().toString()));
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


}
