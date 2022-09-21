package com.example.fruitstore;

import java.io.Serializable;
// i added ( implements Serializable) in the brackts at the top of the page.
public class Fruit implements Serializable {

    private String fruitName;
    private double fruitPrice;
    private String fruitPicture;

    public Fruit(String fruitName, double fruitPrice, String fruitPicture) {
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.fruitPicture = fruitPicture;
    }

    public Fruit (){

    }


    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public double getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(double fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    public String getFruitPicture() {
        return fruitPicture;
    }

    public void setFruitPicture(String fruitPicture) {
        this.fruitPicture = fruitPicture;
    }
}
