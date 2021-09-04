package com.scriptech.agrotech.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class graphModel {
    public String price;
    public String img ;

    public graphModel() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public graphModel(String price, String img) {
        this.price = price;
        this.img = img;
    }
}
