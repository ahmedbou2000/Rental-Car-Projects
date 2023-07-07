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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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



    public static void copyImageToDestination(File sourceImage) {
        // Destination folder path
        String destinationFolderPath = "C:\\wamp64\\www\\restfulAPI\\images\\rental cars";

        // Create the destination folder if it doesn't exist
        File destinationFolder = new File(destinationFolderPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        try {
            // Copy the image file to the destination folder
            Path destinationPath = destinationFolder.toPath().resolve(sourceImage.getName());
            Files.copy(sourceImage.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image copied successfully!");
//            displayInformationAlert("Image Copy", "Success", "Image copied successfully!");
        } catch (IOException e) {
            System.out.println("Error copying image: " + e.getMessage());
//            displayErrorAlert("Image Copy Error", "Error", "Error copying image: " + e.getMessage());
        }
    }
}