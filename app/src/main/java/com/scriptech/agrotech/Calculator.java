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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import static android.R.layout.simple_spinner_item;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.scriptech.agrotech.Adapter.articlesAdapter;
import com.scriptech.agrotech.Adapter.graphAdapter;
import com.scriptech.agrotech.Model.articlesModel;
import com.scriptech.agrotech.Model.graphModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Calculator extends Fragment {


    private FirebaseFirestore db;
    int sum;
    String[] crop = {"सोयाबीन / Soyabeen",};
    String[] state = {"Madhya Pradesh", "Bihar", "Maharashtra"};
    Spinner spin, spin2;
    int e1, e2;
    TextView itemdetails;
    String a,b;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View v = inflater.inflate(R.layout.frag_calsi,container,false);

         db = FirebaseFirestore.getInstance();
         spin = v.findViewById(R.id.spinner);
         spin2 = v.findViewById(R.id.spinner_2);

         itemdetails = v.findViewById(R.id.item_details);

        ArrayAdapter a = new ArrayAdapter(getActivity(), simple_spinner_item, crop);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(a);

        ArrayAdapter b = new ArrayAdapter(getActivity(), simple_spinner_item, state);
        b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(b);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                e1 = 0;
                switch (position) {
                    case 0: {
                        e1 = position*10;
                        break;
                    }
                    case 1: {
                        e1 = position*10;
                        break;
                    }
                    case 2: {
                        e1 = position*10;
                        break;
                    }
                    case 3: {
                        e1 = position*10;
                        break;
                    }

                }
            }

                @Override
                public void onNothingSelected (AdapterView < ? > parent){

                }

        });


        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                e2 = 0;
                switch (position) {
                    case 0: {
                        e2 = position;
                        break;
                    }
                    case 1: {
                        e2 = position;
                        break;
                    }
                    case 2: {
                        e2 = position;
                        break;
                    }
                    case 3: {
                        e2 = position;
                        break;
                    }

                }
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }

        });

        sum = e1 + e2;


        MarketValue();



        return v;
    }

    private void MarketValue(){

        DocumentReference docRef = db.collection("graphList").document(String.valueOf(sum));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document!=null){
                        a = document.getString("name");
                        b = document.getString("price");

                        itemdetails.setText(a+" - "+b+" Rs");


                    }
                    else {
                        Log.d("LOGGER", "Error");
                    }
                }else {
                    Log.d("Logger","get failed with",task.getException());
                }

            }
        });

    }

}
