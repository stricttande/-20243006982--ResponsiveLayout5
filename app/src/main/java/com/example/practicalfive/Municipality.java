package com.example.practicalfive;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {
    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.MUNICIPALITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        String rawName = getIntent() == null
                ? null
                : getIntent().getStringExtra(EXTRA_MUNICIPALITY);
        String municipalityName = TextProcessor.processMunicipality(rawName);
        if (municipalityName == null) {
            Toast.makeText(this, R.string.invalid_municipality_message, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView nameView = findViewById(R.id.municipalityName);
        nameView.setText(municipalityName);
    }
}
