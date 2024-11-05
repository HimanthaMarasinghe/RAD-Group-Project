package com.radgroup.cinemahallticketmanagementsystem.controllers;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * All the controllers for dialog boxes should be extended from this class
 */
public class dialogBox {

    public Dialog<ButtonType> dialog;

    public void initDialog(Dialog<ButtonType> dialog) {
        this.dialog = dialog;
    }
}
