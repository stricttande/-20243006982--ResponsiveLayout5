package com.example.practicalfive;

import android.os.Bundle;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText municipalityInput = findViewById(R.id.municipalityInput);
        municipalityInput.setOnEditorActionListener((view, actionId, event) -> {
            boolean imeAction = actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_DONE;
            boolean enterKey = event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
            if (imeAction || enterKey) {
                if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
                    openMunicipality(municipalityInput);
                }
                return true;
            }
            return false;
        });
    }

    private void openMunicipality(EditText municipalityInput) {
        String normalizedName = TextProcessor.processMunicipality(
                municipalityInput.getText().toString()
        );
        if (normalizedName == null) {
            municipalityInput.setError(getString(R.string.input_error));
            municipalityInput.requestFocus();
            return;
        }

        municipalityInput.setError(null);
        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(Municipality.EXTRA_MUNICIPALITY, normalizedName);
        startActivity(intent);
    }
}
