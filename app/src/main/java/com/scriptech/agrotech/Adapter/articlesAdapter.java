package com.scriptech.agrotech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptech.agrotech.Model.articlesModel;
import com.scriptech.agrotech.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class articlesAdapter extends RecyclerView.Adapter<articlesAdapter.Viewholder> {

    Context context;
    ArrayList<articlesModel> articlesModelArrayList;

    public articlesAdapter(Context context, ArrayList articlesModelArrayList) {
        this.context = context;
        this.articlesModelArrayList = articlesModelArrayList;
    }

    @NonNull
    @Override
    public articlesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.card_article,parent,false);

        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull articlesAdapter.Viewholder holder, int position) {

      articlesModel articleModel = articlesModelArrayList.get(position);

      holder.articleName.setText(articleModel.getArticleName());
      Picasso.get().load(articleModel.getArticleImage()).into(holder.articleImage);



    }

    @Override
    public int getItemCount() {
        return articlesModelArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView articleName;
        ImageView articleImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            articleName = itemView.findViewById(R.id.card_article_name);
            articleImage = itemView.findViewById(R.id.card_article_image);
        }
    }
}
