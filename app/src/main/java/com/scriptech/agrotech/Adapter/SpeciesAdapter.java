package com.scriptech.agrotech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptech.agrotech.Model.speciesModel;
import com.scriptech.agrotech.R;

import java.util.ArrayList;

public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.Viewholder> {

    Context context;
    ArrayList<speciesModel> speciesModelArrayList;

    public SpeciesAdapter(Context context, ArrayList speciesModelArrayList) {
        this.context = context;
        this.speciesModelArrayList = speciesModelArrayList;
    }

    @NonNull
    @Override
    public SpeciesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recycler_species,parent,false);

        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesAdapter.Viewholder holder, int position) {

      speciesModel speciesModel = speciesModelArrayList.get(position);

      holder.txtSpecies.setText(speciesModel.getSpeciesName());


    }

    @Override
    public int getItemCount() {
        return speciesModelArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView txtSpecies;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txtSpecies = itemView.findViewById(R.id.txtSpecies);
        }
    }
}
