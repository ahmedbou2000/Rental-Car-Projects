module com.rentaloo.rentaloo {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.mariadb.jdbc;

    opens com.rentaloo.rentaloo to javafx.fxml;
    exports com.rentaloo.rentaloo;
}