package com.rentaloo.rentaloo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Stage loginStage ;

    @FXML
    private AnchorPane loginPanel;
//    @FXML

//    private VBox login1panel;
//    @FXML




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//// create a new Image object with the path to your image file
//        Image image = new Image(getClass().getResource("/Images/fiesta.jpg").toString());
//
//// create a new BackgroundImage object with the image, sizing options, and other settings
//        BackgroundImage backgroundImage = new BackgroundImage(
//                image,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                new BackgroundSize(1.0, 1.0, true, true, false, false)
//        );
//
//// set the background of the AnchorPane to the new BackgroundImage
//        loginPanel.setBackground(new Background(backgroundImage));
    }

    public void setWidth(){


    }


    public void BtnQuitter(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmer");
        alert.setContentText("Quitter l'application ?");
        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes){
            Platform.exit();
        }
    }

    public void ConnectionBtn(ActionEvent actionEvent) throws IOException {
        ShowDashboardStage();

    }


    public void ShowDashboardStage() throws IOException {
        Stage dashboard = new Stage();
        FXMLLoader loader = new FXMLLoader(Dashboard.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(loader.load(),800,600);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.DOWN && event.isMetaDown()) {
                event.consume();
            }
        });

        dashboard.setScene(scene);
        dashboard.setResizable(false);
        dashboard.setTitle("Rentaloo dashboard");
        dashboard.initStyle(StageStyle.UNDECORATED);
        Dashboard dashboardController = loader.getController();

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        dashboard.setX(primaryScreenBounds.getMinX());
        dashboard.setY(primaryScreenBounds.getMinY());
        dashboard.setWidth(primaryScreenBounds.getWidth());
        dashboard.setHeight(primaryScreenBounds.getHeight());

        dashboard.setOnShown(event->{
            dashboardController.Resizing();
        });
        loginStage = ((Stage)this.loginPanel.getScene().getWindow());
        loginStage.hide();
        dashboard.showAndWait();
        loginStage.show();

    }

    public void ShowStage(){
    loginStage.show();
    }
}