package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.res.ColorStateList;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {
    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.MUNICIPALITY";

    private static final class MunicipalityContent {
        final int summaryIndex;
        final int imageResource;
        final int colorResource;
        final int foregroundColorResource;

        MunicipalityContent(
                int summaryIndex,
                int imageResource,
                int colorResource,
                int foregroundColorResource
        ) {
            this.summaryIndex = summaryIndex;
            this.imageResource = imageResource;
            this.colorResource = colorResource;
            this.foregroundColorResource = foregroundColorResource;
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
        Button visitSiteButton = findViewById(R.id.visitSiteButton);
        int municipalityColor = ContextCompat.getColor(this, content.colorResource);
        int foregroundColor = ContextCompat.getColor(this, content.foregroundColorResource);

        nameView.setText(municipalityName);
        nameView.setBackgroundColor(municipalityColor);
        nameView.setTextColor(foregroundColor);
        imageView.setImageResource(content.imageResource);
        imageView.setContentDescription(
                getString(R.string.city_image_description_named, municipalityName)
        );
        informationView.setText(summaries[content.summaryIndex]);
        informationView.setTextColor(municipalityColor);
        visitSiteButton.setBackgroundTintList(ColorStateList.valueOf(municipalityColor));
        visitSiteButton.setTextColor(foregroundColor);
        visitSiteButton.setOnClickListener(view -> openOfficialWebsite(municipalityName));
    }

    private MunicipalityContent getContentFor(String municipalityName) {
        switch (municipalityName) {
            case "Beijing":
                return new MunicipalityContent(
                        0, R.drawable.beijing, R.color.municipality_beijing, R.color.color_text_light
                );
            case "Shanghai":
                return new MunicipalityContent(
                        1, R.drawable.shanghai, R.color.municipality_shanghai, R.color.color_text_light
                );
            case "Chongqing":
                return new MunicipalityContent(
                        2, R.drawable.chongqing, R.color.municipality_chongqing, R.color.color_text_dark
                );
            case "Tianjin":
                return new MunicipalityContent(
                        3, R.drawable.tianjin, R.color.municipality_tianjin, R.color.color_text_light
                );
            default:
                return null;
        }
    }

    private void closeForInvalidMunicipality() {
        Toast.makeText(this, R.string.invalid_municipality_message, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void openOfficialWebsite(String municipalityName) {
        String websiteUrl;
        switch (municipalityName) {
            case "Beijing":
                websiteUrl = "https://english.beijing.gov.cn/";
                break;
            case "Shanghai":
                websiteUrl = "https://english.shanghai.gov.cn/";
                break;
            case "Chongqing":
                websiteUrl = "https://english.cq.gov.cn/";
                break;
            case "Tianjin":
                websiteUrl = "https://en.tj.gov.cn/";
                break;
            default:
                closeForInvalidMunicipality();
                return;
        }

        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        try {
            startActivity(websiteIntent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, R.string.website_unavailable, Toast.LENGTH_SHORT).show();
        }
    }
}
