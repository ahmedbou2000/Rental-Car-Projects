package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ClientsController implements Initializable {

    @FXML
    private TableView ClientTableList;

    @FXML
    private TextField txtNom;

    @FXML
    private AnchorPane AddClientPanel;

    @FXML
    private AnchorPane CarInformationPane;

    @FXML
    private AnchorPane CarInformationPane1;

    @FXML
    private AnchorPane ClientListPanel;

    @FXML
    private Button ViderBTN;

    @FXML
    private Button btnAddClient;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtTelephone;

    @FXML
    private TextField txtPassConfirm;

    @FXML
    private TextField txtPrenom;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigureClientTable();

        // definire un timer pour une execution infinie chaque 5s :
        Timeline tenSecondsTimer = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            Platform.runLater(this::ConfigureClientTable);
        }));
        tenSecondsTimer.setCycleCount(Timeline.INDEFINITE);
        tenSecondsTimer.play();
    }

    public void ConfigureClientTable(){
        TableColumn<ClientModel,String> NomClientCol = new TableColumn<>("Nom");
        NomClientCol.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        NomClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(5));

        TableColumn<ClientModel,String> PrenomClientCol = new TableColumn<>("Prenom");
        PrenomClientCol.setCellValueFactory(new PropertyValueFactory<>("PrenomClient"));
        PrenomClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(5));

        TableColumn<ClientModel,String> AdresseClientCol = new TableColumn<>("Adresse");
        AdresseClientCol.setCellValueFactory(new PropertyValueFactory<>("AdresseClient"));
        AdresseClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(5));

        TableColumn<ClientModel,String> TelClientCol = new TableColumn<>("Télephone");
        TelClientCol.setCellValueFactory(new PropertyValueFactory<>("TelClient"));
        TelClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(5));

        TableColumn<ClientModel,String> EmailClientCol = new TableColumn<>("Email");
        EmailClientCol.setCellValueFactory(new PropertyValueFactory<>("EmailClient"));
        EmailClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(5));

        ClientTableList.getColumns().addAll(NomClientCol,PrenomClientCol,AdresseClientCol,EmailClientCol,TelClientCol);

        CompletableFuture.supplyAsync(() -> {

                    List<ClientModel> clients = new ArrayList<>();

                    try {
                        // preparing the query to bring all the clients from the databse :
                        ResultSet result = DbContext.Execute("SELECT `IDCLIENT`, `NOM`, `PRENOM`, `ADRESSE`, `EMAIL`, `TEL` FROM `client`");
                        while (result.next()) {
                            // fill the client list from clients in the database:
                            clients.add(new ClientModel(
                                    String.valueOf(result.getInt(1)),
                                    result.getString(2),
                                    result.getString(3),
                                    result.getString(4),
                                    result.getString(5),
                                    result.getString(6)
                            ));
                        }
                        return FXCollections.observableArrayList(clients);
                    } catch (SQLException e) {
                        HelloApplication.InformationAlert("Erreur", "Erreur d'insertion", "une erreur s'est produite lors de l'insertion d'un Client !");
                        throw new RuntimeException(e);
                    }
                }).thenAcceptAsync(clients -> {
                        // Fill the table of clients by the list of clients prepared before :
                        ClientTableList.setItems(clients);
                }, Platform::runLater);



    }

    public void btnAddClient_Click(ActionEvent actionEvent) throws Exception {
        // ajouter le client au base de données :
        try {
            // preparer la requette
            String query = "INSERT INTO `client`(`IDCLIENT`, `NOM`, `PRENOM`, `ADRESSE`, `EMAIL`, `TEL`, `mdp`, `status`) " +
                    "VALUES  (NULL, '%s', '%s', '%s', '%s', '%s', '%s', 'Comfirmé');";

            // preparer les données entrés par l'utilisateur :
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String adresse = txtAdresse.getText();
            String email = txtEmail.getText();
            String telephone = txtTelephone.getText();
            String mdp1 = txtPass.getText();
            String mdp2 = txtPassConfirm.getText();

            // Verifier si les informations en bien eté entrées :
            if (nom.trim().isEmpty() ||
                    prenom.trim().isEmpty() ||
                    adresse.trim().isEmpty() ||
                    email.trim().isEmpty() ||
                    telephone.trim().isEmpty() ||
                    mdp1.trim().isEmpty() ||
                    mdp2.trim().isEmpty()
            ){
                // un ou plusieurs champs sont vides :
                HelloApplication.InformationAlert("Erreur", "Manque d'Information", "Merci de remplir tous les champs !");
                return ;
            } else if (!mdp1.equals(mdp2)) {
                // le mot de passe de comfirmation n'est pas identique au premier :
                HelloApplication.InformationAlert("Erreur", "Faux Informations", "le mot de passe de comfirmation n'est pas identique au premier !");
                return;
            }

            // Ajouter le client à la base de données :
            query = String.format(query, nom, prenom, adresse, email, telephone, mdp1);
            DbContext.Execute(query);
            HelloApplication.InformationAlert("Succes", "", "Client Ajouté avec success !");
            Vider();

        }catch (Exception e){
            HelloApplication.InformationAlert("Erreur", "Erreur d'insertion", "Nous n'avons pas pu enregistrer le client. Un problème a été rencontré. Merci de réessayer l'opération.");
            throw new Exception();
        }


    }

    private void Vider() {
        // vider les champs du formulaire  :
        txtNom.setText("");
        txtPrenom.setText("");
        txtAdresse.setText("");
        txtEmail.setText("");
        txtTelephone.setText("");
        txtPassConfirm.setText("");
        txtPass.setText("");

    }

    public void btnVider_Click(ActionEvent actionEvent) {
    }


    public static class ClientModel{
        private String idClient;
        private String NomClient,PrenomClient,AdresseClient,EmailClient,TelClient ;
        public ClientModel(String IdClient,String nomClient,String prenomClient,String adresseClient,String emailClient,String telClient){
            this.idClient=IdClient;
            this.NomClient = nomClient;
            this.PrenomClient = prenomClient;
            this.AdresseClient = adresseClient;
            this.TelClient= telClient;
            this.EmailClient=emailClient;
        }

        @Override
        public String toString() {
            return this.NomClient +" "+ this.PrenomClient;
        }

        public String getIdClient() {
            return idClient;
        }

        public void setIdClient(String idClient) {
            this.idClient = idClient;
        }

        public String getNomClient() {
            return NomClient;
        }

        public void setNomClient(String nomClient) {
            NomClient = nomClient;
        }

        public String getPrenomClient() {
            return PrenomClient;
        }

        public void setPrenomClient(String prenomClient) {
            PrenomClient = prenomClient;
        }

        public String getAdresseClient() {
            return AdresseClient;
        }

        public void setAdresseClient(String adresseClient) {
            AdresseClient = adresseClient;
        }

        public String getEmailClient() {
            return EmailClient;
        }

        public void setEmailClient(String emailClient) {
            EmailClient = emailClient;
        }

        public String getTelClient() {
            return TelClient;
        }

        public void setTelClient(String telClient) {
            TelClient = telClient;
        }


    }
}
