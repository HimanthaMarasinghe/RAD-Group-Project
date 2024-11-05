package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class Movies implements Cont {
    @FXML
    private Text welcome;

    @FXML
    private GridPane movieGrid;

    @Override
    public void setView(Object data) {
        if (data instanceof String) {
            welcome.setText("Welcome "+(String) data); // Set the username on the label
        }
        try {
            FXMLLoader loader = new FXMLLoader(Movies.class.getResource("/com/radgroup/cinemahallticketmanagementsystem/UIcomponents/Card.fxml"));
            VBox vb = loader.load();
            Card card = loader.getController();
            card.setData(null);
            movieGrid.add(vb, 2, 2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
