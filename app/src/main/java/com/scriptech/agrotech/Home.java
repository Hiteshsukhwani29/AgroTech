package com.scriptech.agrotech;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.scriptech.agrotech.Adapter.articlesAdapter;
import com.scriptech.agrotech.Adapter.graphAdapter;
import com.scriptech.agrotech.Model.articlesModel;
import com.scriptech.agrotech.Model.graphModel;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {
    
    RecyclerView recyclerView_graph ,recyclerView_article;
    FirebaseFirestore db;
    graphAdapter graphAdapter;
    articlesAdapter articlesAdapter;
    ArrayList<graphModel> graphModelArrayList;
    ArrayList<articlesModel> articlesModelArrayList;

    TextView vm1,vm2;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_home, container, false);

        recyclerView_graph = v.findViewById(R.id.recycler_Home_graph);
        recyclerView_article = v.findViewById(R.id.recycler_Artical);
        recyclerView_graph.setHasFixedSize(true);
        recyclerView_graph.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        graphModelArrayList = new ArrayList<graphModel>();
        graphAdapter = new graphAdapter(getActivity(), graphModelArrayList);

        recyclerView_graph.setAdapter(graphAdapter);

        articlesModelArrayList = new ArrayList<articlesModel>();
        articlesAdapter = new articlesAdapter(getActivity(), articlesModelArrayList);

        recyclerView_article.setAdapter(articlesAdapter);
        vm1 = v.findViewById(R.id.vm1);
        vm2 = v.findViewById(R.id.vm2);

        GraphList();
        articlesList();
        vm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new graph();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        vm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Articles();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return v;
    }

    private void articlesList() {
        db.collection("articlesList").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                articlesModel c = d.toObject(articlesModel.class);

                                articlesModelArrayList.add(c);
                            }
                            articlesAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
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