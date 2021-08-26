package com.scriptech.agrotech;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.scriptech.agrotech.Model.speciesModel;

import java.util.ArrayList;


public class Species extends Fragment {

    RecyclerView recyclerView;
    ArrayList<speciesModel> speciesModelArrayList;
    SpeciesAdapter speciesAdapter;
    FirebaseFirestore db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_species, container, false);

        recyclerView = v.findViewById(R.id.recycler_species);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        speciesModelArrayList = new ArrayList<speciesModel>();
        speciesAdapter = new SpeciesAdapter(getActivity(), speciesModelArrayList);

        recyclerView.setAdapter(speciesAdapter);

        SpeciesList();


        return v;

    }

    private void SpeciesList() {

        db.collection("speciesList")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                speciesModelArrayList.add(dc.getDocument().toObject(speciesModel.class));
                            }
                            speciesAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}