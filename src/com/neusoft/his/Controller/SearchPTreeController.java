package com.neusoft.his.Controller;

import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class SearchPTreeController {
    @FXML
    private Button button_search;
    @FXML
    private TextField textField_target;
    @FXML
    private TextArea textArea_result;
    @FXML
    private Button button_return;

    @FXML
    private void initialize() {
        Event.buttonShadow(button_return);
        Event.buttonShadow(button_search);
    }

    @FXML
    private void setButton_search(){
        textArea_result.clear();
        String target = textField_target.getText();
        if (target.equals("")) {
            textArea_result.setText("请输入病种名称");
            return;
        }
        ArrayList<String> searchDP = Event.getInstance().searchDP(target);
        if (searchDP==null) {
            textArea_result.setText("未查到该病种，请输入正确病种");
            return;
        }
        for (String s:searchDP){
            textArea_result.appendText(s+"\n");
        }
    }

    @FXML
    private void setButton_return(){
        MainApp.showCreatDesTree();
    }

}