package com.radgroup.cinemahallticketmanagementsystem.util;

import com.radgroup.cinemahallticketmanagementsystem.controllers.DataReceiver;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    /**
     * Changes the current scene of the application.
     * This method is intended to be called within a controller method that is connected to a UI node.
     * The UI node should trigger an action event, which is then passed to this method to facilitate
     * the scene transition.
     * @param event Action event of the UI node
     * @param sceneName Name (without extension) of the fxml file that should be displayed. Ex: "Home"
     * @param data Any data that should be dynamically displayed in the view. null if there is no data to pass.
     * @throws IOException Should be handled in the controller method using a try catch block
     */
    public static void switchScene(ActionEvent event, String sceneName, Object data) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource("/com/radgroup/cinemahallticketmanagementsystem/"+sceneName+".fxml"));
        Scene Scene = new Scene(fxmlLoader.load());

        // Get the controller associated with the FXML
        Object controller = fxmlLoader.getController();

        // Check if the controller has a method to receive the data
        if (controller instanceof DataReceiver) {
            ((DataReceiver) controller).receiveData(data); // Pass the data to the controller
        }

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
        stage.setTitle(sceneName);
        stage.show();
    }
}
