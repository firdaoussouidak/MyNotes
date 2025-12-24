package com.example.mynotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.R;
import com.example.mynotes.adapter.NoteAdapter;
import com.example.mynotes.model.Note;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    ArrayList<Note> notes;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        ListView listView = findViewById(R.id.listViewNotes);
        Button btnAdd = findViewById(R.id.btnAdd);

        notes = new ArrayList<>();
        adapter = new NoteAdapter(this, notes);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivityForResult(intent, 1);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Note note = notes.get(position);

            Intent intent = new Intent(this, DetailsNoteActivity.class);
            intent.putExtra("nom", note.getNom());
            intent.putExtra("desc", note.getDescription());
            intent.putExtra("date", note.getDate());
            intent.putExtra("prio", note.getPriorite());
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            notes.add(new Note(
                    data.getStringExtra("nom"),
                    data.getStringExtra("desc"),
                    data.getStringExtra("date"),
                    data.getStringExtra("prio")
            ));
            adapter.notifyDataSetChanged();
        }
    }
}
