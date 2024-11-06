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
        showDialogBox(fxmlFile,title, null);
    }

    public void showDialogBox(String fxmlFile, String title, Object dataObject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/radgroup/cinemahallticketmanagementsystem/" + fxmlFile + ".fxml"));
            DialogPane dialogBox = loader.load();
            Object dialogBoxController = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();

            if (dialogBoxController instanceof dialogBox) {
                ((dialogBox) dialogBoxController).initDialog(dialog, dataObject); // Pass the data to the controller
            }

            dialog.setDialogPane(dialogBox);
            dialog.setTitle(title);

            //Without a ButtonType.CANCEL the red X button in the top-right corner does not work. But the position of the ButtonType.CANCEL seems can not change. Therefore, its visibility is set to false;
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            // Get the cancel button and set it to invisible
            dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

            Optional<ButtonType> result = dialog.showAndWait();
            System.out.println(result);
//            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
