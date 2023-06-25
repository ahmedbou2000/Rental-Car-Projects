package com.rentaloo.rentaloo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    private TableView ClientTableList;

    @FXML
    private TextField txtNom;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigureClientTable();
    }

    public void ConfigureClientTable(){
        TableColumn<ClientModel,String> NomClientCol = new TableColumn<>("Nom");
        NomClientCol.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        NomClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(6));

        TableColumn<ClientModel,String> PrenomClientCol = new TableColumn<>("Prenom");
        PrenomClientCol.setCellValueFactory(new PropertyValueFactory<>("PrenomClient"));
        PrenomClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(6));

        TableColumn<ClientModel,String> NPermisCol = new TableColumn<>("N°Permis");
        NPermisCol.setCellValueFactory(new PropertyValueFactory<>("NPermis"));
        NPermisCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(6));

        TableColumn<ClientModel,String> AdresseClientCol = new TableColumn<>("Adresse");
        AdresseClientCol.setCellValueFactory(new PropertyValueFactory<>("AdresseClient"));
        AdresseClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(6));

        TableColumn<ClientModel,String> TelClientCol = new TableColumn<>("Télephone");
        TelClientCol.setCellValueFactory(new PropertyValueFactory<>("TelClient"));
        TelClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(6));

        TableColumn<ClientModel,String> EmailClientCol = new TableColumn<>("Email");
        EmailClientCol.setCellValueFactory(new PropertyValueFactory<>("EmailClient"));
        EmailClientCol.prefWidthProperty().bind(ClientTableList.widthProperty().divide(6));

        ClientTableList.getColumns().addAll(NomClientCol,PrenomClientCol,NPermisCol,AdresseClientCol,EmailClientCol,TelClientCol);

        ClientTableList.setItems(FXCollections.observableArrayList(
                new ClientModel("FA191919","BOUKHRISS","MOHAMED","13 RUE 12 HAY QODS OUJDA","BOOUKHRISS@GMAIL.COM","06060606062","SA23/232"),
                new ClientModel("FA191919","BOUKHRISS","MOHAMED","13 RUE 12 HAY QODS OUJDA","BOOUKHRISS@GMAIL.COM","06060606062","SA23/232"),
                new ClientModel("FA191919","BOUKHRISS","MOHAMED","13 RUE 12 HAY QODS OUJDA","BOOUKHRISS@GMAIL.COM","06060606062","SA23/232"),
                new ClientModel("FA191919","BOUKHRISS","MOHAMED","13 RUE 12 HAY QODS OUJDA","BOOUKHRISS@GMAIL.COM","06060606062","SA23/232"),
                new ClientModel("FA191919","BOUKHRISS","MOHAMED","13 RUE 12 HAY QODS OUJDA","BOOUKHRISS@GMAIL.COM","06060606062","SA23/232")
        ));


    }



    public class ClientModel{
        private String idClient;
        private String NomClient,PrenomClient,AdresseClient,EmailClient,TelClient,NPermis;
        public ClientModel(String IdClient,String nomClient,String prenomClient,String adresseClient,String emailClient,String telClient,String Npermis){
            this.idClient=IdClient;
            this.NomClient = nomClient;
            this.PrenomClient = prenomClient;
            this.AdresseClient = adresseClient;
            this.TelClient= telClient;
            this.EmailClient=emailClient;
            this.NPermis= Npermis;
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

        public String getNPermis() {
            return NPermis;
        }

        public void setNPermis(String NPermis) {
            this.NPermis = NPermis;
        }
    }
}
