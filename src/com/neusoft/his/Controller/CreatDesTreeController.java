package com.neusoft.his.Controller;


import com.neusoft.his.service.DiseaseType;
import com.neusoft.his.service.Event;
import com.neusoft.his.web.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class CreatDesTreeController {
    @FXML
    private TextArea textArea;
    @FXML
    private Button button_creat;
    @FXML
    private Button button_clear;
    @FXML
    private Text yes;
    @FXML
    private Text tip;
    @FXML
    private Button button_search;
    @FXML
    private Button button_searchP;
    @FXML
    private Button button_return;

    @FXML
    private void initialize() {
        Event.buttonShadow(button_clear);
        Event.buttonShadow(button_creat);
        Event.buttonShadow(button_return);
        Event.buttonShadow(button_search);
        Event.buttonShadow(button_searchP);
    }

    @FXML
    public void creatTree() throws IOException {
        String s = textArea.getText();
        String[] strings = s.split("\n");
        int temp = Event.creatTree(strings);
        if (temp == 0) yes.setText("已创建树，勿重复创建");
        else yes.setText("创建成功");
    }

    @FXML
    private void clear() {
        yes.setText("");
        tip.setText("请输入：节点名称，父节点名称。" +
                "（用逗号分隔，如果是根节点，则不需要输入父节点名称）");
        textArea.clear();
    }

    @FXML
    private void outPutD() {
        tip.setText("");
        yes.setText("");
        textArea.clear();
        DiseaseType root = Event.getRoot();
        ArrayList<String> arrayList = Event.outPut(root);
        for (String s : arrayList) {
            textArea.appendText(s + "\n");
        }
    }

    @FXML
    private void outPutP() {
        yes.setText("");
        MainApp.showSearchPTree();
    }

    @FXML
    private void setButton_return() {
        yes.setText("");
        MainApp.showLoginPage();
    }

}
