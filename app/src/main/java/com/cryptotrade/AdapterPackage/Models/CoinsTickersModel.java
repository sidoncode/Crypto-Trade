package com.cryptotrade.AdapterPackage.Models;



public class CoinsTickersModel {
    String id;
    String image;
    String current_price;


    public CoinsTickersModel(String id, String image, String current_price) {
        this.id = id;
        this.current_price = current_price;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;


    }
}
