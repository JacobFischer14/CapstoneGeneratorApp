package com.example.generatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GeneratorAdapter adapter;
    private GeneratorDAO dao;
    private Button btnAdd, btnRefresh;
    private Generator selectedGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new GeneratorDAO(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnRefresh = findViewById(R.id.btnRefresh);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> startActivity(new Intent(this, AddGeneratorActivity.class)));

        btnRefresh.setOnClickListener(v -> {
            // Simulate refresh: reload from DB
            loadGenerators();

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadGenerators();
    }

    private void loadGenerators() {
        List<Generator> list = dao.getAll();
        adapter = new GeneratorAdapter(this, list, generator -> selectedGenerator = generator);
        recyclerView.setAdapter(adapter);
    }
}
