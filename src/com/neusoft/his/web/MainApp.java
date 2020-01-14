package com.neusoft.his.web;

import com.neusoft.his.dao.DataAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage stage;
    private static BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("images/hos.png"));
        DataAccess.getInstance().dataInit();
        this.stage = primaryStage;
        this.stage.setTitle("东软云医院");
        this.initRootLayout();
        showLoginPage();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("src/data/mys.css")));
            this.stage.setScene(scene);
            stage.setResizable(false);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("LoginPage.fxml"));
            AnchorPane loginPage = loader.load();
            rootLayout.setCenter(loginPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showAdministratorPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("AdministratorPage.fxml"));
            AnchorPane administratorPage = loader.load();
            rootLayout.setCenter(administratorPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showRegisterPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RegisterPage.fxml"));
            AnchorPane registerPage = loader.load();
            rootLayout.setCenter(registerPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDoctorPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("DoctorPage.fxml"));
            AnchorPane doctorPage = loader.load();
            rootLayout.setCenter(doctorPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDrugstorePage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("DrugstorePage.fxml"));
            AnchorPane drugstorePage = loader.load();
            rootLayout.setCenter(drugstorePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showRetreatPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RetreatPage.fxml"));
            AnchorPane retreatPage = loader.load();
            rootLayout.setCenter(retreatPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPayPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PayPage.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showRefundPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Refund.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showTakeMedicinePage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TakeMedicine.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPrescribing() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Prescribing.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showCreatDesTree() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("CreatDesTree.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showSearchPTree() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("SearchPTree.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPatient() throws IOException {
        Parent target = FXMLLoader.load(getClass().getResource("PatientMedicalRecord.fxml"));
        Scene scene = new Scene(target);
        Stage stg=new Stage();
        stg.setTitle("就诊记录");
        stg.setHeight(280);
        stg.setWidth(430);
        stg.setResizable(false);
        stg.getIcons().add(new Image("images/hos.png"));
        stg.setScene(scene);
        stg.show();
    }

    public void showAlreadyDia() throws IOException {
        Parent target = FXMLLoader.load(getClass().getResource("AlreadyDia.fxml"));
        Scene scene = new Scene(target);
        Stage stg=new Stage();
        stg.setTitle("已诊病人");
        stg.setHeight(480);
        stg.setWidth(430);
        stg.setResizable(false);
        stg.getIcons().add(new Image("images/hos.png"));
        stg.setScene(scene);
        stg.show();
    }

    public void showReturnVisit() throws IOException {
        Parent target = FXMLLoader.load(getClass().getResource("ReturnVisit.fxml"));
        Scene scene = new Scene(target);
        Stage stg=new Stage();
        stg.setTitle("复诊");
        stg.setHeight(300);
        stg.setWidth(430);
        stg.setResizable(false);
        stg.getIcons().add(new Image("images/hos.png"));
        stg.setScene(scene);
        stg.show();
    }


    //.......................
    //
    private static Stage stage1 = new Stage();
    private static BorderPane rootLayout1;

    private static void initRootLayout1() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout1 = loader.load();
            Scene scene = new Scene(rootLayout1);
            //scene.getStylesheets().add(String.valueOf(getClass().getResource("src/data/mys.css")));
            stage1.getIcons().add(new Image("images/hos.png"));
            stage1.setTitle("东软云医院");
            stage1.setScene(scene);
            stage1.setHeight(820);
            stage1.setWidth(1088);
            stage1.setResizable(false);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showLoginPage1() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("LoginPage.fxml"));
            AnchorPane loginPage = loader.load();
            rootLayout1.setCenter(loginPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDoctorPage1() {
        try {
            initRootLayout1();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("DoctorPage.fxml"));
            AnchorPane doctorPage = loader.load();
            rootLayout1.setCenter(doctorPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPrescribing1() throws IOException {
        Parent target = FXMLLoader.load(MainApp.class.getResource("Prescribing.fxml"));
        Scene scene = new Scene(target);
        Stage stg=new Stage();
        stg.setTitle("开药");
        stg.setHeight(768);
        stg.setWidth(1024);
        stg.setResizable(false);
        stg.getIcons().add(new Image("images/hos.png"));
        stg.setScene(scene);
        stg.show();
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("Prescribing.fxml"));
//            AnchorPane payPage = loader.load();
//            rootLayout1.setCenter(payPage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void showPatientData() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PatientData.fxml"));
            AnchorPane payPage = loader.load();
            rootLayout1.setCenter(payPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
