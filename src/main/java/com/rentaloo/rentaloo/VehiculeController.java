package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VehiculeController implements Initializable {

    @FXML
    private TableView CarListTable;
    @FXML
    AnchorPane CarMainPanel, CarlistPanel, AddCarPane, ImageInforamtionPane, CarInformationPane;

    @FXML
    private ImageView carImg;

    @FXML
    private ComboBox carburantList, GearList;


    @FXML
    private Button AddCarBtn, ViderBTN;

    @FXML
    private TextField txtImmat, txtMarque, txtModel, txtAnnee, txtRentPrice, txtNbrDoor;

    @FXML
    private CheckBox ACcheck;
    private final String DefaultImagePath = getClass().getResource("/Images/carVector.png").toString();
    private String selectedImage = "";

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private File SelectedFile = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ConfigureTableCar();
//        scheduler.scheduleAtFixedRate(this::configureTableCarAsync,0,10, TimeUnit.SECONDS);
        configureCarTableAsync();
        Timeline tenSecondsTimer = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            Platform.runLater(this::configureCarTableAsync);
        }));
        tenSecondsTimer.setCycleCount(Timeline.INDEFINITE);
        tenSecondsTimer.play();
        Resize();
        ConfigImageSelect();
        ConfigureComboBoxCarburan();
        ConfigureAddCarBtn();
        ConfigureViderBTN();
    }


    public void ConfigureViderBTN() {
        ViderBTN.setOnAction(event -> {
            Vider();
        });
    }

    public void ConfigureAddCarBtn() {
        AddCarBtn.setOnAction(event -> {


            if (txtModel.getText().isEmpty() ||
                    txtMarque.getText().isEmpty() ||
                    txtImmat.getText().isEmpty() ||
                    txtAnnee.getText().isEmpty() ||
                    txtRentPrice.getText().isEmpty() ||
                    txtNbrDoor.getText().isEmpty() ||
                    GearList.getSelectionModel().isEmpty() ||
                    carburantList.getSelectionModel().isEmpty()) {
                HelloApplication.ERRORAlert("Attention", "Champs vide", "Merci de remplir tous les champs !");
            } else {
                String query = String.format("INSERT INTO voiture (`IDAGENCE`, `MARQUE`, `MODELE`, `IMMATRICULE`, `ANNEE`, `CARBURANT`, `img`) VALUES ( '1', '%s', '%s', '%s', '%s', '%s','%s' );", txtMarque.getText(), txtModel.getText(), txtImmat.getText(), txtAnnee.getText(), carburantList.getSelectionModel().getSelectedItem().toString(), selectedImage);
                try {
                    DbContext.Execute(query);
                    ResultSet result = DbContext.Execute("SELECT IDVOITURE FROM voiture ORDER BY IDVOITURE DESC LIMIT 1;");
                    if (result.next()) {
                        int CarID = result.getInt(1);
                        query = "INSERT INTO `detailvoiture` (`idDetail`, `year`, `doors`, `ac`, `price`, `gear`, `fuel`, `idVoiture`) " +
                                "VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s');";

                        String year = txtAnnee.getText();
                        String doors = txtNbrDoor.getText();
                        String ac = ACcheck.isSelected() ? "T" : "F";
                        String price = txtRentPrice.getText();
                        String gear = GearList.getSelectionModel().getSelectedItem().toString();
                        String fuel = carburantList.getSelectionModel().getSelectedItem().toString();
                        String idVoiture = CarID + "";

                        query = String.format(query, year, doors, ac, price, gear, fuel, idVoiture);
                        DbContext.Execute(query);
                        HelloApplication.copyImageToDestination(SelectedFile);
                        HelloApplication.InformationAlert("Succes", "", "Voiture Ajouté avec success !");
                        Vider();

                    } else {
                        HelloApplication.InformationAlert("Erreur", "Erreur d'insertion", "une erreur s'est produite lors de l'insertion du véhicule !");
                    }

                } catch (SQLException e) {
                    HelloApplication.InformationAlert("Erreur", "Erreur d'insertion", "une erreur s'est produite lors de l'insertion du véhicule !");
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public void Vider() {
        txtMarque.setText("");
        txtModel.setText("");
        txtImmat.setText("");
        txtAnnee.setText("");
        carburantList.getSelectionModel().clearSelection();
        selectedImage = "";
        txtNbrDoor.setText("");
        ACcheck.setSelected(false);
        txtRentPrice.setText("");
        GearList.getSelectionModel().clearSelection();
        selectedImage = "";
        carImg.setImage(new Image(DefaultImagePath));
        SelectedFile = null;
    }

    public void ConfigImageSelect() {
        carImg.setImage(new Image(DefaultImagePath));

        carImg.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
            if (selectedFile != null) {
                this.SelectedFile = selectedFile;
                String imagePath = selectedFile.getPath();
                HelloApplication.InformationAlert("","",SelectedFile.getName());
                carImg.setImage(new Image(imagePath));
                selectedImage = selectedFile.getName();
            }
        });
    }

    public void ConfigureComboBoxCarburan() {
        carburantList.setItems(FXCollections.observableArrayList(
                "Diesel", "Essence", "Electrique", "Hybrid"
        ));
        GearList.setItems(FXCollections.observableArrayList(
                "AUTOMATIQUE", "MANUEL"
        ));
    }

//    public void ConfigureTableCar() {
//        TableColumn<CarModel, String> Immatricule = new TableColumn<>("Immatricule");
//        Immatricule.setCellValueFactory(new PropertyValueFactory<>("Immatricule"));
//        Immatricule.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//        TableColumn<CarModel, String> Marque = new TableColumn<>("Marque");
//        Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
//        Marque.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//        TableColumn<CarModel, String> Model = new TableColumn<>("Model");
//        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
//        Model.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//        TableColumn<CarModel, String> Carburant = new TableColumn<>("Carburant");
//        Carburant.setCellValueFactory(new PropertyValueFactory<>("Carburant"));
//        Carburant.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//        TableColumn<CarModel, Integer> Year = new TableColumn<>("Year");
//        Year.setCellValueFactory(new PropertyValueFactory<>("Year"));
//        Year.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//        CarListTable.getColumns().clear();
//        CarListTable.getColumns().addAll(Immatricule, Marque, Model, Carburant, Year);
//
//
//        List<CarModel> myCarList = new ArrayList<>();
//        try {
//            ResultSet rs = DbContext.Execute("select * from voiture");
//            while (rs.next()) {
//                myCarList.add(new CarModel(
//                        rs.getInt("IDVOITURE"),
//                        rs.getString("IMMATRICULE"),
//                        rs.getString("MARQUE"),
//                        rs.getString("MODELE"),
//                        rs.getString("CARBURANT"),
//                        rs.getInt("ANNEE"),
//                        rs.getString("img")
//                ));
//            }
//            ObservableList<CarModel> carList = FXCollections.observableArrayList(myCarList);
//            CarListTable.setItems(carList);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public CompletableFuture<Void> configureTableCarAsync() {
//        return CompletableFuture.runAsync(() -> {
//            TableColumn<CarModel, String> Immatricule = new TableColumn<>("Immatricule");
//            Immatricule.setCellValueFactory(new PropertyValueFactory<>("Immatricule"));
//            Immatricule.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//            TableColumn<CarModel, String> Marque = new TableColumn<>("Marque");
//            Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
//            Marque.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//            TableColumn<CarModel, String> Model = new TableColumn<>("Model");
//            Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
//            Model.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//            TableColumn<CarModel, String> Carburant = new TableColumn<>("Carburant");
//            Carburant.setCellValueFactory(new PropertyValueFactory<>("Carburant"));
//            Carburant.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//            TableColumn<CarModel, Integer> Year = new TableColumn<>("Year");
//            Year.setCellValueFactory(new PropertyValueFactory<>("Year"));
//            Year.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
//
//            CarListTable.getColumns().clear();
//            CarListTable.getColumns().addAll(Immatricule, Marque, Model, Carburant, Year);
//
//
//            List<CarModel> myCarList = new ArrayList<>();
//            try {
//                ResultSet rs = DbContext.Execute("select * from voiture");
//                while (rs.next()) {
//                    myCarList.add(new CarModel(
//                            rs.getInt("IDVOITURE"),
//                            rs.getString("IMMATRICULE"),
//                            rs.getString("MARQUE"),
//                            rs.getString("MODELE"),
//                            rs.getString("CARBURANT"),
//                            rs.getInt("ANNEE"),
//                            rs.getString("img")
//                    ));
//                }
//                ObservableList<CarModel> carList = FXCollections.observableArrayList(myCarList);
//                Platform.runLater(() -> CarListTable.setItems(carList));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }


    private void configureCarTableAsync() {
        CompletableFuture.supplyAsync(() -> {
            try {
                // query the database and return a list of CarModel objects
                List<CarModel> myCarList = new ArrayList<>();
                ResultSet rs = DbContext.Execute("select * from voiture");
                while (rs.next()) {
                    myCarList.add(new CarModel(
                            rs.getInt("IDVOITURE"),
                            rs.getString("IMMATRICULE"),
                            rs.getString("MARQUE"),
                            rs.getString("MODELE"),
                            rs.getString("CARBURANT"),
                            rs.getInt("ANNEE"),
                            rs.getString("img")
                    ));
                }
                return FXCollections.observableArrayList(myCarList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).thenAcceptAsync(myCarList -> {
            // create table columns for each field of CarModel
            TableColumn<CarModel, String> immatriculeCol = new TableColumn<>("Immatricule");
            immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatricule"));
            immatriculeCol.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

            TableColumn<CarModel, String> marqueCol = new TableColumn<>("Marque");
            marqueCol.setCellValueFactory(new PropertyValueFactory<>("Marque"));
            marqueCol.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));
            TableColumn<CarModel, String> modelCol = new TableColumn<>("Modèle");
            modelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));
            modelCol.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

            TableColumn<CarModel, String> carburantCol = new TableColumn<>("Carburant");
            carburantCol.setCellValueFactory(new PropertyValueFactory<>("Carburant"));
            carburantCol.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

            TableColumn<CarModel, Integer> anneeCol = new TableColumn<>("Année");
            anneeCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
            anneeCol.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

            // set the table columns and items
            CarListTable.getColumns().clear();
            CarListTable.getColumns().setAll(immatriculeCol, marqueCol, modelCol, carburantCol, anneeCol);
            CarListTable.setItems(myCarList);
        }, Platform::runLater);
    }


    public void Resize() {
        ImageInforamtionPane.prefWidthProperty().bind(AddCarPane.widthProperty().divide(2));
        CarInformationPane.prefWidthProperty().bind(AddCarPane.widthProperty().divide(2));
    }


    public class CarModel {
        private int idVoiture;
        private String Immatricule, Marque, Model, Carburant;
        private int Year;

        private String img;

        public CarModel(int idCar, String immat, String marque, String model, String carb, int year, String Img) {
            this.idVoiture = idCar;
            this.Immatricule = immat;
            this.Marque = marque;
            this.Model = model;
            this.Carburant = carb;
            this.Year = year;
            this.img = Img;
        }

        public String getImmatricule() {
            return Immatricule;
        }

        public void setImmatricule(String immatricule) {
            Immatricule = immatricule;
        }

        public String getMarque() {
            return Marque;
        }

        public void setMarque(String marque) {
            Marque = marque;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            Model = model;
        }

        public String getCarburant() {
            return Carburant;
        }

        public void setCarburant(String carburant) {
            Carburant = carburant;
        }

        public int getYear() {
            return Year;
        }

        public void setYear(int year) {
            Year = year;
        }

        public int getIdVoiture() {
            return idVoiture;
        }

        public void setIdVoiture(int idVoiture) {
            this.idVoiture = idVoiture;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
