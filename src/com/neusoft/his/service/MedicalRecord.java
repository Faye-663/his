package com.neusoft.his.service;

import java.io.Serializable;
import java.util.ArrayList;

public class MedicalRecord implements Serializable {

    private String chiefComplaint;
    private String medicalHistory;
    private String treatmentSituation;
    private String pastHistory;
    private String medicalReport;
    private String diagnosis;
    private int patientID;
    private int doctorID;
    private String time;
    private String disaeseType;//病种
    private  ArrayList<Drug> drugs;//static

    MedicalRecord() {
    }

    MedicalRecord(String chiefComplaint, String medicalHistory,
                  String treatmentSituation, String pastHistory,
                  String medicalReport, String diagnosis,String disaeseType,String time,int pid,int did) {
        this.chiefComplaint = chiefComplaint;
        this.medicalHistory = medicalHistory;
        this.treatmentSituation = treatmentSituation;
        this.pastHistory = pastHistory;
        this.medicalReport = medicalReport;
        this.diagnosis = diagnosis;
        this.disaeseType = disaeseType;
        this.time = time;
        this.patientID = pid;
        this.doctorID = did;
        drugs = new ArrayList<>();
    }

    public void addDrug(String name, int price) {
        this.drugs.add(new Drug(name, price));
    }

    ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    public int getDoctorID() {
        return doctorID;
    }
}
