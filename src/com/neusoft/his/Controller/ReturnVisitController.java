package com.neusoft.his.Controller;

import com.neusoft.his.dao.DataAccess;
import com.neusoft.his.service.Doctor;
import com.neusoft.his.service.Event;
import com.neusoft.his.service.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class ReturnVisitController {

    @FXML
    private Button button_ok;

    @FXML
    private TextField textField_id;

    @FXML
    private Text text_tip;

    @FXML
    private void initialize() {
        Event.buttonShadow(button_ok);
    }

    @FXML
    public void setButton_ok(){
        text_tip.setText("");
        String s = textField_id.getText();
        if (s == null){
            text_tip.setText("请输入病历号！");
            return;
        }
        int id = Integer.parseInt(s);

        for (Patient p: DataAccess.getInstance().getPatients()){
            if (id == p.getId()){
                if (!p.isReturnVisit()){
                    text_tip.setText("您不属于检查后复诊病人");
                    return;
                }
                 int did = p.getMedicalRecord().getDoctorID();
                 for (Doctor d:DataAccess.getInstance().getDoctors()){
                     if (did == d.getId()){
                         Event.getInstance().retrunVisit(p,d.getWaitTreatq());
                         text_tip.setText("成功！");
                         break;
                     }
                 }
                 return;
            }
        }
        text_tip.setText("请输入正确的病历号！");
    }//er fen


}
