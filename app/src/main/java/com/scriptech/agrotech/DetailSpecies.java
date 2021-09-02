package com.scriptech.agrotech;

import android.graphics.Color;
import android.os.Build;
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


public class DetailSpecies extends Fragment {

    private FirebaseFirestore db;
    TextView txtDetail_Species,txt_detail_sowing,txt_detail_temp,txt_detail_soil;
    ImageView img_ds;
    String a,b,c,d,e,dsNos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_detail_species, container, false);

        dsNos = getArguments().get("dsNos").toString();

        img_ds =v.findViewById(R.id.img_ds);
        txtDetail_Species = v.findViewById(R.id.txtDetail_Species);
        txt_detail_sowing = v.findViewById(R.id.txt_detail_sowing);
        txt_detail_temp = v.findViewById(R.id.txt_detail_temp);
        txt_detail_soil = v.findViewById(R.id.txt_detail_soil);

        getdata();
        return v;
    }

    private void getdata() {
        DocumentReference docRef = db.collection("DetailSpecies").document(dsNos);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document!=null){
                        a = document.getString("name");
                        b = document.getString("sowing");
                        c = document.getString("temp");
                        d = document.getString("soil");
                        e = document.getString("img_ds");

                        txtDetail_Species.setText(a);
                        txt_detail_sowing.setText(b);
                        txt_detail_temp.setText(c);
                        txt_detail_soil.setText(d);

                        Picasso.get().load(b).into(img_ds);

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

