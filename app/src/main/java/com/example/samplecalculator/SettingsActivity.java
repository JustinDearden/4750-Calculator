package com.example.samplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    /*
     * Variables for the preferences and switches
     * I made more switches available but didnt implement them
     * */
    SharedPreferences preferences;

    Switch switch_1, switch_2, switch_3;

    boolean stateSwitch1, stateSwitch2;

    private Button btnClose;

    /*
     * Creates the window when the screen loads
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /*
         * Listener for the close button
         * Closes the screen and finishes the intent - transitioning back to the main activity
         * */
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*
         * Sets the preference and mode and assigns a key value pair to the switch
         * This value is used to retrieve the data on the main activity
         * */
        preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        stateSwitch1 = preferences.getBoolean("switch1", false);
        //stateSwitch2 = preferences.getBoolean("switch2", false);

        switch_1 = (Switch) findViewById(R.id.switch_1);
        //switch_2 = (Switch) findViewById(R.id.switch_2);

//        Checking for the switch being checked - becomes a bool value
        switch_1.setChecked(stateSwitch1);
        //switch_2.setChecked(stateSwitch2);

        /*
         * Saves the value of the switch inside the preferences
         * Apply the value to commit the save
         * */
        switch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateSwitch1 = !stateSwitch1;
                switch_1.setChecked(stateSwitch1);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch1", stateSwitch1);
                editor.apply();
            }
        });

    }

}
