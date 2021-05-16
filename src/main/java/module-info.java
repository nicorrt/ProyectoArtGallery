module com.anramirez.primerProyecto {
    requires javafx.controls;
    requires javafx.fxml;
	requires transitive java.sql;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.base;
	requires java.desktop;
	requires java.xml.bind;
	requires java.xml;
	requires transitive javafx.swing;

    opens com.anramirez.primerProyecto to javafx.fxml;
    opens com.anramirez.primerProyecto.model to javafx.base;
    opens com.anramirez.primerProyecto.utils to java.xml.bind;

    exports com.anramirez.primerProyecto;
    exports com.anramirez.primerProyecto.utils;
    exports com.anramirez.primerProyecto.model;
}
