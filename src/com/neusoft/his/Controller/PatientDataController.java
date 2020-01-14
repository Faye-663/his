package com.neusoft.his.Controller;

import com.neusoft.his.dao.DataAccess;
import com.neusoft.his.service.Event;
import com.neusoft.his.service.Patient;
import com.neusoft.his.web.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class PatientDataController {

    @FXML
    private TreeView treeView_dis;

    @FXML
    private Button button_return;

    @FXML
    private TableView tableView_patient;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn name;

    @FXML
    private ChoiceBox sort;

    @FXML
    private Button button_yes;


    public ArrayList<Patient> patientData = new ArrayList<>();

    @FXML
    private void initialize() throws IOException {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        Event.buttonShadow(button_return);
        Event.buttonShadow(button_yes);
        new DoctorPageController().showDisNameTree(treeView_dis);
        sort.setItems(FXCollections.observableArrayList("病历号排序", "姓名排序"));
    }

    @FXML
    public void patientData() {
        TreeItem<String> selectedItem =
                (TreeItem<String>) treeView_dis.getSelectionModel().getSelectedItem();
        String cName = selectedItem.getValue();
        ArrayList<String> searchDP = Event.getInstance().searchDP(cName);

        for (String s : searchDP) {
            if (!s.equals("============")) {
                String[] strings = s.split(" +");
                if (strings.length > 1) {
                    int cpid = Integer.parseInt(strings[0]);
                    for (Patient p : DataAccess.getInstance().getPatients()) {
                        if (p.getId() == cpid) {
                            patientData.add(p);
                        }
                    }
                }
            }
        }
        //tableView_patient.setItems(observableList);

        //tableView_patient.getColumns().addAll(id,name);

    }

    @FXML
    public void setButton_return() {
        MainApp.showDoctorPage1();
    }

    @FXML
    public void setSort() {
        patientData.clear();
        patientData();
        String s = (String) sort.getValue();
        if (s.equals("病历号排序")) {
            tableView_patient.setItems(Event.getInstance().sortByID(patientData));
        } else {
            tableView_patient.setItems(Event.getInstance().sortByName(patientData));
        }
    }


}
