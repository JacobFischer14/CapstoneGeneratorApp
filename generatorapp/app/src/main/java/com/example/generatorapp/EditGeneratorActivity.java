package com.example.generatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditGeneratorActivity extends AppCompatActivity {
    private EditText etModel, etCurrentA, etCurrentB, etCurrentC;
    private Button btnUpdate, btnDelete;
    private GeneratorDAO dao;
    private Generator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_generator);  // Make sure this layout doesn't have waveform fields

        dao = new GeneratorDAO(this);

        etModel = findViewById(R.id.etModel);
        etCurrentA = findViewById(R.id.etCurrentA);
        etCurrentB = findViewById(R.id.etCurrentB);
        etCurrentC = findViewById(R.id.etCurrentC);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        int id = getIntent().getIntExtra("generator_id", -1);
        for (Generator g : dao.getAll()) {
            if (g.getId() == id) {
                generator = g;
                break;
            }
        }

        if (generator != null) {
            etModel.setText(generator.getModelNumber());
            etCurrentA.setText(generator.getCurrentA());
            etCurrentB.setText(generator.getCurrentB());
            etCurrentC.setText(generator.getCurrentC());
        }

        btnUpdate.setOnClickListener(v -> {
            generator.setModelNumber(etModel.getText().toString());
            generator.setCurrentA(etCurrentA.getText().toString());
            generator.setCurrentB(etCurrentB.getText().toString());
            generator.setCurrentC(etCurrentC.getText().toString());
            dao.update(generator);
            finish();
        });

        btnDelete.setOnClickListener(v -> {
            dao.delete(generator.getId());
            finish();
        });
    }
}

