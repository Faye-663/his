package com.neusoft.his.service;

import java.io.Serializable;

public class Drug implements Serializable {

    private String name;
    private float price;
    private int id;
    private int number;
    private String functions;

    public Drug(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Drug(String name, float price,int id,int number) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.id =id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctions() {
        return functions;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + " " + this.price;
    }
}
