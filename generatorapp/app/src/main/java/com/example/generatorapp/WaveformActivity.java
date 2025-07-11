package com.example.generatorapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.ArrayList;
import androidx.core.content.ContextCompat;

public class WaveformActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waveform_display);

        // Safely get values from intent
        String currentAStr = getIntent().getStringExtra("currentA");
        String currentBStr = getIntent().getStringExtra("currentB");
        String currentCStr = getIntent().getStringExtra("currentC");

        String freqStr = getIntent().getStringExtra("frequency");
        float frequency = parseFloatOrDefault(freqStr, 60);

        float ampA = parseFloatOrDefault(currentAStr, 0f);
        float ampB = parseFloatOrDefault(currentBStr, 0f);
        float ampC = parseFloatOrDefault(currentCStr, 0f);

        // Update UI
        TextView tvCurrentA = findViewById(R.id.tvCurrentA);
        TextView tvCurrentB = findViewById(R.id.tvCurrentB);
        TextView tvCurrentC = findViewById(R.id.tvCurrentC);

        TextView tvFrequency = findViewById(R.id.tvFrequency);
        tvFrequency.setText("Frequency: " + frequency + " Hz");

        tvCurrentA.setText("Current A: " + ampA + " A");
        tvCurrentB.setText("Current B: " + ampB + " A");
        tvCurrentC.setText("Current C: " + ampC + " A");

        // Plot sine waves
        LineChart lineChart = findViewById(R.id.lineChart);
        ArrayList<Entry> entriesA = new ArrayList<>();
        ArrayList<Entry> entriesB = new ArrayList<>();
        ArrayList<Entry> entriesC = new ArrayList<>();

        int points = 360;
        float period = 1 / frequency; // in seconds
        for (int i = 0; i <= points; i++)
        {
            float time = (float) i / points * period; // time in seconds
            float angle = 2 * (float) Math.PI * frequency * time;
            entriesA.add(new Entry(time, (float) (ampA * Math.sin(angle))));
            entriesB.add(new Entry(time, (float) (ampB * Math.sin(angle - 2 * Math.PI / 3))));
            entriesC.add(new Entry(time, (float) (ampC * Math.sin(angle + 2 * Math.PI / 3))));
        }

        LineDataSet dataSetA = new LineDataSet(entriesA, "Phase A");
        LineDataSet dataSetB = new LineDataSet(entriesB, "Phase B");
        LineDataSet dataSetC = new LineDataSet(entriesC, "Phase C");

        dataSetA.setColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        dataSetB.setColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        dataSetC.setColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));

        // Gives waveforms better shape and colour
        dataSetA.setLineWidth(2f);
        dataSetA.setDrawCircles(false);
        dataSetA.setDrawValues(false);

        dataSetB.setLineWidth(2f);
        dataSetB.setDrawCircles(false);
        dataSetB.setDrawValues(false);

        dataSetC.setLineWidth(2f);
        dataSetC.setDrawCircles(false);
        dataSetC.setDrawValues(false);

        LineData lineData = new LineData(dataSetA, dataSetB, dataSetC);
        lineChart.setData(lineData);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter()
        {
            @Override
            public String getFormattedValue(float value)
            {
                return String.format("%.4f s", value);
            }
        });

        xAxis.setGranularity(0.001f);
        xAxis.setGranularityEnabled(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.invalidate();
    }

    private float parseFloatOrDefault(String value, float fallback)
    {
        try
        {
            return (value != null && !value.trim().isEmpty()) ? Float.parseFloat(value.trim()) : fallback;
        }
        catch (Exception e)
        {
            return fallback;
        }
    }
}
