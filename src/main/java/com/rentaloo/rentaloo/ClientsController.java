package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private TextField txtEmail1;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtPassConfirm;

    @FXML
    private TextField txtPrenom;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigureClientTable();
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

            // Fill the table of clients by the list of clients prepared before :
            ClientTableList.setItems(FXCollections.observableArrayList(clients));
        } catch (SQLException e) {
            HelloApplication.InformationAlert("Erreur", "Erreur d'insertion", "une erreur s'est produite lors de l'insertion d'un Client !");
            throw new RuntimeException(e);
        }
        //ClientTableList.setItems();


    }



    public class ClientModel{
        private String idClient;
        private String NomClient,PrenomClient,AdresseClient,EmailClient,TelClient;
        public ClientModel(String IdClient,String nomClient,String prenomClient,String adresseClient,String emailClient,String telClient){
            this.idClient=IdClient;
            this.NomClient = nomClient;
            this.PrenomClient = prenomClient;
            this.AdresseClient = adresseClient;
            this.TelClient= telClient;
            this.EmailClient=emailClient;
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
