package com.rentaloo.rentaloo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VehiculeController implements Initializable {

    @FXML
    private TableView CarListTable;
    @FXML
    AnchorPane CarlistPanel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigureTableCar();
    }

    public void ConfigureTableCar(){
        TableColumn<CarModel,String> Immatricule = new TableColumn<>("Immatricule");
        Immatricule.setCellValueFactory(new PropertyValueFactory<>("Immatricule"));
        Immatricule.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

        TableColumn<CarModel,String> Marque = new TableColumn<>("Marque");
        Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
        Marque.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

        TableColumn<CarModel,String> Model = new TableColumn<>("Model");
        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Model.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

        TableColumn<CarModel,String> Carburant = new TableColumn<>("Carburant");
        Carburant.setCellValueFactory(new PropertyValueFactory<>("Carburant"));
        Carburant.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

        TableColumn<CarModel,Integer> Year = new TableColumn<>("Year");
        Year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        Year.prefWidthProperty().bind(CarlistPanel.widthProperty().divide(5));

        CarListTable.getColumns().clear();
        CarListTable.getColumns().addAll(Immatricule,Marque,Model,Carburant,Year);

        CarListTable.setItems(FXCollections.observableArrayList(
                new CarModel("12345-A-49","Mercedes-Benz","Classe C 220","Diesel",2020),
                new CarModel("12346-A-49","Audi","A3","Diesel",2020),
                new CarModel("12347-A-49","Rnage Rover","Evoque","Diesel",2020),
                new CarModel("12348-A-49","Opel","Corsa","Diesel",2020)
        ));

    }


    public class CarModel{
        private String Immatricule,Marque,Model,Carburant;
        private int Year;
        public CarModel(String immat,String marque,String model,String carb,int year){
            this.Immatricule=immat;
            this.Marque=marque;
            this.Model=model;
            this.Carburant=carb;
            this.Year=year;
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
    }
}
