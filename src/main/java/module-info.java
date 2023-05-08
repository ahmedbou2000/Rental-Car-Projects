module com.rentaloo.rentaloo {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            requires eu.hansolo.tilesfx;
        
    opens com.rentaloo.rentaloo to javafx.fxml;
    exports com.rentaloo.rentaloo;
}