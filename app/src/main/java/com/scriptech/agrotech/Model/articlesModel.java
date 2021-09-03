package com.scriptech.agrotech.Model;

public class articlesModel {

    public String name;
    public String image;
    public String rno;


    public articlesModel(){

    }

   /* public articlesModel(String speciesName) {
        this.name = name;
    }
*/
    public String getArticleName() {
        return name;
    }
    public String getArticleImage() {
        return image;
    }
    public String getRefNo() {
        return rno;
    }
/*
    public void setSpeciesName(String speciesName) {
        this.name = name;
    }*/
}

