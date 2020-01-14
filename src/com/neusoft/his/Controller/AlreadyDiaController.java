package com.neusoft.his.Controller;

import com.neusoft.his.service.Event;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class AlreadyDiaController {

    @FXML
    private ListView listView_alreadQ;

    @FXML
    private void initialize() {
        ObservableList<String> patientName = Event.getInstance().getAlreadyTreat();
        listView_alreadQ.setItems(patientName);
    }

}
