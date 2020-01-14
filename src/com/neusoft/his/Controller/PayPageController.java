package com.neusoft.his.Controller;

import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PayPageController {

    @FXML
    private Text Text_age;

    @FXML
    private Text Text_sex;

    @FXML
    private Text Text_name;

    @FXML
    private TextField TextField_receiptNumber;

    @FXML
    private Button Button_check;

    @FXML
    private Button Button_ok;

    @FXML
    private Text Text_price;

    @FXML
    private Text Text_tip;

    private static ObservableList<String> ol = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Event.buttonShadow(Button_check);
        Event.buttonShadow(Button_ok);
    }

    @FXML
    public void checkPatientMessage() {
        Event.getInstance().setPatient(Integer.valueOf(TextField_receiptNumber.getText()));
        ArrayList<String> list = Event.getInstance().getPatientMessage();
        Text_name.setText(list.get(0));
        Text_sex.setText(list.get(1));
        Text_age.setText(list.get(2));
        //ListView_drugs.setItems(Event.getInstance().getDrugs());
        //this.Text_price.setText(Integer.toString(Event.getInstance().getAllDrugPrice()));
        this.Text_price.setText("100");
    }

    @FXML
    public void determine() {
        Text_tip.setText("缴费成功");
    }

    @FXML
    public void toRegister() {
        MainApp.showRegisterPage();
    }

    @FXML
    public void toRetreat() {
        MainApp.showRetreatPage();
    }

    @FXML
    public void toLogin() {
        MainApp.showLoginPage();
    }
}
