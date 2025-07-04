package com.example.generatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddGeneratorActivity extends AppCompatActivity {
    private EditText etModel, etCurrentA, etCurrentB, etCurrentC, etFrequency;
    private Button btnSave;
    private GeneratorDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_generator);

        dao = new GeneratorDAO(this);

        etModel = findViewById(R.id.etModel);
        etCurrentA = findViewById(R.id.etCurrentA);
        etCurrentB = findViewById(R.id.etCurrentB);
        etCurrentC = findViewById(R.id.etCurrentC);
        EditText etFrequency = findViewById(R.id.etFrequency);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            float etfrequency = Float.parseFloat(etFrequency.getText().toString().trim());
            Generator g = new Generator(0,
                    etModel.getText().toString(),
                    etCurrentA.getText().toString(),
                    etCurrentB.getText().toString(),
                    etCurrentC.getText().toString(),
                    etfrequency);

            dao.insert(g);
            finish();
        });
    }
}

