package com.neusoft.his.service;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Serializable,Comparable {

    private int id;
    private String name;
    private String sex;
    private String birth;
    private int age;
    private String billingCategory;
    private String identityNumber;
    private String homeAddress;
    private String doctorName;
    private MedicalRecord medicalRecord = new MedicalRecord();
    private ArrayList<String> seeTime = new ArrayList<>();
    private int Weights = 1;
    private boolean returnVisit = false;

    Patient(int id, String name, String sex, String birth,
            int age, String billingCategory, String identityNumber,
            String homeAddress, String doctorName) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.age = age;
        this.billingCategory = billingCategory;
        this.identityNumber = identityNumber;
        this.homeAddress = homeAddress;
        this.doctorName = doctorName;
    }

    Patient(int id, String name, String sex, String birth,
            int age, String billingCategory, String identityNumber,
            String homeAddress, String doctorName,int weights) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.age = age;
        this.billingCategory = billingCategory;
        this.identityNumber = identityNumber;
        this.homeAddress = homeAddress;
        this.doctorName = doctorName;
        this.Weights = 999999 - weights;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setWeights(int weights) {
        Weights = weights;
    }

    public int getWeights() {
        return Weights;
    }

    public ArrayList<String> getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(ArrayList<String> seeTime) {
        this.seeTime = seeTime;
    }

    void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public boolean isReturnVisit() {
        return returnVisit;
    }

    public void setReturnVisit(boolean returnVisit) {
        this.returnVisit = returnVisit;
    }

    @Override
    public int compareTo(Object o) {
        Patient p = (Patient) o;
        return this.Weights-((Patient) o).getWeights();
    }
}
