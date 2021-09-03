package com.scriptech.agrotech.Model;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptech.agrotech.Adapter.SpeciesAdapter;

import java.util.ArrayList;

public class speciesModel {

    public String name;
    public String dsNos;

    public String getdsNos() {
        return dsNos;
    }

    public  speciesModel(){

    }


    public void setdsNos(String refno) {
        this.dsNos = refno;
    }

    public speciesModel(String speciesName) {
        this.name = name;
    }


    public String getSpeciesName() {
        return name;
    }

    public void setSpeciesName(String speciesName) {
        this.name = name;
    }
}

