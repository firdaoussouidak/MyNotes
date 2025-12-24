package com.example.mynotes.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mynotes.R;

public class AddNoteActivity extends AppCompatActivity {

    EditText edtNom, edtDesc, edtDate;
    Spinner spinner;
    ImageView imgPhoto;
    Uri imageUri;

    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edtNom = findViewById(R.id.edtNom);
        edtDesc = findViewById(R.id.edtDesc);
        edtDate = findViewById(R.id.edtDate);
        spinner = findViewById(R.id.spinnerPriorite);
        imgPhoto = findViewById(R.id.imgPhoto);

        Button btnCamera = findViewById(R.id.btnCamera);
        Button btnGallery = findViewById(R.id.btnGallery);
        Button btnSave = findViewById(R.id.btnSave);

        spinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Basse", "Moyenne", "Haute"}
        ));

        // 📷 Camera result
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && imageUri != null) {
                        imgPhoto.setImageURI(imageUri);
                    }
                }
        );

        // 🖼️ Gallery result
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        imgPhoto.setImageURI(imageUri);
                    }
                }
        );

        btnCamera.setOnClickListener(v -> openCamera());
        btnGallery.setOnClickListener(v -> openGallery());

        btnSave.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("nom", edtNom.getText().toString());
            data.putExtra("desc", edtDesc.getText().toString());
            data.putExtra("date", edtDate.getText().toString());
            data.putExtra("prio", spinner.getSelectedItem().toString());

            if (imageUri != null) {
                data.putExtra("imageUri", imageUri.toString());
            }

            setResult(RESULT_OK, data);
            finish();
        });
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
            return;
        }

        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new android.content.ContentValues()
        );

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraLauncher.launch(intent);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
}
