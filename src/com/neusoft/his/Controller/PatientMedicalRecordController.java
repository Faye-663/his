package com.neusoft.his.Controller;

import com.neusoft.his.service.Event;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class PatientMedicalRecordController {
    @FXML
    private ListView medicalRecord;

    @FXML
    private void initialize() {
        ObservableList o = Event.getInstance().getObservableList();
        medicalRecord.setItems(o);
    }
}
