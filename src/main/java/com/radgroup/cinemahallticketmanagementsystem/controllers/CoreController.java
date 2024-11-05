package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.Optional;

/**
 * This class contain basic methods that all the controllers might need.
 */
public class CoreController {
    public void showDialogBox(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/"+fxmlFile+".fxml"));
            DialogPane dialogBox = loader.load();
            Object dialogBoxController = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();

            if (dialogBoxController instanceof dialogBox) {
                ((dialogBox) dialogBoxController).initDialog(dialog); // Pass the data to the controller
            }

            dialog.setDialogPane(dialogBox);
            dialog.setTitle(title);

            Optional<ButtonType> result = dialog.showAndWait();
            System.out.println(result);
//            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
