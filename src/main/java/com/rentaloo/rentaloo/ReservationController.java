package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private TableView RentalListTable, ReservationListTable;
    @FXML
    private AnchorPane ReservationInfoSide, ClientInfoSide, AddLocationContainer;
    @FXML
    TextField TxtNom, TxtPrenom, TxtAdresse, TxtEmail, TxtCIN, TxtNPermis;
    @FXML
    Label LbPERMIS, LbPrenom;

    @FXML
    Button btnAojuterResevation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ConfigureRentalListTable();
            ConfigureReservationListTable();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
            Resize();

        btnAojuterResevation.setOnAction(event -> {

            String nom = "";
            ResultSet rs = null;
            try {
                rs = DbContext.Execute("select * from client");
                while (rs.next()) {
                    nom = rs.getString("PRENOM");
                    HelloApplication.InformationAlert("", "", nom);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });
    }


    // this method is a configuration for rental List Table
    public void ConfigureRentalListTable() throws Exception {
        TableColumn<StatsController.Rent, String> numReservationCol = new TableColumn<>("N° Reservation");
        numReservationCol.setCellValueFactory(new PropertyValueFactory<>("NumReservation"));
        numReservationCol.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));


        TableColumn<StatsController.Rent, String> immatriculeCol = new TableColumn<>("Immatriculation");
        immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatriculation"));
        immatriculeCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(5));
        immatriculeCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> NomCompletCol = new TableColumn<>("NomComplet");
        NomCompletCol.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        NomCompletCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(5));
        NomCompletCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> DepartCol = new TableColumn<>("DateDepart");
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DepartCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(5));
        DepartCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> RetourCol = new TableColumn<>("DateRetour");
        RetourCol.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        RetourCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(5));
        RetourCol.setMaxWidth(Double.MAX_VALUE);

        RentalListTable.getColumns().clear();
        RentalListTable.getColumns().addAll(numReservationCol,immatriculeCol, NomCompletCol, DepartCol, RetourCol);
        try {
            // get data :
            ResultSet result = DbContext.Execute("SELECT r.idReservation , v.IMMATRICULE , CONCAT(c.NOM, \" \" ,c.PRENOM) as 'NomComplet' , r.DATEDEBUT , r.DATEFIN \n" +
                    "FROM reservation r\n" +
                    "INNER JOIN detail d on d.IDRESERVATION = r.IDRESERVATION\n" +
                    "INNER JOIN voiture v on v.IDVOITURE = r.IDVOITURE \n" +
                    "INNER JOIN client c on c.IDCLIENT = r.IDCLIENT\n" +
                    "WHERE d.STATUT = 'comfirmé' " +
                    "ORDER BY r.idReservation DESC");

            List<StatsController.Rent> listLocations = new ArrayList<>();
            while (result.next()) {
                listLocations.add(new StatsController.Rent(
                        result.getString("idReservation"),
                        result.getString("immatricule"),
                        result.getString("NomComplet"),
                        result.getString("dateDebut"),
                        result.getString("dateFin")
                ));
            }
                // set the data to the table :
                RentalListTable.setItems(FXCollections.observableArrayList(listLocations));

        }catch(Exception e){
            throw new Exception();
        }
    }

    //this method is a  configuration for Reservation List Table
    public void ConfigureReservationListTable() throws Exception {
        TableColumn<ReservationModel, String> numReservationCol = new TableColumn<>("N° Reservation");
        numReservationCol.setCellValueFactory(new PropertyValueFactory<>("NumReservation"));
        numReservationCol.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<StatsController.Rent, String> immatriculeCol = new TableColumn<>("Immatriculation");
        immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatriculation"));
        immatriculeCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(5));
        immatriculeCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<ReservationModel, String> NomComplet = new TableColumn<>("NomComplet");
        NomComplet.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        NomComplet.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));


        TableColumn<ReservationModel, String> DateDepart = new TableColumn<>("DateDepart");
        DateDepart.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DateDepart.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<ReservationModel, String> DateRetour = new TableColumn<>("DateRetour");
        DateRetour.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        DateRetour.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));


        ReservationListTable.getColumns().clear();
        ReservationListTable.getColumns().addAll(numReservationCol, immatriculeCol, NomComplet, DateDepart, DateRetour);

        try {
            // get data :
            ResultSet result = DbContext.Execute("SELECT r.idReservation , v.IMMATRICULE , CONCAT(c.NOM, \" \" ,c.PRENOM) as 'NomComplet' , r.DATEDEBUT , r.DATEFIN \n" +
                    "FROM reservation r\n" +
                    "INNER JOIN detail d on d.IDRESERVATION = r.IDRESERVATION\n" +
                    "INNER JOIN voiture v on v.IDVOITURE = r.IDVOITURE \n" +
                    "INNER JOIN client c on c.IDCLIENT = r.IDCLIENT\n" +
                    "WHERE d.STATUT = 'en attente' " +
                    "ORDER BY r.idReservation DESC");

            List<StatsController.Rent> listReservations = new ArrayList<>();
            while (result.next()) {
                listReservations.add(new StatsController.Rent(
                        result.getString("idReservation"),
                        result.getString("immatricule"),
                        result.getString("NomComplet"),
                        result.getString("dateDebut"),
                        result.getString("dateFin")
                ));
            }
            // set the data to the table :
            ReservationListTable.setItems(FXCollections.observableArrayList(listReservations));

        }catch(Exception e){
            throw new Exception();
        }

        //Mouse Click Event
        ReservationListTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Object mdl = ReservationListTable.getSelectionModel().getSelectedItem();

                StatsController.Rent reservationSelectionnee = (StatsController.Rent) mdl;
                if (HelloApplication.ConfirmationAlert("Confirmation", "Confirmé cette reservation ?", "Voulez-vous confirmé cette reservations du client : "+ reservationSelectionnee.getNomComplet()+" ?")) {
                    // Comfirmer la reservation du client :


                    HelloApplication.InformationAlert("Success", "", "Reservation Comfirmé !");
                }
            }
        });
    }

    //This method is for resizing all Controlls
    public void Resize() {
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


        public ReservationModel(int idReservation, String immat, String nom, String prenom, String dateDepart, String dateRetour) {
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
