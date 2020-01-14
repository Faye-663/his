package com.neusoft.his.service;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DiseaseType implements Type {
    private String name;
    private String parentDis;
    private ArrayList<Pair<Integer,String>> patients;
    private ArrayList<DiseaseType> sub_diseases;
    private int id;

    public DiseaseType(String name,String parentDis,ArrayList sub,ArrayList patients){
        this.name = name;
        this.sub_diseases = sub;
        this.parentDis = parentDis;
        this.patients = patients;
    }

    public DiseaseType(String name,String parentDis){
        this.name = name;
        this.sub_diseases =new ArrayList<>();
        this.parentDis = parentDis;
        this.patients = new ArrayList<>();
    }


    public DiseaseType(String name){
        this.name = name;
        this.sub_diseases =new ArrayList<>();
        this.parentDis = null;
        this.patients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentDis() {
        return parentDis;
    }

    public void setParentDis(String parentDis) {
        this.parentDis = parentDis;
    }

    public ArrayList<Pair<Integer, String>> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Pair<Integer, String>> patients) {
        this.patients = patients;
    }

    public ArrayList<DiseaseType> getSub_diseases() {
        return sub_diseases;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSub_diseases(ArrayList<DiseaseType> sub_diseases) {
        this.sub_diseases = sub_diseases;
    }
}

//内科
//呼吸内科，内科
//消化内科，内科
//肝病，消化内科
//胃病，消化内科
//肝硬化，肝病
//胃溃疡，胃病
//胃炎，胃病
//外科
//骨科，外科
//神经外科，外科
//骨折，骨科
//颈椎病，骨科
//神经痛，神经外科
//帕金森，神经外科
//exit