package com.example.mobilecw;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    static final String EMPTY_REGEX = "^$";

    private EditText propertyType, bedrooms, date, rentPrice, reporterName, notes;
    private Button submit, setDate;
    private Spinner furniture;
    private DatePickerDialog datePickerDialog;
    private String furnitureTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initDatePicker();

        propertyType = findViewById(R.id.txtProp);
        bedrooms = findViewById(R.id.txtBed);
        date = findViewById(R.id.txtDateTime);
        rentPrice = findViewById(R.id.txtRentPrice);
        reporterName = findViewById(R.id.txtReporter);
        notes = findViewById(R.id.txtNotes);

        submit = findViewById(R.id.btnSave);
        setDate = findViewById(R.id.btnChooseDate);

        furniture = findViewById(R.id.furniture_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                HomeActivity.this,
                R.array.furniture_types,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        furniture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                furnitureTypes = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        furniture.setAdapter(adapter);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        submit.setOnClickListener(v -> {
            Boolean valid = true;
            String msg = "You must enter %s";

            if (propertyType.getText().toString().trim().matches(EMPTY_REGEX)) {
                propertyType.setError(String.format(msg, "Property type"));
                valid = false;
            }

            if (bedrooms.getText().toString().trim().matches(EMPTY_REGEX)) {
                bedrooms.setError(String.format(msg, "Bedrooms"));
                valid = false;
            }

            if (date.getText().toString().trim().matches(EMPTY_REGEX)) {
                date.setError(String.format(msg, "Date"));
                valid = false;
            }

            if (rentPrice.getText().toString().trim().matches(EMPTY_REGEX)) {
                rentPrice.setError(String.format(msg, "Monthly rent price"));
                valid = false;
            }

            if (reporterName.getText().toString().trim().matches(EMPTY_REGEX)) {
                reporterName.setError(String.format(msg, "Reporter name"));
                valid = false;
            }

            if (!valid) {
                Toast.makeText(getApplicationContext(), "You must enter all required field", Toast.LENGTH_LONG).show();
                return;
            }

            Intent i = new Intent(HomeActivity.this, DetailActivity.class);
            i.putExtra("property type", propertyType.getText().toString().trim());
            i.putExtra("bedrooms", bedrooms.getText().toString().trim());
            i.putExtra("date", date.getText().toString().trim());
            i.putExtra("rent price", rentPrice.getText().toString().trim());
            i.putExtra("furniture types", furnitureTypes);
            i.putExtra("reporter name", reporterName.getText().toString().trim());
            i.putExtra("notes", notes.getText().toString().trim());
            startActivityForResult(i, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String pType = data.getStringExtra("property type");
        String bed = data.getStringExtra("bedrooms");
        String dateTime = data.getStringExtra("date");
        String rentP = data.getStringExtra("rent price");
        String fType = data.getStringExtra("furniture types");
        String rName = data.getStringExtra("reporter name");
        String note = data.getStringExtra("notes");

        propertyType.setText(pType);
        bedrooms.setText(bed);
        date.setText(dateTime);
        rentPrice.setText(rentP);
        furnitureTypes = fType;
        reporterName.setText(rName);
        notes.setText(note);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String tmp = formatDate(year, month, day);

                date.setText(tmp);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                this,
                AlertDialog.THEME_HOLO_LIGHT,
                dateSetListener,
                year,
                month,
                day
                );
    }

    private String formatDate(int year, int month, int day) {
        return String.format("%s-%s-%s", year, month, day);
    }
}