package com.neusoft.his.Controller;

import com.neusoft.his.dao.DataAccess;
import com.neusoft.his.service.Event;
import com.neusoft.his.service.Patient;
import com.neusoft.his.web.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoctorPageController {

    @FXML
    private Text Text_name;

    @FXML
    private Text Text_sex;

    @FXML
    private Text Text_age;

    @FXML
    private Text text_id;

    @FXML
    private TextField TextField_medicalHistory;

    @FXML
    private TextField TextField_pastHistory;

    @FXML
    private TextField TextField_chiefComplaint;

    @FXML
    private TextArea TextArea_diagnosis;

    @FXML
    private TextField TextField_treatmentSituation;

    @FXML
    private TextField TextField_medicalReport;

    @FXML
    private Button Button_save;

    @FXML
    private Button Button_prescription;

    @FXML
    private TextField textField_dis;

    @FXML
    private Text text_save;

    @FXML
    private Text text_currentP;

    @FXML
    private Text text_nextP;

    @FXML
    private Button button_next;

    @FXML
    private Button button_refresh;


    @FXML
    private TreeView treeView_dis;

    @FXML
    private Button button_alreadyDiagnosed;

    @FXML
    private Button button_search;

    @FXML
    private TableColumn pID;

    @FXML
    private TableColumn pName;

    @FXML
    private TableView tableView_waitTreat;

    @FXML
    private Button button_returnVisit;


    private ObservableList<Patient> patientName = Event.getInstance().getPatientName();

    private ArrayList<TreeItem> treeItemsContainer = new ArrayList<>();

    @FXML
    private void initialize() throws IOException {
        payientname();
        showWaitTraetQ();
        button_next.setDisable(true);
        showTree(treeView_dis);

        pID.setCellValueFactory(new PropertyValueFactory<>("id"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView_waitTreat.setItems(Event.getInstance().getPatientName());

        Event.buttonShadow(button_refresh);
        Event.buttonShadow(button_next);
        Event.buttonShadow(Button_prescription);
        Event.buttonShadow(Button_save);
        Event.buttonShadow(button_alreadyDiagnosed);
        Event.buttonShadow(button_search);
        Event.buttonShadow(button_returnVisit);
    }

    private void payientname(){
        this.patientName = Event.getInstance().getPatientName();
        if (patientName.size() >= 2) {
            text_currentP.setText(patientName.get(0).getName());
            text_nextP.setText(patientName.get(1).getName());
            checkPatientMessage();
        } else if (patientName.size() == 1) {
            text_currentP.setText(patientName.get(0).getName());
            text_nextP.setText("无");
            checkPatientMessage();
        } else {
            text_currentP.setText("无");
            text_nextP.setText("无");
        }
    }

    @FXML
    public void save() {
        String patientName = this.text_currentP.getText();
        String chiefComplaint = this.TextField_chiefComplaint.getText();
        String medicalHistory = this.TextField_medicalHistory.getText();
        String treatmentSituation = this.TextField_treatmentSituation.getText();
        String pastHistory = this.TextField_pastHistory.getText();
        String medicalReport = this.TextField_medicalReport.getText();
        String diagnosis = this.TextArea_diagnosis.getText();
        String disaeseType = this.textField_dis.getText();
        String id = this.text_id.getText();

        Event.getInstance().setPatient(patientName);

        Event.getInstance().savePD(patientName, id, disaeseType);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Event.getInstance().saveSeeTime(Integer.parseInt(id), patientName, time);

        Event.getInstance().saveMedicalRecord(chiefComplaint, medicalHistory,
                treatmentSituation, pastHistory, medicalReport, diagnosis, disaeseType,time);

        button_next.setDisable(false);
        text_save.setText("保存成功");
    }

    @FXML
    public void goToPrescribe() throws IOException {
        Event.getInstance().getCprescription().clear();
        MainApp.showPrescribing1();
    }

    @FXML
    public void setButton_next() throws IOException {
        this.Text_name.setText("_");
        this.Text_sex.setText("_");
        this.Text_age.setText("_");
        this.text_id.setText("_");
        this.textField_dis.setText("");
        this.TextArea_diagnosis.setText("");
        this.TextField_chiefComplaint.setText("");
        this.TextField_medicalHistory.setText("");
        this.TextField_medicalReport.setText("");
        this.TextField_pastHistory.setText("");
        this.TextField_treatmentSituation.setText("");
        this.text_save.setText("");
        Event.getInstance().removeCP();
        this.patientName = Event.getInstance().getPatientName();
        payientname();
        showWaitTraetQ();
        button_next.setDisable(true);
    }

    @FXML
    public void setButton_refresh() throws IOException {
        payientname();
        showWaitTraetQ();
    }

    public void checkPatientMessage() {
        String patientName = this.text_currentP.getText();
        Event.getInstance().setPatient(patientName);
        ArrayList<String> list = Event.getInstance().getPatientMessage();
        this.Text_name.setText(list.get(0));
        this.Text_sex.setText(list.get(1));
        this.Text_age.setText(list.get(2));
        this.text_id.setText(list.get(3));
    }

    public void showWaitTraetQ() {
        this.patientName = Event.getInstance().getPatientName();
        tableView_waitTreat.setItems(patientName);
    }

    public void showPMRecord() throws IOException {
        tableView_waitTreat.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2)
            {
                Patient cPatient = (Patient) tableView_waitTreat.getSelectionModel().getSelectedItem();

                Event.getInstance().setObservableList(Event.getInstance().patientMR(cPatient.getId()));
                try {
                    new MainApp().showPatient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private TreeItem look(String s) {
        for (TreeItem treeItem : treeItemsContainer) {
            if (treeItem.getValue().equals(s)) {
                return treeItem;
            }
        }
        return new TreeItem(s);
    }

    private void showTree(TreeView treeView) throws IOException {
        showDisNameTree(treeView);
        String s = Event.getInstance().getCurrentDoctor().getDepartmentName();
        treeView.setRoot(look(s));
    }

    public void showDisNameTree(TreeView treeView) throws IOException {
        ArrayList<String> disName = DataAccess.getInstance().readDisNameTree();
        TreeItem<String> root = new TreeItem<>("DiseaseType");
        root.setExpanded(true);
        for (String s : disName) {
            if (!s.equals("exit")) {
                String[] strings = s.split("[,，]");
                if (strings.length == 1) {
                    TreeItem<String> root1 = new TreeItem<>(strings[0]);
                    root.getChildren().add(root1);
                    treeItemsContainer.add(root1);
                } else {
                    TreeItem<String> leaf = new TreeItem<>(strings[0]);
                    TreeItem leafroot = look(strings[1]);
                    leafroot.getChildren().add(leaf);
                    treeItemsContainer.add(leaf);
                }
            }
        }
        treeView.setRoot(root);
    }




    @FXML
    private void dis(){
        treeView_dis.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2)
            {
                TreeItem<String> selectedItem =
                        (TreeItem<String>) treeView_dis.getSelectionModel().getSelectedItem();
                String cName = selectedItem.getValue();
                textField_dis.setText(cName);//duo zhong bing
            }
        });
    }

    @FXML
    public void setButton_alreadyDiagnosed() throws IOException {
        new MainApp().showAlreadyDia();
    }

    @FXML
    public void setButton_search(){
        MainApp.showPatientData();
    }

    @FXML
    public void setButton_returnVisit(){
        save();
        Event.getInstance().getCurrentPatient().setReturnVisit(true);
    }
}
