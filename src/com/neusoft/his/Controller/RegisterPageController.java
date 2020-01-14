package com.neusoft.his.Controller;

import com.neusoft.his.dao.DataAccess;
import com.neusoft.his.service.Patient;
import com.neusoft.his.web.MainApp;
import com.neusoft.his.service.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RegisterPageController {

    @FXML
    private Button Button_toLogin;

    @FXML
    private TextField TextField_receiptNumber;

    @FXML
    private TextField TextField_age;

    @FXML
    private CheckBox CheckBox_book;

    @FXML
    private ChoiceBox<String> ChoiceBox_billingCategory;

    @FXML
    private DatePicker DatePicker_date;

    @FXML
    private Button Button_ok;

    @FXML
    private Button Button_clear;

    @FXML
    private TextField TextField_address;

    @FXML
    private TextField TextField_name;

    @FXML
    private ChoiceBox<String> ChoiceBox_doctor;

    @FXML
    private ChoiceBox<String> ChoiceBox_registerDepartment;

    @FXML
    private ChoiceBox<String> ChoiceBox_chargingMethod;

    @FXML
    private TextField TextField_identityNumber;

    @FXML
    private DatePicker DatePicker_birth;

    @FXML
    private Button Button_refund;

    @FXML
    private Button Button_takeMedicine;

    @FXML
    private Text Text_tip1;

    @FXML
    private ChoiceBox<String> choiceBox_sex;

    @FXML
    private TextField textField_id;

    @FXML
    private CheckBox CheckBox_emergency;

    @FXML
    private Button button_reviseId;

    @FXML
    private  Button button_yesId;

    @FXML
    private Button button_returnVisit;

    @FXML
    private void initialize() {
        this.textField_id.setText(String.valueOf(10000 + DataAccess.getID()));
        textField_id.setDisable(true);
        this.choiceBox_sex.setItems(FXCollections.observableArrayList("男", "女"));
        this.ChoiceBox_billingCategory.setItems(Event.getInstance().getBillingCategory());
        this.ChoiceBox_registerDepartment.setItems(Event.getInstance().getRegisterDepartment());
        this.ChoiceBox_chargingMethod.setItems(Event.getInstance().getChargingMethod());

        button_yesId.setDisable(true);
        Event.buttonShadow(Button_clear);
        Event.buttonShadow(Button_ok);
        Event.buttonShadow(Button_refund);
        Event.buttonShadow(Button_takeMedicine);
        Event.buttonShadow(Button_toLogin);
        Event.buttonShadow(button_yesId);
        Event.buttonShadow(button_reviseId);
        Event.buttonShadow(button_returnVisit);
    }

    @FXML
    public void register() throws IOException {
        int id = Integer.parseInt(this.textField_id.getText());
        String name = this.TextField_name.getText();
        String sex = this.choiceBox_sex.getValue();
        LocalDate localDate = this.DatePicker_birth.getValue();
        String year = String.valueOf(localDate.getYear());
        String month = String.valueOf(localDate.getMonthValue());
        String day = String.valueOf(localDate.getDayOfMonth());
        String birth =(year+"/"+month+"/"+day);
        int age = Integer.parseInt(this.TextField_age.getText());
        String identityNumber = this.TextField_identityNumber.getText();
        String homeAddress = this.TextField_address.getText();
        String billingCategory = this.ChoiceBox_billingCategory.getValue();
        String doctorName = this.ChoiceBox_doctor.getValue();
        String department = this.ChoiceBox_registerDepartment.getValue();

        boolean b = CheckBox_emergency.isSelected();
        Event.getInstance().register(id, name, sex, birth, age,
                billingCategory, identityNumber, homeAddress, doctorName,b);
        Text_tip1.setText("挂号成功");
    }

    @FXML
    public void calDoctorNames() {
        if (this.ChoiceBox_registerDepartment.getValue() != null) {
            this.ChoiceBox_doctor.setItems(Event.getInstance().getDoctorName(
                    this.ChoiceBox_registerDepartment.getValue()));
        }
    }


    @FXML
    public void calAge() {
        if (this.DatePicker_birth.getValue() != null) {
            LocalDate birthday = this.DatePicker_birth.getValue();
            LocalDate today = LocalDate.now();
            long duration = ChronoUnit.YEARS.between(birthday, today);
            this.TextField_age.setText(Long.toString(duration));
        }
    }

    @FXML
    public void toLogin() {
        MainApp.showLoginPage();
    }

    @FXML
    public void retreat() {
        MainApp.showRetreatPage();
    }

    @FXML
    public void pay() {
        MainApp.showPayPage();
    }

    @FXML
    public void clear() {
        MainApp.showRegisterPage();
    }

    @FXML
    public void refund() {
        MainApp.showRefundPage();
    }

    @FXML
    public void takeMedicine() {
        MainApp.showTakeMedicinePage();
    }

    @FXML
    public void setCheckBox_emergency(){
        //加急
    }

    @FXML
    public void setID(){
        textField_id.setDisable(false);
        button_reviseId.setDisable(true);
        button_yesId.setDisable(false);
    }

    @FXML
    public void confirmID(){
        int cID = Integer.parseInt(textField_id.getText());
        if (Event.getInstance().lookID(cID)){
            Patient patient = Event.getInstance().lookP(cID);
            TextField_name.setText(patient.getName());
            ObservableList<String> observableList=FXCollections.observableArrayList();
            observableList.add(patient.getSex());
            choiceBox_sex.setValue(patient.getSex());
            TextField_age.setText(String.valueOf(patient.getAge()));
            TextField_identityNumber.setText(patient.getIdentityNumber());
            TextField_address.setText(patient.getHomeAddress());
            String time = patient.getBirth();
            String[] strings = time.split("/");
            LocalDate localDate = LocalDate.of(Integer.parseInt(strings[0]),
                    Integer.parseInt(strings[1]),Integer.parseInt(strings[2]));
            DatePicker_birth.setValue(localDate);
        }
    }

    @FXML
    public void setButton_returnVisit() throws IOException {
        new MainApp().showReturnVisit();
    }
}
