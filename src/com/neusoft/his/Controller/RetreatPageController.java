package com.neusoft.his.Controller;

import com.neusoft.his.web.MainApp;
import com.neusoft.his.service.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RetreatPageController {

    @FXML
    private TextField TextField_receiptNumber;

    @FXML
    private Button Button_ok;

    @FXML
    private Text Text_tips;

    @FXML
    private void initialize() {
        Event.buttonShadow(Button_ok);
    }

    @FXML
    public void determine() {
        Event.getInstance().removePatient(Integer.valueOf(this.TextField_receiptNumber.getText()));
        Text_tips.setText("退号成功");
    }

    @FXML
    public void toRegister() {
        MainApp.showRegisterPage();
    }

    @FXML
    public void pay() {
        MainApp.showPayPage();
    }

    @FXML
    public void toLogin() {
        MainApp.showLoginPage();
    }
}
