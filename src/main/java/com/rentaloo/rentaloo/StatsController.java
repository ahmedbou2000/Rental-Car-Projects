package com.rentaloo.rentaloo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
        ConfigureStatsItem(StatsItem1, StatsItem1img, StatsItem1title, StatsItem1value, "car_pending.png", "Réservation En Attente", "4", 120, 120);
        ConfigureStatsItem(StatsItem2, StatsItem2img, StatsItem2title, StatsItem2value, "contract_car.png", "Contrat En Cours", "3", 100, 100);
        ConfigureStatsItem(StatsItem3, StatsItem3img, StatsItem3title, StatsItem3value, "location_month.png", "Véhicule Libre", "2", 100, 100);
        ConfigureChart();
        configureTableLastRent();

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

    public void ConfigureChart() {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

        ChartItem.setTitle("Nombre de location Durant" + LocalDate.now().getYear());

        // Add data to the series
        dataSeries.getData().add(new XYChart.Data<>("Janvier", 10));
        dataSeries.getData().add(new XYChart.Data<>("Février", 20));
        dataSeries.getData().add(new XYChart.Data<>("Avril", 30));
        dataSeries.getData().add(new XYChart.Data<>("Mai", 30));
        dataSeries.getData().add(new XYChart.Data<>("Juin", 20));
        dataSeries.getData().add(new XYChart.Data<>("Juillet", 30));
        dataSeries.getData().add(new XYChart.Data<>("Aôut", 60));
        dataSeries.getData().add(new XYChart.Data<>("Septembre", 50));
        dataSeries.getData().add(new XYChart.Data<>("Octobre", 30));
        dataSeries.getData().add(new XYChart.Data<>("Novembre", 10));
        dataSeries.getData().add(new XYChart.Data<>("Décembre", 70));

        ChartItem.getData().add(dataSeries);
        ChartItem.setLegendVisible(false);
    }

    public void configureTableLastRent() {
        TableColumn<Rent, String> immatriculeCol = new TableColumn<>("Immatriculation");
        immatriculeCol.setCellValueFactory(new PropertyValueFactory<>("Immatriculation"));
        immatriculeCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(4));
        immatriculeCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Rent, String> NomCompletCol = new TableColumn<>("NomComplet");
        NomCompletCol.setCellValueFactory(new PropertyValueFactory<>("NomComplet"));
        NomCompletCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(4));
        NomCompletCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Rent, String> DepartCol = new TableColumn<>("DateDepart");
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));
        DepartCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(4));
        DepartCol.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Rent, String> RetourCol = new TableColumn<>("DateRetour");
        RetourCol.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        RetourCol.prefWidthProperty().bind(tableLastRent.widthProperty().divide(4));
        RetourCol.setMaxWidth(Double.MAX_VALUE);

        tableLastRent.getColumns().removeAll();
        tableLastRent.getColumns().addAll(immatriculeCol, NomCompletCol, DepartCol, RetourCol);

        tableLastRent.setItems(FXCollections.observableArrayList(
                new Rent("12345-A-49","BOUKHRISS MOHAMED","01/05/2023","12/05/2023"),
                new Rent("12346-A-49","BENAISSATI AHMED","02/05/2023","21/05/2023"),
                new Rent("12347-A-49","CHIKOUR KAMEL","06/05/2023","30/05/2023"),
                new Rent("12348-A-49","BELAMIN JAWAD","02/05/2023","13/05/2023")
        ));

//        tableLastRent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public static class Rent {
        private String Immatriculation;
        private String NomComplet;

        private String DateDepart;
        private String DateRetour;

        public Rent(String immat, String nomComplet, String depart, String retour) {
            this.Immatriculation = immat;
            this.NomComplet = nomComplet;
            this.DateDepart = depart;
            this.DateRetour = retour;
        }


        public String getImmatriculation() {
            return Immatriculation;
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
