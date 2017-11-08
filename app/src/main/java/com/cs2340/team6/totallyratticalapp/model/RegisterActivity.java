package com.cs2340.team6.totallyratticalapp;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Spinner;

/**
 * This activity is loaded when a user presses the register button on the welcome or login
 * screen
 *
 * @author Group 6
 * @version 1.0
 * @since whenever
 *
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etUsername;
    private EditText etPassword;
    private Spinner roleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        roleSpinner = (Spinner) findViewById(R.id.roleSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.Role.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);
    }

    /**
     * Method is called when Cancel button is pressed, and starts the WelcomeActivity
     *
     * @param view a View
     */
    public void onCancel2Pressed(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * Called when register button is pressed.
     * Checks validity of registration, adds user if valid, shows warning otherwise
     *
     * @param view a View
     */
    public void onRegisterPressed(View view){
        if (etName.getText().toString().equals("") || etUsername.getText().toString().equals("")
                || etPassword.getText().toString().equals("") ||
                !CSVReader.usernameAvailable(etUsername.getText().toString())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("One or more fields empty or username is taken. Try again.");
            AlertDialog dialogue = builder.create();
            dialogue.show();
        }
        else {
            CSVReader.addUser(new User (etName.getText().toString(), etUsername.getText().toString(),
                    etPassword.getText().toString(), (User.Role) roleSpinner.getSelectedItem()), this);

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
