package com.example.mobilecw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends Activity {

    private TextView propertyType, bedrooms, date, rentPrice, furnitureTypes, reporterName, notes;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        propertyType = findViewById(R.id.txt_property);
        bedrooms = findViewById(R.id.txt_bedrooms);
        date = findViewById(R.id.txt_date);
        rentPrice = findViewById(R.id.txt_rent_price);
        furnitureTypes = findViewById(R.id.txt_furniture_types);
        reporterName = findViewById(R.id.txt_reporter_name);
        notes = findViewById(R.id.txt_notes);

        goBack = findViewById(R.id.btn_goback);

        // Get extras from prev activity
        Bundle extras = getIntent().getExtras();

        Intent i = new Intent();

        if (extras != null) {
            propertyType.setText(extras.getString("property type"));
            bedrooms.setText(extras.getString("bedrooms"));
            date.setText(extras.getString("date"));
            rentPrice.setText(extras.getString("rent price"));
            furnitureTypes.setText(extras.getString("furniture types"));
            reporterName.setText(extras.getString("reporter name"));
            notes.setText(extras.getString("notes"));

            i.putExtra("property type", extras.getString("property type"));
            i.putExtra("bedrooms", extras.getString("bedrooms"));
            i.putExtra("date", extras.getString("date"));
            i.putExtra("rent price", extras.getString("rent price"));
            i.putExtra("furniture types", extras.getString("furniture types"));
            i.putExtra("reporter name", extras.getString("reporter name"));
            i.putExtra("notes", extras.getString("notes"));
        }

        goBack.setOnClickListener(v -> {
            setResult(0, i);
            finish();
        });
    }
}