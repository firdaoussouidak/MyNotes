package com.example.mynotes.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.R;

public class DetailsNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        ((TextView) findViewById(R.id.txtNom))
                .setText(getIntent().getStringExtra("nom"));
        ((TextView) findViewById(R.id.txtDesc))
                .setText(getIntent().getStringExtra("desc"));
        ((TextView) findViewById(R.id.txtDate))
                .setText(getIntent().getStringExtra("date"));
        ((TextView) findViewById(R.id.txtPrio))
                .setText(getIntent().getStringExtra("prio"));

        Button btnRetour = findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(v -> finish());
    }
}
