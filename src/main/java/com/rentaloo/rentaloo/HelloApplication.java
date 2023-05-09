package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 370, 450);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);


        HelloController controller = fxmlLoader.getController();


        stage.setOnShown(event -> {
            controller.setWidth();
        });
        new DbContext();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void InformationAlert(String alertTitle, String headerText, String Content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(headerText);
        alert.setContentText(Content);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(600);
        alert.show();
    }

    public static void ERRORAlert(String alertTitle, String headerText, String Content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setHeaderText(headerText);
        alert.setContentText(Content);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(600);
        alert.show();
    }




    public static boolean ConfirmationAlert(String alertTitle, String headerText, String Content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(headerText);
        alert.setContentText(Content);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(600);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}