package com.example.mobilecw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends Activity {
    static final String EMPTY_REGEX = "^$";

    private EditText propertyType, bedrooms, date, rentPrice, furnitureTypes, reporterName, notes;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        propertyType = findViewById(R.id.txt_input_property_type);
        bedrooms = findViewById(R.id.txt_input_bedrooms);
        date = findViewById(R.id.txt_input_date);
        rentPrice = findViewById(R.id.txt_input_monthly_rent_price);
        furnitureTypes = findViewById(R.id.txt_input_furniture_types);
        reporterName = findViewById(R.id.txt_input_reporter_name);
        notes = findViewById(R.id.txt_edit_note);

        submit = findViewById(R.id.btn_submit);

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
            i.putExtra("furniture types", furnitureTypes.getText().toString().trim());
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
        furnitureTypes.setText(fType);
        reporterName.setText(rName);
        notes.setText(note);
    }
}