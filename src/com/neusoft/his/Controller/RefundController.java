package com.neusoft.his.Controller;

import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class RefundController {
    @FXML
    private Button Button_toLogin;

    @FXML
    private Button Button_refund;

    @FXML
    private Text Text_tip;

    @FXML
    private void initialize() {
        Event.buttonShadow(Button_refund);
        Event.buttonShadow(Button_toLogin);
    }

    @FXML
    public void setButton_toLogin() {
        MainApp.showLoginPage();
    }

    @FXML
    public void setButton_refund() {
        Text_tip.setText("退费成功");
    }
}
