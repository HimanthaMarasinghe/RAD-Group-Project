module com.radgroup.cinemahallticketmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.radgroup.cinemahallticketmanagementsystem to javafx.fxml;
    exports com.radgroup.cinemahallticketmanagementsystem;
}