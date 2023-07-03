package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import javafx.util.StringConverter;
import org.mariadb.jdbc.client.Client;

import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @FXML
    private Tab AddLocation;

    @FXML
    private AnchorPane AddLocationContainer;

    @FXML
    private AnchorPane ClientInfoSide;

    @FXML
    private Label LbCIN1;

    @FXML
    private Label LbCIN11;

    @FXML
    private Label LbCIN111;

    @FXML
    private Label LbCIN12;

    @FXML
    private Label LbCIN121;

    @FXML
    private Tab RentalList;

    @FXML
    private TableView<StatsController.Rent> RentalListTable;

    @FXML
    private AnchorPane ReservationInfoSide;

    @FXML
    private Tab ReservationList;

    @FXML
    private TableView<StatsController.Rent> ReservationListTable;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnAojuterResevation;

    @FXML
    private ComboBox<ClientsController.ClientModel> cb2emeConducteur;

    @FXML
    private ComboBox<ClientsController.ClientModel> cbConducteurs1;

    @FXML
    private ComboBox<VehiculeController.CarModel> cbVehicules;

    @FXML
    private CheckBox check2emeConducteur;

    @FXML
    private CheckBox checkComfirme;

    @FXML
    private DatePicker dpDateDepart;

    @FXML
    private DatePicker dpDateFin;

    @FXML
    private TextField txtPrixLocation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // remplir les tables :
            ConfigureRentalListTable();
            ConfigureReservationListTable();

            // evenement pour le checkbox des deuxiemes conducteurs :
            CongigueCheck2emeConducteur_Event();

            // remplir les listes des conducteurs :
            RemplirCbConducteurs ();

            // remplir la liste des vehicules :
            RemplirCbVehicules();

            // configurer le format de date picker :
            ConfigueDatePickerFormat();


        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private void ConfigueDatePickerFormat() {
        dpDateDepart.setValue(LocalDate.now());
        dpDateFin.setValue(LocalDate.now());
        dpDateFin.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });


        dpDateDepart.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

    }

    private void CongigueCheck2emeConducteur_Event (){
        // event for the ckeckbox :
        check2emeConducteur.setOnMouseClicked(event -> {
            // mettre la liste des deuximes conducteurs visible :
            cb2emeConducteur.setDisable(!check2emeConducteur.isSelected());
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

        // remplir la table des locations :
        remplirLocationsTable();

    }

    //this method is a  configuration for Reservation List Table
    public void ConfigureReservationListTable() throws Exception {
        TableColumn<StatsController.Rent, String> numReservationCol = new TableColumn<>("N° Reservation");
        numReservationCol.setCellValueFactory(new PropertyValueFactory<>("NumReservation"));
        numReservationCol.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<StatsController.Rent, String> immatriculeCol = new TableColumn<>("Immatriculation");
        immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatriculation"));
        immatriculeCol.prefWidthProperty().bind(RentalListTable.widthProperty().divide(5));
        immatriculeCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<StatsController.Rent, String> NomComplet = new TableColumn<>("NomComplet");
        NomComplet.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        NomComplet.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));


        TableColumn<StatsController.Rent, String> DateDepart = new TableColumn<>("DateDepart");
        DateDepart.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DateDepart.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));

        TableColumn<StatsController.Rent, String> DateRetour = new TableColumn<>("DateRetour");
        DateRetour.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        DateRetour.prefWidthProperty().bind(ReservationListTable.widthProperty().divide(5));


        ReservationListTable.getColumns().clear();
        ReservationListTable.getColumns().addAll(numReservationCol, immatriculeCol, NomComplet, DateDepart, DateRetour);

        // remplir la table des reservations :
        remplirReservationsTable();

        //Mouse Click Event
        ReservationListTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Object mdl = ReservationListTable.getSelectionModel().getSelectedItem();

                StatsController.Rent reservationSelectionnee = (StatsController.Rent) mdl;
                if (HelloApplication.ConfirmationAlert("Confirmation", "Confirmé cette reservation ?", "Voulez-vous confirmé cette reservations N°"+reservationSelectionnee.getNumReservation()+" du client : "+ reservationSelectionnee.getNomComplet()+" ?"))
                {
                    try {
                        // Comfirmer la reservation du client (update the statut to 'accepté') :
                        DbContext.Execute("UPDATE `detail` SET `STATUT`='accepté' WHERE IDRESERVATION = "+reservationSelectionnee.getNumReservation()+"");

                        HelloApplication.InformationAlert("Success", "", "La reservation N° "+reservationSelectionnee.getNumReservation()+" est accepté !");

                        // refresh the data :
                        remplirReservationsTable();
                        remplirLocationsTable();
                    }catch (Exception e){
                        HelloApplication.ERRORAlert("Erreur", "Erreur de modification", "On a pas pu de modifier la reservation !!!");
                    }

                }
            }
        });
    }

    private void RemplirCbConducteurs() {
        try {

            List<ClientsController.ClientModel> clients = new ArrayList<>();
            ResultSet result = DbContext.Execute("SELECT `IDCLIENT`, `NOM`, `PRENOM`, `ADRESSE`, `EMAIL`, `TEL` FROM `client`");
            while (result.next()) {
                // fill the client list from clients in the database:
                ClientsController.ClientModel client = new ClientsController.ClientModel(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6));
                clients.add(client);
            }
            // set the data to the comboboxof conducteurs :
            cbConducteurs1.setItems(FXCollections.observableArrayList(clients));
            cb2emeConducteur.setItems((FXCollections.observableArrayList(clients)));

        }catch(Exception e){
        }

    }

    private void RemplirCbVehicules(){
        // remplir la liste des vihicules :
        try {
            // query the database and return a list of CarModel objects
            List<VehiculeController.CarModel> myCarList = new ArrayList<>();
            ResultSet rs = DbContext.Execute("select * from voiture ORDER BY marque");
            while (rs.next()) {
                myCarList.add(new VehiculeController.CarModel(
                        rs.getInt("IDVOITURE"),
                        rs.getString("IMMATRICULE"),
                        rs.getString("MARQUE"),
                        rs.getString("MODELE"),
                        rs.getString("CARBURANT"),
                        rs.getInt("ANNEE"),
                        rs.getString("img")
                ));
            }

            // set the data to the comboboxof conducteurs :
            cbVehicules.setItems(FXCollections.observableArrayList(myCarList));

        }catch(Exception e){
        }
    }

    private void remplirReservationsTable () throws Exception {
        // get data :
        try {
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


    }

    private void remplirLocationsTable () throws Exception {
        try {
            // get data :
            ResultSet result = DbContext.Execute("SELECT r.idReservation , v.IMMATRICULE , CONCAT(c.NOM, \" \" ,c.PRENOM) as 'NomComplet' , r.DATEDEBUT , r.DATEFIN \n" +
                    "FROM reservation r\n" +
                    "INNER JOIN detail d on d.IDRESERVATION = r.IDRESERVATION\n" +
                    "INNER JOIN voiture v on v.IDVOITURE = r.IDVOITURE \n" +
                    "INNER JOIN client c on c.IDCLIENT = r.IDCLIENT\n" +
                    "WHERE d.STATUT = 'accepté' " +
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

    public void btnAjouterReservation_Click(ActionEvent actionEvent)
    {
        try{
            // ajouter la reservation :
            if (cbConducteurs1.getSelectionModel().getSelectedIndex() == -1 ||
                    ( check2emeConducteur.isSelected() && cb2emeConducteur.getSelectionModel().getSelectedIndex() == -1  ) ||
                    dpDateDepart.getEditor().getText() == "" ||
                    dpDateFin.getEditor().getText() == "" ||
                    txtPrixLocation.getText() == ""){
                // des informations manquante :
                HelloApplication.InformationAlert("Validation de données", "Merci de remplir tous les champs !", "");
                return;
            }
            // get data :
            String idConducteur1 = cbConducteurs1.getSelectionModel().getSelectedItem() != null ? cbConducteurs1.getSelectionModel().getSelectedItem().getIdClient() : "";
            String idConducteur2 = check2emeConducteur.isSelected()? cb2emeConducteur.getSelectionModel().getSelectedItem() != null ? cb2emeConducteur.getSelectionModel().getSelectedItem().getIdClient(): "" : "NULL";
            String idVehicule = cbVehicules.getSelectionModel().getSelectedItem().getIdVoiture()+"";
            String dateDepart = dpDateDepart.getEditor().getText();
            String dateFin = dpDateFin.getEditor().getText();
            String isAccepted = checkComfirme.isSelected() ? "accepté" : "en attente" ;
            String prixLocation = txtPrixLocation.getText();

            DbContext.Execute("insert into reservation values (NULL, '"+idConducteur1+"', '"+idVehicule+"', '"+idConducteur2+"', CURRENT_DATE(),'"+dateDepart+"','"+dateFin+"')");
            ResultSet result =  DbContext.Execute("select idReservation from reservation order by idReservation desc limit 1");
            String idCurrentReservation = "";
            if (result.next()){
                // get : reservation ajouté maintenant :
                idCurrentReservation = result.getString(1);
            }
            DbContext.Execute("insert into detail values (NULL, '"+idCurrentReservation+"', '"+prixLocation+"', '"+isAccepted+"')");

            HelloApplication.InformationAlert("Success", "Reservation est bien été ajouté !", "");
            if (checkComfirme.isSelected()){
                remplirLocationsTable();
            }else{
                remplirReservationsTable();
            }

            // vider les champs :
            vider();

        }catch (Exception e){
            HelloApplication.ERRORAlert("Erreur", "Erreur d'ajouter une Reservation", e.getMessage());
        }

    }

    public void btnAnnuler_Click(ActionEvent actionEvent) {
        vider();
    }

    private void vider() {
        // vider les champs de la page :
        //clear combobox

        cbConducteurs1.getSelectionModel().clearSelection();
        cb2emeConducteur.getSelectionModel().clearSelection();

        // check 2eme conducteur par defaut :
        check2emeConducteur.setSelected(false);
        cb2emeConducteur.setDisable(true);

        // clear vehicules selections :
        cbVehicules.getSelectionModel().clearSelection();

        // clear date selections :
        dpDateDepart.setValue(LocalDate.now());
        dpDateFin.setValue(LocalDate.now());

        // check comfirme par defaut :
        checkComfirme.setSelected(true);

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
