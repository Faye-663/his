package com.neusoft.his.Controller;

import com.neusoft.his.dao.DataAccess;
import com.neusoft.his.service.Drug;
import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;

public class PrescribingController {

    @FXML
    private Button button_removeDrugs;

    @FXML
    private Button button_apply;

    @FXML
    private Button button_searchDrugs;

    @FXML
    private Button button_addDrugs;

    @FXML
    private ListView<String> mlistview;

    @FXML
    private TextField sreachDrugName;

    @FXML
    private Button button_send;


    @FXML
    private Text Text_tip;

    @FXML
    private TableView drugStore;

    @FXML
    private TableView prescription;

    @FXML
    private TableColumn dID;

    @FXML
    private TableColumn dName;

    @FXML
    private TableColumn dPrice;

    @FXML
    private TableColumn dNum;

    @FXML
    private TableColumn pID;

    @FXML
    private TableColumn pName;

    @FXML
    private TableColumn pPrice;

    @FXML
    private TableColumn pNum;

    @FXML
    private TextField textField_num;

    @FXML
    private Text text_tip;

    @FXML
    private Text text_findtip;

    @FXML
    private Button button_cancel;


    private static Drug curDrug;

    public static Drug getCurDrug() {
        return curDrug;
    }

    public static boolean temp = true;//.........

    private static ObservableList<String> prescribeList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        DataAccess.getp(prescribeList);
        //mlistview.setItems(prescribeList);
        button_apply.setDisable(true);
        dID.setCellValueFactory(new PropertyValueFactory<>("id"));
        pID.setCellValueFactory(new PropertyValueFactory<>("id"));
        dName.setCellValueFactory(new PropertyValueFactory<>("name"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        pPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dNum.setCellValueFactory(new PropertyValueFactory<>("number"));
        pNum.setCellValueFactory(new PropertyValueFactory<>("number"));

        drugStore.setItems(DataAccess.getInstance().getDrugContainer());
        //drugStore.getColumns().addAll(dID, dName, dPrice, dName);

        Event.buttonShadow(button_send);
        Event.buttonShadow(button_addDrugs);
        Event.buttonShadow(button_searchDrugs);
        Event.buttonShadow(button_apply);
        Event.buttonShadow(button_removeDrugs);
        Event.buttonShadow(button_cancel);
    }

    @FXML
    public void addDrugs() throws IOException {
        curDrug = (Drug) drugStore.getSelectionModel().getSelectedItem();
        int number = Integer.parseInt(textField_num.getText());
        int num = curDrug.getNumber();
        if (num < number) {
            text_tip.setText("数量不足，最大 " + num);
            number = num;
        }
        Event.getInstance().setHanNum(number);
        Event.getInstance().makePrescription(curDrug);
        prescription.setItems(Event.getInstance().getCprescription());
        drugStore.setItems(DataAccess.getInstance().getDrugContainer());
        textField_num.setText("");
    }

    @FXML
    public void removeDrugs() {
        text_tip.setText("");
        Drug drug = (Drug) prescription.getSelectionModel().getSelectedItem();

        Event.getInstance().removedrugs(drug);
        Event.getInstance().getCprescription().remove(drug);
        prescription.setItems(Event.getInstance().getCprescription());
        drugStore.setItems(DataAccess.getInstance().getDrugContainer());
    }

    @FXML
    public void searchDrugs() {
        text_tip.setText("");
        String drugName = sreachDrugName.getText();
        boolean b = Event.getInstance().fingdn(drugName);
        if (b){
            drugStore.setItems(Event.getInstance().getFindDrugsName());
            //Event.getInstance().getFindDrugsName().clear();
        }else {
            text_findtip.setText("未搜索到该药物！");
        }
    }

    @FXML
    public void setButton_cancel(){
        Event.getInstance().getFindDrugsName().clear();
        drugStore.setItems(DataAccess.getInstance().getDrugContainer());
        text_findtip.setText("");
        sreachDrugName.setText("");
    }


    @FXML
    public void setSend() {
        text_tip.setText("");
        ObservableList<Drug> curprescribeList = prescription.getItems();

        Text_tip.setText("发送成功");
    }



    @FXML
    public void setApply() {
//        String s = mlistview.getSelectionModel().getSelectedItem();
//        flistview.setItems(Event.getInstance().findPrescription(s));
        //mo ban
    }

    @FXML
    public void clearTip(){
        text_tip.setText("");
    }


}
