package com.neusoft.his.Controller;

import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TakeMedicine {
    @FXML
    private Button Button_toLogin;

    @FXML
    private Button Button_takeMedicine;

    @FXML
    private Text Text_asd;

    @FXML
    private void initialize() {
        Event.buttonShadow(Button_toLogin);
        Event.buttonShadow(Button_takeMedicine);
    }

    @FXML
    public void setButton_toLogin() {
        MainApp.showLoginPage();
    }

    @FXML
    public void setButton_takeMedicine() {
        Text_asd.setText("已审方，请到一楼药房窗口取药");
    }
}
