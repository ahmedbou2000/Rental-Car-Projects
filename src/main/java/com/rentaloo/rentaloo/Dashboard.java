package com.rentaloo.rentaloo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.text.Element;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

public class Dashboard implements Initializable {

    private List<Button> btnList;
    public Timer timer;
    public int a = 0;

    @FXML
    private VBox SiderPane, SiderPane1, SiderPane2;
    @FXML
    private AnchorPane DashboardPane, ContentPane, TopContentPane;
    @FXML
    private Label titleLabelSider, DateTimeLabel;
    @FXML
    private Button TableauBordbtn, deconexionBTN, reservationBTN, carBTN, clientBTN, NotificationBtn;

    @Override
    public void initialize(URL url, ResourceBundle rvesourceBundle) {
        btnList = new ArrayList<Button>() {
            {
                add(TableauBordbtn);
                add(reservationBTN);
                add(carBTN);
                add(clientBTN);
            }
        };

        //Configuration on date and time
        updateDateTime(DateTimeLabel);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateDateTime(DateTimeLabel);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        //resizing Element
        Resizing();

        //set Image to buttons
        setImageToBTN("/Images/Icons/logout.png", deconexionBTN);
        setImageToBTN("/Images/Icons/dashboard.png", TableauBordbtn);
        setImageToBTN("/Images/Icons/reservation.png", reservationBTN);
        setImageToBTN("/Images/Icons/car.png", carBTN);
        setImageToBTN("/Images/Icons/clients.png", clientBTN);


        ConfigNotificationBtn();
        ConfigDeconnexionBTN();
        ConfigureButtons();


        try {
            ShowComponent("stats.fxml");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void ConfigureButtons() {
        for (Button btn : btnList) {
            setActiveBtn(btn, btnList.indexOf(btn));

        }


    }

    public void Resizing() {
        SiderPane.prefWidthProperty().bind(DashboardPane.widthProperty().multiply(0.15));
        titleLabelSider.prefHeightProperty().bind(SiderPane.heightProperty().multiply(0.2));
        titleLabelSider.prefWidthProperty().bind(SiderPane.widthProperty().multiply(1));
        SiderPane1.prefWidthProperty().bind(SiderPane.widthProperty());
        SiderPane1.prefHeightProperty().bind(SiderPane.heightProperty().multiply(0.75));
        SiderPane2.prefHeightProperty().bind(SiderPane.heightProperty().multiply(0.05));
        SiderPane2.prefWidthProperty().bind(SiderPane.widthProperty().multiply(1));
        TableauBordbtn.prefWidthProperty().bind(SiderPane1.widthProperty());
        deconexionBTN.prefWidthProperty().bind(SiderPane2.widthProperty());
        deconexionBTN.prefHeightProperty().bind(SiderPane2.heightProperty());
        reservationBTN.prefWidthProperty().bind(SiderPane1.widthProperty());
        carBTN.prefWidthProperty().bind(SiderPane1.widthProperty());
        clientBTN.prefWidthProperty().bind(SiderPane1.widthProperty());
        ContentPane.prefWidthProperty().bind(DashboardPane.widthProperty().multiply(0.85));
        ContentPane.prefHeightProperty().bind(DashboardPane.heightProperty().multiply(0.95));
        TopContentPane.prefWidthProperty().bind(DashboardPane.widthProperty().multiply(0.85));
        TopContentPane.prefHeightProperty().bind(DashboardPane.heightProperty().multiply(0.05));

    }

    public void ConfigDeconnexionBTN() {

        deconexionBTN.setOnAction(event -> {
            if (HelloApplication.ConfirmationAlert("confirmer", "", "Quitter ?"))
                ((Stage) this.deconexionBTN.getScene().getWindow()).close();
            ;
        });
    }

    public void ConfigNotificationBtn() {
        ImageView view = setImageToBTN("/Images/Icons/notification.png", NotificationBtn);
        NotificationBtn.setText("");
        view.setFitHeight(25);
        view.setFitWidth(25);

    }

    public void updateDateTime(Label timeLabel) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        timeLabel.setText(formattedDate);
    }


    public ImageView setImageToBTN(String imagePATH, Button btn) {
        Image img = new Image(getClass().getResource(imagePATH).toString());
        ImageView view = new ImageView(img);
        view.setFitWidth(20); // set the width of the icon to 16 pixels
        view.setFitHeight(20); // set the height of the icon to 16 pixels
        btn.setGraphic(view);
        return view;
    }


    public void removeActiveClassBtn() {
        for (Button btn : btnList) {
            btn.getStyleClass().remove("ActiveDashBtn");
            btn.getStyleClass().add("DashBtn");
        }
    }

    public void setActiveBtn(Button btn, int index) {
        btn.setOnAction(event -> {
            removeActiveClassBtn();
            btn.getStyleClass().add("ActiveDashBtn");

            switch (index) {
                case 0:
                    try {
                        ShowComponent("stats.fxml");
                    } catch (Exception ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case 1:
                    try {
                        ShowComponent("reservation.fxml");
                    } catch (Exception ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case 2:
                    try {
                        ShowComponent("vehicules.fxml");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        ShowComponent("clients.fxml");
                    } catch (IOException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
            }


        });
    }

//    public void ConfigureFirstPage() throws IOException {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("stats.fxml"));
//            AnchorPane statsComponent = loader.load();
//            ContentPane.getChildren().removeAll();
//            ContentPane.getChildren().add(statsComponent);
//            statsComponent.prefHeightProperty().bind(ContentPane.heightProperty());
//            statsComponent.prefWidthProperty().bind(ContentPane.widthProperty());
//
//        } catch (Exception exception) {
//            System.out.println(exception.getLocalizedMessage());
//        }
//    }

    public void ShowComponent(String fxmlPath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane Controller = loader.load();
            ContentPane.getChildren().clear();
            ContentPane.getChildren().add(Controller);
            (Controller).prefHeightProperty().bind(ContentPane.heightProperty());
            (Controller).prefWidthProperty().bind(ContentPane.widthProperty());

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }


}
