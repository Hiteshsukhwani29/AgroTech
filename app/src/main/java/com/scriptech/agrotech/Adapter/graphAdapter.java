package com.scriptech.agrotech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptech.agrotech.Model.graphModel;
import com.scriptech.agrotech.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class graphAdapter extends RecyclerView.Adapter<graphAdapter.Viewholder> {

    Context context;
    ArrayList<graphModel> graphModelArrayList;

    public graphAdapter(Context context, ArrayList<graphModel> graphModelArrayList) {
        this.context = context;
        this.graphModelArrayList = graphModelArrayList;
    }

    @NonNull
    @Override
    public graphAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recycler_graph,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull graphAdapter.Viewholder holder, int position) {

        graphModel graphModel = graphModelArrayList.get(position);

        holder.txt_price.setText(graphModel.getPrice());
        Picasso.get().load(graphModel.getImg()).into(holder.img_graph);

    }

    @Override
    public int getItemCount() {
        return graphModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView txt_price;
        ImageView img_graph;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txt_price = itemView.findViewById(R.id.txt_price);
            img_graph = itemView.findViewById(R.id.img_graph);
        }
    }
}