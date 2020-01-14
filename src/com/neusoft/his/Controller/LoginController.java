package com.neusoft.his.Controller;

import com.neusoft.his.dao.DataAccess;
import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private TextField TextField_id;
    @FXML
    private PasswordField PasswordField_password;
    @FXML
    private Button Button_login;
    @FXML
    private Text Text_tip;

    @FXML
    private void initialize() {
        DataAccess.getInstance().readAllData();
        Event.buttonShadow(Button_login);
    }

    @FXML
    public void login() {

        int id = Integer.parseInt(this.TextField_id.getText());
        int password = Integer.parseInt(this.PasswordField_password.getText());
        switch (Event.getInstance().login(id, password)) {
            case 1:
                MainApp.showCreatDesTree();
                break;
            case 2:
                MainApp.showRegisterPage();
                break;
            case 3:
                MainApp.showDoctorPage1();//.............................
                break;
            case 4:
                MainApp.showDrugstorePage();
                break;
            case -1:
                Text_tip.setText("登陆失败");
        }
    }
}
