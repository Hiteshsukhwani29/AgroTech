package com.scriptech.agrotech.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptech.agrotech.Model.articlesModel;
import com.scriptech.agrotech.R;
import com.scriptech.agrotech.detailedArticle;
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
      holder.Card.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Fragment fragment = new detailedArticle();
              Bundle bundle = new Bundle();
              bundle.putString("refno", articleModel.getRefNo());
              fragment.setArguments(bundle);
              FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
              FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
              fragmentTransaction.replace(R.id.frame, fragment);
              fragmentTransaction.addToBackStack(null);
              fragmentTransaction.commit();
          }
      });



    }

    @Override
    public int getItemCount() {
        return articlesModelArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView articleName;
        ImageView articleImage;
        CardView Card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            articleName = itemView.findViewById(R.id.card_article_name);
            articleImage = itemView.findViewById(R.id.card_article_image);
            Card = itemView.findViewById(R.id.maincard);
        }
    }
}
