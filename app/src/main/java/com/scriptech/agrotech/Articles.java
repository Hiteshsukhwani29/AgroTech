package com.scriptech.agrotech;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.scriptech.agrotech.Adapter.articlesAdapter;
import com.scriptech.agrotech.Model.articlesModel;

import java.util.ArrayList;


public class Articles extends Fragment {

    RecyclerView recyclerView;
    ArrayList<articlesModel> articlesModelArrayList;
    articlesAdapter articlesAdapter;
    FirebaseFirestore db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_articles, container, false);

        recyclerView = v.findViewById(R.id.rv_all_articles);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        articlesModelArrayList = new ArrayList<articlesModel>();
        articlesAdapter = new articlesAdapter(getActivity(), articlesModelArrayList);

        recyclerView.setAdapter(articlesAdapter);

        articlesList();


        return v;

    }

    private void articlesList() {

        db.collection("articlesList")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                articlesModelArrayList.add(dc.getDocument().toObject(articlesModel.class));
                            }
                            articlesAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}