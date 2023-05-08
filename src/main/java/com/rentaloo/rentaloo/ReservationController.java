package com.rentaloo.rentaloo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private TableView RentalListTable, ReservationListTable;
    @FXML
    private AnchorPane ReservationInfoSide,ClientInfoSide,AddLocationContainer;
    @FXML
    TextField TxtNom,TxtPrenom,TxtAdresse,TxtEmail,TxtCIN,TxtNPermis;
    @FXML
    Label LbPERMIS,LbPrenom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigureRentalListTable();
        ConfigureReservationListTable();
        Resize();


    }


    // this method is a configuration for rental List Table
    public void ConfigureRentalListTable() {
        TableColumn<StatsController.Rent, String> immatriculeCol = new TableColumn<>("Immatriculation");
        immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatriculation"));
        immatriculeCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(4));
        immatriculeCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> NomCompletCol = new TableColumn<>("NomComplet");
        NomCompletCol.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        NomCompletCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(4));
        NomCompletCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> DepartCol = new TableColumn<>("DateDepart");
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DepartCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(4));
        DepartCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> RetourCol = new TableColumn<>("DateRetour");
        RetourCol.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        RetourCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(4));
        RetourCol.setMaxWidth(Double.MAX_VALUE);

        RentalListTable.getColumns().clear();
        RentalListTable.getColumns().addAll(immatriculeCol, NomCompletCol, DepartCol, RetourCol);


        RentalListTable.setItems(FXCollections.observableArrayList(
                new StatsController.Rent("12345-A-49", "BOUKHRISS MOHAMED", "01/05/2023", "12/05/2023"),
                new StatsController.Rent("12346-A-49", "BENAISSATI AHMED", "02/05/2023", "21/05/2023"),
                new StatsController.Rent("12347-A-49", "CHIKOUR KAMEL", "06/05/2023", "30/05/2023"),
                new StatsController.Rent("12348-A-49", "BELAMIN JAWAD", "02/05/2023", "13/05/2023"),
                new StatsController.Rent("12345-A-49", "BOUKHRISS MOHAMED", "01/05/2023", "12/05/2023"),
                new StatsController.Rent("12346-A-49", "BENAISSATI AHMED", "02/05/2023", "21/05/2023"),
                new StatsController.Rent("12347-A-49", "CHIKOUR KAMEL", "06/05/2023", "30/05/2023"),
                new StatsController.Rent("12348-A-49", "BELAMIN JAWAD", "02/05/2023", "13/05/2023")

        ));
    }

//this method is a  configuration for Reservation List Table
    public void ConfigureReservationListTable() {
        TableColumn<ReservationModel, String> immatCol = new TableColumn<>("Immatricule");
        immatCol.setCellValueFactory(new PropertyValueFactory<>("Immatricule"));
        immatCol.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<ReservationModel, String> Nom = new TableColumn<>("Nom");
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Nom.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<ReservationModel, String> Prenom = new TableColumn<>("Prenom");
        Prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        Prenom.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<ReservationModel, String> DateDepart = new TableColumn<>("DateDepart");
        DateDepart.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DateDepart.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<ReservationModel, String> DateRetour = new TableColumn<>("DateRetour");
        DateRetour.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        DateRetour.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));



        ReservationListTable.getColumns().clear();
        ReservationListTable.getColumns().addAll(immatCol, Nom, Prenom, DateDepart, DateRetour);

        ReservationListTable.setItems(FXCollections.observableArrayList(
                new ReservationModel(1,"12345-A-49", "BOUKHRISS", "MOHAMED", "01/05/2023", "12/05/2023"),
                new ReservationModel(2,"12345-A-49", "BOUKHRISS", "MOHAMED", "01/05/2023", "12/05/2023"),
                new ReservationModel(3,"12345-A-49", "BOUKHRISS", "MOHAMED", "01/05/2023", "12/05/2023")
        ));


        //Mouse Click Event
        ReservationListTable.setOnMouseClicked(event->{
            if(event.getButton()== MouseButton.SECONDARY){
                ReservationModel mdl = ((ReservationModel)ReservationListTable.getSelectionModel().getSelectedItem());
                HelloApplication.ConfirmationAlert("Confirmation","Confirmer la réservation",String.format("%s %s a effectué une demande de reservation\ndu véhicule sous immatricule %s\ndu %s à %s ",mdl.Prenom,mdl.Nom,mdl.Immatricule,mdl.DateDepart,mdl.DateRetour));
            }
        });
    }

    //This method is for resizing all Controlls
    public void Resize(){
        ClientInfoSide.prefWidthProperty().bind(AddLocationContainer.widthProperty().divide(2));
        ReservationInfoSide.prefWidthProperty().bind(AddLocationContainer.widthProperty().divide(2));
        TxtNom.prefWidthProperty().bind(ClientInfoSide.widthProperty().multiply(0.43));
        TxtPrenom.prefWidthProperty().bind(ClientInfoSide.widthProperty().multiply(0.43));
        TxtCIN.prefWidthProperty().bind(ClientInfoSide.widthProperty().multiply(0.43));
        TxtNPermis.prefWidthProperty().bind(ClientInfoSide.widthProperty().multiply(0.43));
        LbPrenom.prefWidthProperty().bind(TxtNPermis.widthProperty());
        LbPERMIS.prefWidthProperty().bind(TxtNPermis.widthProperty());
    }

    //this is a class Model for showing data in Reservation Table
    public class ReservationModel {
        private int IdReservation;
        private String Immatricule;
        private String Nom;
        private String Prenom;
        private String DateDepart;
        private String DateRetour;



        public ReservationModel(int idReservation ,String immat, String nom, String prenom, String dateDepart, String dateRetour) {
            this.IdReservation = idReservation;
            this.Immatricule = immat;
            this.Nom = nom;
            this.Prenom = prenom;
            this.DateDepart = dateDepart;
            this.DateRetour = dateRetour;

        }

        public String getImmatricule() {
            return Immatricule;
        }

        public void setImmatricule(String immatricule) {
            Immatricule = immatricule;
        }

        public String getNom() {
            return Nom;
        }

        public void setNom(String nom) {
            Nom = nom;
        }

        public String getPrenom() {
            return Prenom;
        }

        public void setPrenom(String prenom) {
            Prenom = prenom;
        }

        public String getDateDepart() {
            return DateDepart;
        }

        public void setDateDepart(String dateDepart) {
            DateDepart = dateDepart;
        }

        public String getDateRetour() {
            return DateRetour;
        }

        public void setDateRetour(String dateRetour) {
            DateRetour = dateRetour;
        }


    }
}
