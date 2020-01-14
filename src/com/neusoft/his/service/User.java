package com.neusoft.his.service;

import java.io.Serializable;

public abstract class User implements Serializable {

    private int id;
    private int password;

    User(int id, int password) {
        this.id = id;
        this.password = password;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
