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
import com.scriptech.agrotech.Adapter.SpeciesAdapter;
import com.scriptech.agrotech.Adapter.graphAdapter;
import com.scriptech.agrotech.Model.graphModel;
import com.scriptech.agrotech.Model.speciesModel;

import java.util.ArrayList;


public class graph extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    graphAdapter graphAdapter;
    ArrayList<graphModel> graphModelArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_graph, container, false);

        recyclerView = v.findViewById(R.id.recycler_graph);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();

        graphModelArrayList = new ArrayList<graphModel>();
        graphAdapter = new graphAdapter(getActivity(), graphModelArrayList);

        recyclerView.setAdapter(graphAdapter);

        GraphList();


        return v;
    }

    private void GraphList() {
        db.collection("graphList")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                graphModelArrayList.add(dc.getDocument().toObject(graphModel.class));
                            }
                            graphAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
