package com.neusoft.his.service;

import data_structure.MyPriorityQueue;
import data_structure.MyQueue;

import java.util.ArrayList;

public class Doctor extends User {

    private String name;
    private String departmentName;
    private String time;
    private ArrayList<Integer> patientIds = new ArrayList<>();
    private  MyPriorityQueue<Patient> waitTreatq = new MyPriorityQueue<>();
    private MyQueue<Patient> alreadyTreatq = new MyQueue<>();

    public Doctor() {
        super();
    }

    public Doctor(int id, int password, String name, String departmentName, String time) {
        super(id, password);
        this.name = name;
        this.departmentName = departmentName;
        this.time = time;
//        Comparator<Patient> distanceComparator = (o1, o2)
//                -> Integer.compare(o1.getWeights() - o2.getWeights(), 0);
//        waitTreatq = new MyPriorityQueue<>(distanceComparator);
    }

    String getName() {
        return name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<Integer> getPatientIds() {
        return patientIds;
    }

    public void addPatientId(int id) {
        this.patientIds.add(id);
    }

    public MyPriorityQueue<Patient> getWaitTreatq() {
        return waitTreatq;
    }

    public void setWaitTreatq(MyPriorityQueue<Patient> waitTreatq) {
        this.waitTreatq = waitTreatq;
    }

    public MyQueue<Patient> getAlreadyTreatq() {
        return alreadyTreatq;
    }

    public void setAlreadyTreatq(MyQueue<Patient> alreadyTreatq) {
        this.alreadyTreatq = alreadyTreatq;
    }

    @Override
    public String toString() {
        return this.patientIds.toString();
    }
}
