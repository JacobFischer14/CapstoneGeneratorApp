package com.example.generatorapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GeneratorAdapter extends RecyclerView.Adapter<GeneratorAdapter.ViewHolder>
{
    private List<Generator> generatorList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(Generator generator);
    }

    public GeneratorAdapter(Context context, List<Generator> generatorList, OnItemClickListener listener)
    {
        this.context = context;
        this.generatorList = generatorList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvModel, tvCurrents;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tvModel = itemView.findViewById(R.id.tvModel);
            tvCurrents = itemView.findViewById(R.id.tvCurrents);
        }

        public void bind(Generator generator, OnItemClickListener listener)
        {
            itemView.setOnClickListener(v -> listener.onItemClick(generator));
        }
    }

    @Override
    public GeneratorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.generator_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Generator g = generatorList.get(position);
        holder.tvModel.setText("Model: " + g.getModelNumber());
        holder.tvCurrents.setText("Currents: " + g.getCurrentA() + " | " + g.getCurrentB() + " | " + g.getCurrentC());
        holder.itemView.setOnClickListener(v -> {
                listener.onItemClick(g);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Action")
                        .setItems(new String[]{"Edit/Delete Generator", "View Waveforms"}, (dialog, which) -> {
                            if (which == 0)
                            {
                                context.startActivity(new Intent(context, EditGeneratorActivity.class)
                                        .putExtra("generator_id", g.getId()));
                            }
                        })
                        .show();
                });

        holder.itemView.setOnClickListener(v ->
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Choose Action")
                    .setItems(new String[]{"Edit/Delete Generator", "View Waveforms"}, (dialog, which) -> {
                        if (which == 0)
                        {
                            Intent intent = new Intent(context, EditGeneratorActivity.class);
                            intent.putExtra("generator_id", g.getId());
                            context.startActivity(intent);
                        }
                        else if (which == 1)
                        {
                            Intent intent = new Intent(context, WaveformActivity.class);
                            try
                            {
                                intent.putExtra("currentA", g.getCurrentA());
                                intent.putExtra("currentB", g.getCurrentB());
                                intent.putExtra("currentC", g.getCurrentC());
                                intent.putExtra("frequency", String.valueOf(g.getFrequency()));
                            }
                            catch (NumberFormatException e)
                            {
                                intent.putExtra("currentA", 1f);
                                intent.putExtra("currentB", 1f);
                                intent.putExtra("currentC", 1f);
                            }
                            context.startActivity(intent);
                        }
                    })
                    .show();
        });
    }
    @Override
    public int getItemCount()
    {
        return generatorList.size();
    }
}