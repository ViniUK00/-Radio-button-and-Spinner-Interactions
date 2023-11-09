package com.example.radio_button_spinner_interaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Spinner citySpinner;
    private TextView selectedChoicesTextView;
    private String selectedCountry = "";
    private String selectedCity = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        citySpinner = findViewById(R.id.citySpinner);
        selectedChoicesTextView = findViewById(R.id.selectedChoicesTextView);
        Button submitButton = findViewById(R.id.submitButton);

        // Define arrays for countries and their corresponding cities
        final String[] countries = {"Spain", "Greece", "Albania"};
        final String[][] cities = {
                {"Barcelona", "Madrid", "Malaga"},
                {"Athens", "Larissa", "Thessaloniki"},
                {"Tirana", "Sarande", "Permet"}
        };

        // Populate the Spinner initially with no cities
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        citySpinner.setAdapter(spinnerAdapter);

        // Set an OnCheckedChangeListener for the RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);

                int countryIndex = radioGroup.indexOfChild(radioButton);

                // Populate the Spinner with cities for the selected country
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, cities[countryIndex]);
                citySpinner.setAdapter(spinnerAdapter);

                // Store the selected country
                selectedCountry = countries[countryIndex];
            }
        });

        // Set an OnItemSelectedListener for the Spinner
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Store the selected city
                selectedCity = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCity = " ";
            }
        });

        // Set an OnClickListener for the Submit Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the selected choices in the TextView
                String selectedChoices = "Selected Country: " + selectedCountry + "\nSelected City: " + selectedCity;
                selectedChoicesTextView.setText(selectedChoices);
            }
        });
    }
}
