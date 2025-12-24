package com.example.mynotes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mynotes.R;
import com.example.mynotes.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, parent, false);
        }

        Note note = getItem(position);

        TextView txtNom = convertView.findViewById(R.id.txtNom);
        TextView txtDate = convertView.findViewById(R.id.txtDate);

        txtNom.setText(note.getNom());
        txtDate.setText(note.getDate());

        // Couleur selon priorité
        switch (note.getPriorite()) {
            case "Haute":
                convertView.setBackgroundColor(Color.parseColor("#FFCDD2"));
                break;
            case "Moyenne":
                convertView.setBackgroundColor(Color.parseColor("#FFF9C4"));
                break;
            default:
                convertView.setBackgroundColor(Color.parseColor("#C8E6C9"));
        }

        return convertView;
    }
}
