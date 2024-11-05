module com.radgroup.cinemahallticketmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;


    opens com.radgroup.cinemahallticketmanagementsystem to javafx.fxml;
    exports com.radgroup.cinemahallticketmanagementsystem;
    exports com.radgroup.cinemahallticketmanagementsystem.controllers;
    opens com.radgroup.cinemahallticketmanagementsystem.controllers to javafx.fxml;
}