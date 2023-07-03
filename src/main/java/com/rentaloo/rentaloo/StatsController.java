package com.rentaloo.rentaloo;

import DbContext.DbContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatsController implements Initializable {

    @FXML
    private HBox StatsContainer;
    @FXML
    private AnchorPane StatsPane, StatsItem1, StatsItem2, StatsItem3, ContentStatsPane, StatsItem1img, StatsItem2img, StatsItem3img, chartContentState;
    @FXML
    private VBox statsContainer1, statsContainer2, statsContainer3;
    @FXML
    private Label StatsItem1title, StatsItem1value, StatsItem2title, StatsItem2value, StatsItem3title, StatsItem3value;
    @FXML
    private BarChart ChartItem;
    @FXML
    private TableView tableLastRent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Resize();
        try {
            // get data for statistics :
            String nbrReservationsEnAttentes = getReservationEnAttenteCount();
            String nbrContratsEnCours = getContratsEnCoursCount();
            String nbrVehiculesLibires = getVerhiculesLibresCount();

            ConfigureStatsItem(StatsItem1, StatsItem1img, StatsItem1title, StatsItem1value, "car_pending.png", "Réservation En Attente", nbrReservationsEnAttentes, 120, 120);
            ConfigureStatsItem(StatsItem2, StatsItem2img, StatsItem2title, StatsItem2value, "contract_car.png", "Contrat En Cours", nbrContratsEnCours, 100, 100);
            ConfigureStatsItem(StatsItem3, StatsItem3img, StatsItem3title, StatsItem3value, "location_month.png", "Véhicule Libre", nbrVehiculesLibires, 100, 100);

            ConfigureChart();

            configureTableLastRent();


        }catch (Exception e){ }
    }



    public void Resize() {
        StatsContainer.prefHeightProperty().bind(StatsPane.heightProperty().multiply(0.25));
        ContentStatsPane.prefHeightProperty().bind(StatsPane.heightProperty().multiply(0.75));
        statsContainer1.prefWidthProperty().bind(StatsContainer.widthProperty().multiply(0.33));
        statsContainer2.prefWidthProperty().bind(StatsContainer.widthProperty().multiply(0.33));
        statsContainer3.prefWidthProperty().bind(StatsContainer.widthProperty().multiply(0.33));

        statsContainer1.prefHeightProperty().bind(StatsContainer.heightProperty());
        statsContainer2.prefHeightProperty().bind(StatsContainer.heightProperty());
        statsContainer3.prefHeightProperty().bind(StatsContainer.heightProperty());

        StatsItem1.prefHeightProperty().bind(statsContainer1.heightProperty().multiply(0.8));
        StatsItem2.prefHeightProperty().bind(statsContainer1.heightProperty().multiply(0.8));
        StatsItem3.prefHeightProperty().bind(statsContainer1.heightProperty().multiply(0.8));

        chartContentState.prefHeightProperty().bind(ContentStatsPane.heightProperty().multiply(0.45));
        tableLastRent.prefHeightProperty().bind(ContentStatsPane.heightProperty().multiply(0.45));
//        StatsItem1img.fitHeightProperty().bind(StatsItem1.heightProperty());

    }


    public void ConfigureStatsItem(AnchorPane statsItem, AnchorPane statsItemimg, Label titleLabel, Label valueLabel, String img, String title, String value, double width, double height) {
        //Configure Image Icon
        statsItemimg.prefHeightProperty().bind(statsItem.heightProperty());
        statsItemimg.prefWidthProperty().bind(statsItem.widthProperty().multiply(0.35));
        BackgroundImage bgStat1img = new BackgroundImage(new Image(getClass().getResource("/Images/Icons/" + img).toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, false, false));
        Background bgStat1 = new Background(bgStat1img);
        statsItemimg.setBackground(bgStat1);

        //Configure Sizing
        titleLabel.prefWidthProperty().bind(statsItem.widthProperty().multiply(0.65));
        titleLabel.prefHeightProperty().bind(statsItem.heightProperty().multiply(0.5));
        valueLabel.prefWidthProperty().bind(statsItem.widthProperty().multiply(0.65));
        valueLabel.prefHeightProperty().bind(statsItem.heightProperty().multiply(0.5));
        //Configure Text
        titleLabel.setText(title);
        valueLabel.setText(value);
    }

    public void ConfigureChart() throws Exception {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

        ChartItem.setTitle("Nombre de location Durant " + LocalDate.now().getYear());

        // get data for each month 1 to 12 for the currentYear :
        try {
            int nbrLocationsJanvier = getLocationsForTheCurrentYear(1);
            int nbrLocationsFevrier = getLocationsForTheCurrentYear(2);
            int nbrLocationsMars = getLocationsForTheCurrentYear(3);
            int nbrLocationsAvril = getLocationsForTheCurrentYear(4);
            int nbrLocationsMai = getLocationsForTheCurrentYear(5);
            int nbrLocationsJuin = getLocationsForTheCurrentYear(6);
            int nbrLocationsJuillet = getLocationsForTheCurrentYear(7);
            int nbrLocationsAout = getLocationsForTheCurrentYear(8);
            int nbrLocationsSeptembre = getLocationsForTheCurrentYear(9);
            int nbrLocationsOctobre= getLocationsForTheCurrentYear(10);
            int nbrLocationsNovembre = getLocationsForTheCurrentYear(11);
            int nbrLocationsDecembre = getLocationsForTheCurrentYear(12);



            // Add data to the series
        dataSeries.getData().add(new XYChart.Data<>("Janvier", nbrLocationsJanvier));
        dataSeries.getData().add(new XYChart.Data<>("Février", nbrLocationsFevrier));
            dataSeries.getData().add(new XYChart.Data<>("Mars", nbrLocationsMars));
            dataSeries.getData().add(new XYChart.Data<>("Avril", nbrLocationsAvril));
        dataSeries.getData().add(new XYChart.Data<>("Mai", nbrLocationsMai));
        dataSeries.getData().add(new XYChart.Data<>("Juin", nbrLocationsJuin));
        dataSeries.getData().add(new XYChart.Data<>("Juillet", nbrLocationsJuillet));
        dataSeries.getData().add(new XYChart.Data<>("Aôut", nbrLocationsAout));
        dataSeries.getData().add(new XYChart.Data<>("Septembre", nbrLocationsSeptembre));
        dataSeries.getData().add(new XYChart.Data<>("Octobre", nbrLocationsOctobre));
        dataSeries.getData().add(new XYChart.Data<>("Novembre", nbrLocationsNovembre));
        dataSeries.getData().add(new XYChart.Data<>("Décembre", nbrLocationsDecembre));

        }catch (Exception e){
            throw  new Exception();
        }

        ChartItem.getData().add(dataSeries);
        ChartItem.setLegendVisible(false);
    }

    private int getLocationsForTheCurrentYear(int month) throws Exception {
        try {
            // get : les Locations par mois de l'année courante :
            ResultSet result = DbContext.Execute("SELECT COUNT(*)\n" +
                                                    "from reservation r \n" +
                                                    "where r.IDRESERVATION in (SELECT DISTINCT r.IDRESERVATION\n" +
                                                    "FROM reservation r\n" +
                                                    "INNER JOIN detail d on d.IDRESERVATION = r.IDRESERVATION\n" +
                                                    "WHERE d.STATUT = 'accepté'\n" +
                                                    "    AND (YEAR(r.DATEDEBUT) = YEAR(CURRENT_DATE()) OR YEAR(r.DATEFIN) = YEAR(CURRENT_DATE()))\n" +
                                                    "    AND (MONTH(r.DATEDEBUT) = "+month+" OR MONTH(r.DATEFIN) = "+month+"));");
            result.next();
            int NbrLocations =  result.getInt(1);

            return NbrLocations;
        }catch (Exception e){
            HelloApplication.InformationAlert("Erreur", "", "Erreur lors de l'affichage des données (Reservations En Attente");
            throw new Exception();
        }
    }

    public void configureTableLastRent() throws Exception {


        TableColumn<Rent, String> numReservationCol = new TableColumn<>("N° Reservation");
        numReservationCol.setCellValueFactory(new PropertyValueFactory<>("NumReservation"));
        numReservationCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(5));

        TableColumn<Rent, String> immatriculeCol = new TableColumn<>("Immatriculation");
        immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatriculation"));
        immatriculeCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(5));
        immatriculeCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Rent, String> NomCompletCol = new TableColumn<>("NomComplet");
        NomCompletCol.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        NomCompletCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(5));
        NomCompletCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Rent, String> DepartCol = new TableColumn<>("DateDepart");
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DepartCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(5));
        DepartCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Rent, String> RetourCol = new TableColumn<>("DateRetour");
        RetourCol.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        RetourCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(5));
        RetourCol.setMaxWidth(Double.MAX_VALUE);

        tableLastRent.getColumns().removeAll();
        tableLastRent.getColumns().addAll(numReservationCol, immatriculeCol, NomCompletCol, DepartCol, RetourCol);


        try{
            // get reservations en cours :

            ResultSet result = DbContext.Execute("SELECT r.idReservation , v.IMMATRICULE , CONCAT(c.NOM, \" \" ,c.PRENOM) as 'NomComplet' , r.DATEDEBUT , r.DATEFIN \n" +
                                                        "FROM reservation r\n" +
                                                        "INNER JOIN detail d on d.IDRESERVATION = r.IDRESERVATION\n" +
                                                        "INNER JOIN voiture v on v.IDVOITURE = r.IDVOITURE \n" +
                                                        "INNER JOIN client c on c.IDCLIENT = r.IDCLIENT\n" +
                                                        "WHERE d.STATUT = 'accepté' and CURRENT_DATE() BETWEEN r.DATEDEBUT and r.DATEFIN");
            List<Rent> listReservationsEnCours = new ArrayList<>();
            while (result.next()){
                listReservationsEnCours.add(new Rent(
                                                    result.getString("idReservation"),
                                                    result.getString("immatricule"),
                                                    result.getString("NomComplet"),
                                                    result.getString("dateDebut"),
                                                    result.getString("dateFin")
                        ));
            }

        tableLastRent.setItems(FXCollections.observableArrayList(listReservationsEnCours));
        }catch (Exception e){
            throw new Exception();
        }

    }

    public String getReservationEnAttenteCount() throws Exception {
        String stringNbrReservationEnAttentes = "0" ;
        try {
            // get : les reservation en attente ( les reservation où le status = ''en attente''
            ResultSet result = DbContext.Execute("SELECT COUNT(*) FROM `detail` WHERE STATUT = 'en attente'");
            result.next();
            int nbrReservationEnAttente =  result.getInt(1);
            stringNbrReservationEnAttentes = String.valueOf(nbrReservationEnAttente);

        }catch (Exception e){
            HelloApplication.InformationAlert("Erreur", "", "Erreur lors de l'affichage des données (Reservations En Attente");
            throw new Exception();
        }

        return stringNbrReservationEnAttentes;
    }

    public String getContratsEnCoursCount() throws Exception{
        String stringNbrContratsEnCours = "0" ;
        try {
            // get : les contrats en Cours
            ResultSet result = DbContext.Execute("SELECT COUNT(*) \n" +
                                                    "FROM `reservation` r\n" +
                                                    "INNER JOIN detail d ON d.IDRESERVATION = r.IDRESERVATION\n" +
                                                    "WHERE d.STATUT = 'accepté' AND CURRENT_DATE() BETWEEN r.DATEDEBUT AND r.DATEFIN;");
            result.next();
            int NbrContratsEnCours =  result.getInt(1);
            stringNbrContratsEnCours = String.valueOf(NbrContratsEnCours);

        }catch (Exception e){
            HelloApplication.InformationAlert("Erreur", "", "Erreur lors de l'affichage des données (Contrats en cours)");
            throw new Exception();
        }

        return stringNbrContratsEnCours;

    }

    private String getVerhiculesLibresCount() throws Exception{
        String stringNbrVehiculesLibres = "0";
        try {
            // get : les vehicules libres :
            ResultSet result = DbContext.Execute("SELECT COUNT(*) \n" +
                                                    "FROM voiture v \n" +
                                                    "WHERE v.IDVOITURE NOT IN (SELECT DISTINCT v.IDVOITURE\n" +
                                                    "FROM `reservation` r\n" +
                                                    "INNER JOIN detail d ON d.IDRESERVATION = r.IDRESERVATION\n" +
                                                    "inner join voiture v on v.IDVOITURE = r.IDVOITURE\n" +
                                                    "WHERE d.STATUT = 'accepté' AND CURRENT_DATE() BETWEEN r.DATEDEBUT AND r.DATEFIN );");
            result.next();
            int NbrVehiculesLibres = result.getInt(1);
            stringNbrVehiculesLibres = String.valueOf(NbrVehiculesLibres);

        } catch (Exception e) {
            HelloApplication.InformationAlert("Erreur", "", "Erreur lors de l'affichage des données (Vehicules libres");
            throw new Exception();
        }

        return stringNbrVehiculesLibres;

    }
    public static class Rent {

        private String NumReservation;
        private String Immatriculation;
        private String NomComplet;

        private String DateDepart;
        private String DateRetour;

        public Rent(String numReservation, String immat, String nomComplet, String depart, String retour) {
            this.NumReservation = numReservation;
            this.Immatriculation = immat;
            this.NomComplet = nomComplet;
            this.DateDepart = depart;
            this.DateRetour = retour;
        }


        public String getImmatriculation() {
            return Immatriculation;
        }

        public void setNumReservation(String numReservation){
            this.NumReservation = numReservation;
        }

        public String getNumReservation(){
            return this.NumReservation;
        }

        public void setImmatriculation(String immatriculation) {
            Immatriculation = immatriculation;
        }

        public String getNomComplet() {
            return NomComplet;
        }

        public void setNomComplet(String nomComplet) {
            NomComplet = nomComplet;
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
