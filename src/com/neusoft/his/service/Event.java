package com.neusoft.his.service;

import com.neusoft.his.dao.DataAccess;
import data_structure.MyHashTable;
import data_structure.MyPriorityQueue;
import data_structure.MyQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Event {

    private static class EventHolder {
        private static final Event INSTANCE = new Event();
    }

    private Doctor currentDoctor;

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    private Patient currentPatient;
    private static DiseaseType root = new DiseaseType("root");
    private static ArrayList<String> disNameArrayList = new ArrayList<>();
    private static ArrayList<String> patientArrayList = new ArrayList<>();
    private static ArrayList<DiseaseType> diseaseContainer = new ArrayList<>();
    private static ArrayList<Integer> patientID = new ArrayList<>();
    private static ArrayList<String> patientRegister = new ArrayList<>();
    private static MyQueue waitTreat = new MyQueue();
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private ObservableList<Drug> cprescription = FXCollections.observableArrayList();

    public ObservableList<String> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<String> observableList) {
        this.observableList = observableList;
    }

    public static DiseaseType getRoot() {
        return root;
    }

    public static void setRoot(DiseaseType root) {
        Event.root = root;
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    private Event() {
    }

    public static Event getInstance() {
        return EventHolder.INSTANCE;
    }

    public int login(int id, int password) {
        for (User user : DataAccess.getInstance().getUsers()) {
            if (user.getId() == id && user.getPassword() == password) {
                char mark = (id + "").charAt(0);
                switch (mark) {
                    case '1':
                        return 1;
                    case '2':
                        return 2;
                    case '3':
                        this.findDoctor(id);
                        return 3;
                    case '4':
                        return 4;
                }
            }
        }
        return -1;
    }

    private void findDoctor(int id) {
        for (Doctor doctor : DataAccess.getInstance().getDoctors()) {
            if (doctor.getId() == id) {
                this.currentDoctor = doctor;
                break;
            }
        }
    }

    public ObservableList<String> getBillingCategory() {
        ObservableList<String> billingCategory = FXCollections.observableArrayList();
        billingCategory.add("自费");
        billingCategory.add("医保");
        return billingCategory;
    }

    public ObservableList<String> getRegisterDepartment() {
        ObservableList<String> registerDepartment = FXCollections.observableArrayList();
        registerDepartment.addAll(DataAccess.getInstance().getDepartments());
        return registerDepartment;
    }

    public ObservableList<String> getDoctorName(String departmentName) {
        ObservableList<String> doctorName = FXCollections.observableArrayList();
        for (Doctor doctor : DataAccess.getInstance().getDoctors()) {
            if (doctor.getDepartmentName().equals(departmentName)) {
                doctorName.add(doctor.getName());
            }
        }
        return doctorName;
    }

    public ObservableList<String> getChargingMethod() {
        ObservableList<String> chargingMethod = FXCollections.observableArrayList();
        chargingMethod.add("现金");
        chargingMethod.add("银行卡");
        chargingMethod.add("支付宝");
        chargingMethod.add("微信支付");
        return chargingMethod;
    }

    public void register(int id, String name, String sex, String birth, int age,
                         String billingCategory, String identityNumber,
                         String homeAddress, String doctorName, boolean b) throws IOException {

        SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        int int_time = Integer.parseInt(df.format(new Date()));
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmm");
        String stime = df1.format(new Date());

        Patient patient = new Patient(id, name, sex, birth, age,
                billingCategory, identityNumber, homeAddress, doctorName, int_time);
        if (b) {
            patient.setWeights(8888888);
        }
        DataAccess.getInstance().addPatient(patient);
        ArrayList<Doctor> doctors = DataAccess.getInstance().getDoctors();
        Doctor d = new Doctor();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(doctorName)) {
                doctor.addPatientId(patient.getId());
                doctor.getWaitTreatq().push(patient);
                d = doctor;
            }
        }
        patientRegister.add(id +" "+name+" "+String.valueOf(d.getId())+" "+stime);
        patientID.add(id);
        DataAccess.getInstance().writePatientID1(patientID);
        DataAccess.getInstance().writePatientID(patientRegister);
        DataAccess.getInstance().getPatientMyHashTable().put(patient.getId(),patient);
        DataAccess.getInstance().setDoctors(doctors);
        DataAccess.getInstance().writeDoctorData();
    }


    public ObservableList<Patient> getPatientName() {
        ObservableList<Patient> patientName = FXCollections.observableArrayList();
        MyPriorityQueue wtq1 = this.currentDoctor.getWaitTreatq();
        MyPriorityQueue<Patient> demo = new MyPriorityQueue<>();
        while (!wtq1.isEmpty()) {
            Patient patient1 = (Patient) wtq1.poll();
            patientName.add(patient1);
            demo.push(patient1);
        }

        this.currentDoctor.setWaitTreatq(demo);
        return patientName;
    }

    public ObservableList<String> getAlreadyTreat() {
        ObservableList<String> alreadyTreat = FXCollections.observableArrayList();
        MyQueue atq = this.currentDoctor.getAlreadyTreatq();
        MyQueue<Patient> demo = new MyQueue<>();
        while (!atq.isEmpty()) {
            Patient patient1 = (Patient) atq.poll();
            int temp = patient1.getId();
            for (Patient patient : DataAccess.getInstance().getPatients()) {
                if (patient.getId() == temp) {
                    ArrayList a = patient.getSeeTime();
                    alreadyTreat.add(patient.getId() + "        " + patient.getName() + "        " + a.get(a.size() - 1));
                    break;
                }
            }
            demo.offer(patient1);
        }

        this.currentDoctor.setAlreadyTreatq(demo);
        return alreadyTreat;
    }

    public void removeCP() {
        Patient patient = this.currentDoctor.getWaitTreatq().poll();
        this.currentDoctor.getAlreadyTreatq().offer(patient);
    }

    public void setPatient(String patientName) {
        for (Patient patient : DataAccess.getInstance().getPatients()) {
            if (patient.getName().equals(patientName)) {
                this.currentPatient = patient;
                break;
            }
        }
    }


    public void setPatient(int receiptNumber) {
        for (Patient patient : DataAccess.getInstance().getPatients()) {
            if (patient.getId() == receiptNumber) {
                this.currentPatient = patient;
                break;
            }
        }
    }

    public void saveMedicalRecord(String chiefComplaint, String medicalHistory,
                                  String treatmentSituation, String pastHistory,
                                  String medicalReport, String diagnosis, String diseaseType, String time) {
        MedicalRecord medicalRecord = new MedicalRecord(chiefComplaint, medicalHistory,
                treatmentSituation, pastHistory, medicalReport, diagnosis,
                diseaseType, time, currentPatient.getId(), currentDoctor.getId());
        this.currentPatient.setMedicalRecord(medicalRecord);
        this.savePatientData();
    }

    private void savePatientData() {

        //存储在MyHashTable中
//        DataAccess.getInstance().getPatientMyHashTable().remove(currentPatient.getId());
//        DataAccess.getInstance().getPatientMyHashTable().put(currentPatient.getId(),currentPatient);
//        DataAccess.getInstance().writePatientByHash();


        //存储在ArrayList中
        ArrayList<Patient> patients = DataAccess.getInstance().getPatients();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == this.currentPatient.getId()) {
                patients.set(i, currentPatient);
                break;
            }
        }
        DataAccess.getInstance().setPatients(patients);
        DataAccess.getInstance().writePatient();
    }

    public ArrayList<String> getPatientMessage() {
        ArrayList<String> list = new ArrayList<>();
        list.add(this.currentPatient.getName());
        list.add(this.currentPatient.getSex());
        list.add(Integer.toString(this.currentPatient.getAge()));
        list.add(String.valueOf(this.currentPatient.getId()));
        return list;
    }

    public void removePatient(int receiptNumber) {
        ArrayList<Patient> patients = DataAccess.getInstance().getPatients();
        for (Patient patient : patients) {
            if (patient.getId() == receiptNumber) {
                patients.remove(patient);
                break;
            }
        }
        DataAccess.getInstance().setPatients(patients);
        DataAccess.getInstance().writePatient();
    }

    public void removePatient(String name) {
        ArrayList<Patient> patients = DataAccess.getInstance().getPatients();
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                patients.remove(patient);
                break;
            }
        }
        DataAccess.getInstance().setPatients(patients);
        DataAccess.getInstance().writePatient();
    }

    public ObservableList<String> getDrugs() {
        ObservableList<String> drugs = FXCollections.observableArrayList();
        for (Drug drug : this.currentPatient.getMedicalRecord().getDrugs()) {
            drugs.add(drug.getName() + " , " + drug.getPrice() + " 元");
        }
        return drugs;
    }

    public int getAllDrugPrice() {
        int price = 0;
        for (Drug drug : this.currentPatient.getMedicalRecord().getDrugs()) {
            price += drug.getPrice();
        }
        return price;
    }

    private static DiseaseType look(String s) {
        for (DiseaseType d : diseaseContainer) {
            if (d.getName().equals(s)) {
                return d;
            }
        }
        return new DiseaseType(s);
    }

    public static int creatTree(String[] strings) throws IOException {
        int disID = 1001;

        if (diseaseContainer.size() != 0) {
            return 0;
        }
        DataAccess.writeDisNameTree(strings);
        diseaseContainer.clear();
        int i = 0;
        while (!strings[i].equals("exit")) {
            String s = strings[i];
            String[] strings1 = s.split("[,，]");
            if (strings1.length == 1) {
                DiseaseType diseaseType = new DiseaseType(s, "root");
                diseaseType.setId(1000);
                DiseaseType temp = root;
                ArrayList<DiseaseType> demo = temp.getSub_diseases();
                demo.add(diseaseType);
                root.setSub_diseases(temp.getSub_diseases());
                diseaseContainer.add(diseaseType);
            } else {
                DiseaseType d = look(strings1[1]);
                DiseaseType diseaseType = new DiseaseType(strings1[0], d.getName());
                diseaseType.setId(disID++);
                d.getSub_diseases().add(diseaseType);
                diseaseContainer.add(diseaseType);
            }
            i++;
        }
        return 1;
    }


    public static ArrayList<String> outPut(DiseaseType root) {
        disNameArrayList.clear();
        return outPutTemp(root);
    }

    private static ArrayList<String> outPutTemp(DiseaseType root) {
        if (!root.getSub_diseases().isEmpty()) {
            for (DiseaseType d : root.getSub_diseases()) {
                disNameArrayList.add(d.getName());
                outPutTemp(d);
            }
        }
        return disNameArrayList;
    }

    public static ArrayList<String> outPut1(DiseaseType root1) {
        if (!root1.getSub_diseases().isEmpty()) {
            for (DiseaseType d : root1.getSub_diseases()) {
                patientArrayList.add("============");
                patientArrayList.add(d.getName());
                outPut1(d);
            }
        }
        ArrayList<Pair<Integer, String>> pairArrayList = root1.getPatients();
        for (Pair p : pairArrayList) {
            String patient = p.value_s;
            patientArrayList.add(p.key_i + "      " + patient);
        }
        return patientArrayList;
    }

    public ArrayList<String> searchDP(String s) {
        patientArrayList.clear();
        boolean temp = false;
        for (DiseaseType d : diseaseContainer) {
            if (d.getName().equals(s)) {
                temp = true;
                break;
            }
        }
        if (!temp) return null;
        DiseaseType target = look(s);
        patientArrayList.add("============");
        patientArrayList.add(s);
        ArrayList<String> s1 = outPut1(target);
        return s1;
    }

    public void savePD(String pn, String id, String dn) {
        look(dn).getPatients().add(new Pair(Integer.valueOf(id), pn));
    }

    public ObservableList<String> patientMR(int pID) {
        ObservableList<String> record = FXCollections.observableArrayList();

        //存储在MyHashTable中，在MyHashTable中查找
        Patient cpatient = DataAccess.getInstance().getPatientMyHashTable().get(pID);

        //存储在ArratyList中，在ArratyList中查找
//        ArrayList<Patient> patients = DataAccess.getInstance().getPatients();
//       Patient cpatient = null;
//        for (Patient p : patients) {
//            if (p.getId() == pID) {
//                cpatient = p;
//                break;
//            }
//        }
        if (cpatient.getSeeTime().isEmpty()) {
            record.add(cpatient.getId() + "             " + cpatient.getName()
                    + "               " + "[]");
            return record;
        }
        for (String time : cpatient.getSeeTime()) {
            record.add(cpatient.getId() + "             " + cpatient.getName()
                    + "               " + time);
        }
        return record;
    }


    public void saveSeeTime(int id, String name, String time) {
        this.currentPatient.getSeeTime().add(time);
        this.savePatientData();
    }

    public static void buttonShadow(Button button) {
        DropShadow shadow = new DropShadow();
        DropShadow shadow1 = new DropShadow(15, Color.rgb(255, 195, 205, 1));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            button.setEffect(shadow);
        });
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            button.setEffect(shadow1);
        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            button.setEffect(null);
        });
    }

    public static ArrayList<Integer> getPatientID() {
        return patientID;
    }

    public static void setPatientID(ArrayList<Integer> patientID) {
        Event.patientID = patientID;
    }

    public boolean lookID(int id) {
        for (Integer cid : patientID) {
            if (id == cid) {
                return true;
            }
        }
        return false;
    }

    public Patient lookP(int id) {
        ArrayList<Patient> patients = DataAccess.getInstance().getPatients();
        for (Patient p : patients) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private int hanNum = 0;

    public void setHanNum(int hanNum) {
        this.hanNum = hanNum;
    }

    public void removedrugs(Drug drug) {
        if (drug == null) {
            return;
        }
        int num = drug.getNumber();
        ObservableList<Drug> a = DataAccess.getInstance().getDrugContainer();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getId() == drug.getId()) {
                int number = a.get(i).getNumber();
                drug.setNumber(number + num);
                a.set(i, drug);
            }
        }
    }

    public ObservableList<String> findPrescription(String s) {
        ArrayList<String> arrayList = DataAccess.getPrescription();
        for (String arrs : arrayList) {
            if (s.equals(arrs)) {
                return DataAccess.hanHan(s);
            }
        }
        return null;
    }

    public void makePrescription(Drug drug) {
        if (drug == null) {
            return;
        }
        int id = drug.getId();
        String name = drug.getName();
        int num = drug.getNumber();//.........
        float price = drug.getPrice();
        drug.setNumber(num - hanNum);
        ObservableList<Drug> a = DataAccess.getInstance().getDrugContainer();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getId() == drug.getId()) {
                a.set(i, drug);
            }
        }
        Drug drug1 = new Drug(name, price, id, hanNum);

        cprescription.add(drug1);
        hanNum = 0;
    }

    public ObservableList<Drug> getCprescription() {
        return cprescription;
    }

    public void setCprescription(ObservableList<Drug> cprescription) {
        this.cprescription = cprescription;
    }

    private ObservableList<Drug> findDrugsName = FXCollections.observableArrayList();

    public ObservableList<Drug> getFindDrugsName() {
        return findDrugsName;
    }

    public boolean fingdn(String name) {
        for (Drug drug : DataAccess.getInstance().getDrugContainer()) {
            if (drug.getName().equals(name)) {
                findDrugsName.add(drug);
                return true;
            }
        }
        return false;
    }

    public void savePrescription(ObservableList<Drug> curprescribeList) {
        ArrayList<Drug> drugs = this.currentPatient.getMedicalRecord().getDrugs();
        for (Drug drug : curprescribeList) {
            drugs.add(drug);
        }
        this.currentPatient.getMedicalRecord().setDrugs(drugs);
        this.savePatientData();
    }

    public void retrunVisit(Patient p, MyPriorityQueue<Patient> waitTreatq) {

        switch (waitTreatq.getSize()) {
            case 0:
                waitTreatq.push(p);
                break;
            case 1:
                Patient p1 = waitTreatq.poll();
                p.setWeights(p1.getWeights()-1);
                waitTreatq.push(p1);
                waitTreatq.push(p);
                break;
            case 2:
                Patient p2 = waitTreatq.poll();
                Patient p3 = waitTreatq.poll();
                p.setWeights((p3.getWeights()-1));
                waitTreatq.push(p2);
                waitTreatq.push(p3);
                waitTreatq.push(p);
                break;
            default:
                Patient p4 = waitTreatq.poll();
                Patient p5 = waitTreatq.poll();
                Patient p6 = waitTreatq.poll();
                p.setWeights((p5.getWeights() + p6.getWeights()) / 2);
                waitTreatq.push(p4);
                waitTreatq.push(p5);
                waitTreatq.push(p);
                waitTreatq.push(p6);
                break;
        }
        this.currentDoctor.setWaitTreatq(waitTreatq);
    }

    public ObservableList<Patient>  sortByID(ArrayList<Patient> a){
        ObservableList<Patient> observableList = FXCollections.observableArrayList();

        //归并排序
//        Patient[] patients = new  Patient[a.size()];
//        for (int i =0;i<a.size();i++){
//            patients[i] = a.get(i);
//        }
//        mergeSort(patients);
//        observableList.addAll(patients);

        //快速排序
        ArrayList<Patient> arrayList = quickSort(a);
        observableList.addAll(arrayList);

        return observableList;
    }

    public ObservableList<Patient> sortByName(ArrayList<Patient> arrayList){
        ObservableList<Patient> observableList = FXCollections.observableArrayList();

        ArrayList<String> pn = new ArrayList<>();
        for (Patient p :arrayList){
            pn.add(p.getName());
        }

        Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
        pn.sort(comparator);

        for (String s :pn){
            for (Patient p:DataAccess.getInstance().getPatients()){
                if (p.getName().equals(s)){
                    observableList.add(p);
                }
            }
        }
        return observableList;
    }

    //快速排序
    private static ArrayList<Patient> quickSort(ArrayList<Patient> a) {
        sort(a, 0, a.size()-1);
        return a;
    }
    private static void sort(ArrayList<Patient> a, int low, int high) {
        if (low > high) return;
        int i = low;
        int j = high;
        int temp = a.get(low).getId();
        while (i < j) {
            while (i < j && a.get(j).getId() > temp) {
                j--;
            }
            while (i < j && a.get(i).getId() <= temp) {
                i++;
            }
            if (i < j) {
                Patient mid = a.get(i);
                a.set(i,a.get(j));
                a.set(j,mid);
            }
        }
        Patient mid1 = a.get(i);
        a.set(i,a.get(low));
        a.set(low,mid1);

        sort(a, low, i - 1);
        sort(a, i + 1, high);

    }


    //归并排序
    private static void mergeSort(Patient[] arr) {
        mergeSort(arr, new Patient[arr.length], 0, arr.length - 1);
    }

    private static void mergeSort(Patient[] arr, Patient[] temp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(arr, temp, left, center);
            mergeSort(arr, temp, center + 1, right);
            merge(arr, temp, left, center + 1, right);
        }
    }

    private static void merge(Patient[] arr, Patient[] temp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numEle = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos].getId() <= arr[rightPos].getId())
                temp[tempPos++] = arr[leftPos++];
            else
                temp[tempPos++] = arr[rightPos++];
        }
        while (leftPos <= leftEnd) {
            temp[tempPos++] = arr[leftPos++];
        }
        while (rightPos <= rightEnd) {
            temp[tempPos++] = arr[rightPos++];
        }
        for (int i = 0; i < numEle; i++) {
            arr[rightEnd] = temp[rightEnd];
            rightEnd--;
        }

    }


}
