package com.example.practicalfive;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {
    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.MUNICIPALITY";

    private static final class MunicipalityContent {
        final int summaryIndex;
        final int imageResource;

        MunicipalityContent(int summaryIndex, int imageResource) {
            this.summaryIndex = summaryIndex;
            this.imageResource = imageResource;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        String rawName = getIntent() == null
                ? null
                : getIntent().getStringExtra(EXTRA_MUNICIPALITY);
        String municipalityName = TextProcessor.processMunicipality(rawName);
        if (municipalityName == null) {
            closeForInvalidMunicipality();
            return;
        }

        MunicipalityContent content = getContentFor(municipalityName);
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        if (content == null || content.summaryIndex >= summaries.length) {
            closeForInvalidMunicipality();
            return;
        }

        TextView nameView = findViewById(R.id.municipalityName);
        ImageView imageView = findViewById(R.id.municipalityImage);
        TextView informationView = findViewById(R.id.informationText);

        nameView.setText(municipalityName);
        imageView.setImageResource(content.imageResource);
        imageView.setContentDescription(
                getString(R.string.city_image_description_named, municipalityName)
        );
        informationView.setText(summaries[content.summaryIndex]);
    }

    private MunicipalityContent getContentFor(String municipalityName) {
        switch (municipalityName) {
            case "Beijing":
                return new MunicipalityContent(0, R.drawable.beijing);
            case "Shanghai":
                return new MunicipalityContent(1, R.drawable.shanghai);
            case "Chongqing":
                return new MunicipalityContent(2, R.drawable.chongqing);
            case "Tianjin":
                return new MunicipalityContent(3, R.drawable.tianjin);
            default:
                return null;
        }
    }

    private void closeForInvalidMunicipality() {
        Toast.makeText(this, R.string.invalid_municipality_message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
