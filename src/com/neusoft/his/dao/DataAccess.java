package com.neusoft.his.dao;

import com.neusoft.his.service.*;
import data_structure.MyHashTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class DataAccess {

    private static class DataAccessHolder {
        private static final DataAccess INSTANCE = new DataAccess();
    }

    private File userFile = new File("src/data/UserData.txt");
    private File doctorFile = new File("src/data/DoctorData.txt");
    private File departmentFile = new File("src/data/Department.txt");
    private File patientFile = new File("src/data/PatientData.txt");
    private File drugFile = new File("src/data/DrugData.txt");
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<String> departments = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();

    private MyHashTable<Integer , Patient> patientMyHashTable = new MyHashTable<>();

    private ArrayList<Drug> drugs = new ArrayList<>();
    private static ArrayList<String> prescription = new ArrayList<>();
    private static int[] arr = randomArr();
    private static int temp = 0;
    private ObservableList<Drug> drugContainer = FXCollections.observableArrayList();


    private DataAccess() {
    }

    public static DataAccess getInstance() {
        return DataAccessHolder.INSTANCE;
    }

    public void writeUserData() {
        this.writeObject(this.users, userFile);
    }

    private void readUserData() {
        this.users = this.readObject(this.userFile);
    }

    private void addUser(User user) {
        this.addElement(user, this.users, userFile);
    }

    public void writeDoctorData() {
        this.writeObject(this.doctors, doctorFile);
    }

    private void readDoctorData() {
        this.doctors = this.readObject(this.doctorFile);
    }

    private void addDoctor(Doctor doctor) {
        this.addElement(doctor, this.doctors, doctorFile);
    }

    public void writeDepartmentData() {
        this.writeObject(this.departments, departmentFile);
    }

    private void readDepartmentData() {
        this.departments = this.readObject(this.departmentFile);
    }

    private void addDepartment(String departmentName) {
        this.addElement(departmentName, this.departments, departmentFile);
    }

    public void writePatient() {
        this.writeObject(this.patients, this.patientFile);
    }

    public void readPatientData() {
        this.patients = this.readObject(this.patientFile);
    }

    public void addPatient(Patient patient) {
        this.addElement(patient, this.patients, this.patientFile);
    }

    public void writeDrugData() {
        this.writeObject(this.drugs, this.drugFile);
    }

    private void readDrugData() {
        this.drugs = this.readObject(this.drugFile);
    }

    private void addDrug(Drug drug) {
        this.addElement(drug, this.drugs, this.drugFile);
    }

    public ObservableList<Drug> getDrugContainer() {
        return drugContainer;
    }

    public void setDrugContainer(ObservableList<Drug> drugContainer) {
        this.drugContainer = drugContainer;
    }


    private void writeObject(ArrayList elements, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(elements);
            oos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writePatientByHash() {
        try {
            File file = new File("src/data/PatientData.txt");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(patientMyHashTable);
            oos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private ArrayList readObject(File file) {
        ArrayList elements = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            elements = (ArrayList) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return elements;
    }

    private static int[] randomArr() {
        int[] arr = new int[1000];
        int num = 1001;
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            int temp = rd.nextInt(num);
            for (int value : arr) {
                if (value == temp) {
                    count++;
                }
            }
            if (count > 0) {
                i--;
            } else {
                arr[i] = temp;
            }
        }
        return arr;
    }

    public static int getID() {
        int num = arr[temp];
        temp++;
        return num;
    }

    private void addElement(Object element, ArrayList elements, File file) {
        elements.add(element);
        this.writeObject(elements, file);
    }

    public void dataInit()  {
        this.addUser(new Register(1000, 123456));
        this.addUser(new Register(2000, 123456));
        this.addUser(new Doctor(3000, 123456, "张三", "呼吸内科", "11111111111111"));
        this.addUser(new Doctor(3001, 123456, "李四", "消化内科", "11111111111111"));
        this.addUser(new Doctor(3002, 123456, "王五", "骨科", "11111111111111"));
        this.addUser(new Doctor(3003, 123456, "赵六", "神经外科", "11111111111111"));
        this.addDepartment("呼吸内科");
        this.addDepartment("消化内科");
        this.addDepartment("骨科");
        this.addDepartment("神经外科");
        this.addDoctor(new Doctor(3000, 123456, "张三", "呼吸内科", "11111111111111"));
        this.addDoctor(new Doctor(3001, 123456, "李四", "消化内科", "11111111111111"));
        this.addDoctor(new Doctor(3002, 123456, "王五", "骨科", "11111111111111"));
        this.addDoctor(new Doctor(3003, 123456, "赵六", "神经外科", "11111111111111"));
        Drug drug1 = new Drug("阿莫西林", (float) 28.5,1001,999);
        Drug drug2 = new Drug("感冒胶囊", (float) 23.6,1002,999);
        Drug drug3 = new Drug("顺尔宁片", 45,1003,999);
        Drug drug4 = new Drug("肠炎宁", 36,1004,999);
        Drug drug5 = new Drug("藤黄健骨胶囊", 89,1005,999);
        this.addDrug(drug1);
        this.addDrug(drug2);
        this.addDrug(drug3);
        this.addDrug(drug4);
        this.addDrug(drug5);

        //ArrayList<String> arrayList =  readDisNameTree();

        drugContainer.add(drug1);
        drugContainer.add(drug2);
        drugContainer.add(drug3);
        drugContainer.add(drug4);
        drugContainer.add(drug5);



        this.readAllData();
    }

    public void readAllData() {
        this.readUserData();
        this.readDepartmentData();
        this.readDoctorData();
        this.readDrugData();
    }

    public static ObservableList<String> getp(ObservableList<String> p) {
        prescription.add("风寒");
        prescription.add("发热");
        prescription.add("咳嗽");
        p.addAll(prescription);
        return p;
    }

    public static ObservableList<String> hanHan(String s){
        if (s.equals("风寒")){
            return getFh();
        }else if (s.equals("发热")){
            return getFr();
        }else {
            return getKs();
        }
    }

    public static ObservableList<String> getFh() {
        ObservableList<String> prescribeList1 = FXCollections.observableArrayList();
        prescribeList1.add("感冒胶囊");
        prescribeList1.add("感康灵");
        return prescribeList1;
    }

    public static ObservableList<String> getFr() {
        ObservableList<String> p = FXCollections.observableArrayList();
        p.add("退烧贴");
        p.add("退烧冲剂");
        return p;
    }

    public static ObservableList<String> getKs() {
        ObservableList<String> p = FXCollections.observableArrayList();
        p.add("止咳糖浆");
        p.add("止咳胶囊");
        return p;
    }

    public static void writeDisNameTree(String[] strings) throws IOException {
        File file = new File("src/data/DiseaseType.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/DiseaseType.txt"));
        for (String s : strings) {
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public ArrayList<String> readDisNameTree() throws IOException {
        File file = new File("src/data/DiseaseType.txt");
        BufferedReader bf = new BufferedReader(new FileReader("src/data/DiseaseType.txt"));
        ArrayList<String> arrayList = new ArrayList<>();
        String str = bf.readLine();
        while (str != null) {
            arrayList.add(str);
            str = bf.readLine();
        }
        return arrayList;
    }

    public  void writePatientID(ArrayList<String> patientID) throws IOException {
        File file = new File("src/data/PatientID.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/PatientID.txt"));
        for (String id:patientID) {
            bw.write(id);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public  void writePatientID1(ArrayList<Integer> patientID) throws IOException {
        File file = new File("src/data/PatientID.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/PatientID.txt"));
        for (Integer id:patientID) {
            bw.write(id);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void setDepartments(ArrayList<String> departments) {
        this.departments = departments;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    public static ArrayList<String> getPrescription() {
        return prescription;
    }

    public MyHashTable<Integer, Patient> getPatientMyHashTable() {
        return patientMyHashTable;
    }

    public void setPatientMyHashTable(MyHashTable<Integer, Patient> patientMyHashTable) {
        this.patientMyHashTable = patientMyHashTable;
    }

    public static void setPrescription(ArrayList<String> prescription) {
        DataAccess.prescription = prescription;
    }
}
