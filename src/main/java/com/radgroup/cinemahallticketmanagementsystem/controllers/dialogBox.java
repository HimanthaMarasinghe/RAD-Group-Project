package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * All the controllers for dialog boxes should be extended from this class
 */
public class dialogBox {

    public Dialog<ButtonType> dialog;

//    public void initDialog(Dialog<ButtonType> dialog) {
//        this.dialog = dialog;
//    }

    public void initDialog(Dialog<ButtonType> dialog, Object dataObject) {
        this.dialog = dialog;
        setDialogBox(dataObject);
    }

    @FXML
    private void cancel(ActionEvent event) {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }

    /**
     * called in the initDialog method (when the dialog box is initialized).
     * should be overridden if there should be any dynamic data in the Dialog box.
     * Ex: If the dialog box is used to update something current data should be in the textFields.
     */
    public void setDialogBox(Object dataObject){
        return;
    }
}
