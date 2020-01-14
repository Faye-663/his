package com.neusoft.his.service;


import java.lang.reflect.Type;

public class Pair<I , P> {
    public String key_s;
    public int key_i;
    public Type value;
    public String value_s;

    public Pair(String key, Type value){
        this.key_s = key;
        this.value = value;
    }
    public Pair(int key, Type value){
        this.key_i = key;
        this.value = value;
    }

    public Pair(Integer id, String dn) {
        this.key_i = id;
        this.value_s = dn;
    }
}
