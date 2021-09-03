package com.scriptech.agrotech;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class detailedArticle extends Fragment {

    private FirebaseFirestore db;
    TextView ArticleHeading, ArticleAuthorName, ArticlePubDate, ArticleBody;
    ImageView ArticleAuthorImage, ArticlePoster;
    String a, b, c, d, e, f, refno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_detailed_article,container,false);

        refno = getArguments().get("refno").toString();

        ArticleHeading = v.findViewById(R.id.detailedArticle_heading);
        ArticleAuthorName = v.findViewById(R.id.article_person_name);
        ArticlePubDate = v.findViewById(R.id.article_date);
        ArticleBody = v.findViewById(R.id.article_body);
        ArticleAuthorImage = v.findViewById(R.id.article_image);
        ArticlePoster = v.findViewById(R.id.article_poster);

        db = FirebaseFirestore.getInstance();

        getdata();


        return v;
    }

    private void getdata(){
        DocumentReference docRef = db.collection("DetailedArticle").document(refno);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        a = document.getString("author_name");
                        b = document.getString("author_image");
                        c = document.getString("date");
                        d = document.getString("body");
                        e = document.getString("poster");
                        f = document.getString("heading");

                        ArticleAuthorName.setText(a);
                        ArticlePubDate.setText(c);
                        ArticleBody.setText(d);
                        ArticleHeading.setText(f);

                        Picasso.get().load(b).into(ArticleAuthorImage);
                        Picasso.get().load(e).into(ArticlePoster);


                    } else {
                        Log.d("LOGGER", "Error");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });
    }

}
